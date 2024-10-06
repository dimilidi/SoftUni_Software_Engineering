package org.lididimi.gamestore.services.user;

import jakarta.transaction.Transactional;
import org.lididimi.gamestore.domain.dtos.user.UserLoginDTO;
import org.lididimi.gamestore.domain.dtos.user.UserOwnedGameTitlesDTO;
import org.lididimi.gamestore.domain.dtos.user.UserRegisterDTO;
import org.lididimi.gamestore.domain.entities.Game;
import org.lididimi.gamestore.domain.entities.User;
import org.lididimi.gamestore.repositories.GameRepository;
import org.lididimi.gamestore.repositories.UserRepository;
import org.lididimi.gamestore.services.order.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.lididimi.gamestore.constants.Messages.*;

@Service
public class UserServiceImpl implements UserService {

    private User loggedInUser;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final OrderService orderService;

    public UserServiceImpl(ModelMapper mapper, UserRepository userRepository, GameRepository gameRepository, OrderService orderService) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.orderService = orderService;
    }

    @Override
    public String registerUser(String[] args) {

        final String email = args[0];
        final String password = args[1];
        final String confirmPassword = args[2];
        final String fullName = args[3];

        final UserRegisterDTO userRegisterDTO;

        try {
            userRegisterDTO = new UserRegisterDTO(email, password, confirmPassword, fullName);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        boolean isUserFound = this.userRepository.existsByEmail(userRegisterDTO.getEmail());

        if (isUserFound) {
            return EMAIL_ALREADY_EXISTS;
        }

        final User user = this.mapper.map(userRegisterDTO, User.class);

        user.setAdministrator(this.userRepository.count() == 0);

        this.userRepository.save(user);

        return userRegisterDTO.successfulRegisteredUser();
    }

    @Override
    public String loginUser(String[] userData) {

        if (this.loggedInUser != null) {
            return USER_ALREADY_LOGGED_IN;
        }

        final String email = userData[0];
        final String password = userData[1];

        final UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);

        final Optional<User> user = this.userRepository.findByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword());

        if (user.isPresent() && this.loggedInUser == null) {

            this.loggedInUser = this.userRepository.findByEmail(userLoginDTO.getEmail()).get();

            return String.format(USER_LOGGED_IN_SUCCESSFULLY, this.loggedInUser.getFullName());
        }

        return String.format(USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE);
    }

    @Override
    public String logoutUser() {

        if (this.loggedInUser == null) {
            return USER_CANNOT_LOGGED_IN;
        }

        final String fullName = this.loggedInUser.getFullName();

        this.loggedInUser = null;

        return String.format(USER_LOGGED_OUT_SUCCESSFULLY, fullName);
    }

    @Override
    public String getUserOwnedGames() {

        if (this.loggedInUser == null) {
            return USER_MUST_BE_LOGGED_IN;
        }

        final User user = this.userRepository.findUserById(this.loggedInUser.getId()).get();
        final Set<Game> ownedGames = user.getGames();

        if (ownedGames.isEmpty()) {
            return String.format(USER_DOES_NOT_OWN_GAMES, user.getFullName());
        }

        final Set<UserOwnedGameTitlesDTO> userOwnedGameTitlesDTOS =
                ownedGames
                        .stream()
                        .map(e -> mapper.map(e, UserOwnedGameTitlesDTO.class))
                        .collect(Collectors.toSet());

        final StringBuilder sb = new StringBuilder();
        sb.append(String.format(USER_OWNS_GAMES, user.getFullName()))
                .append(System.lineSeparator());

        userOwnedGameTitlesDTOS
                .forEach(e -> sb.append(e.getTitle())
                        .append(System.lineSeparator()));

        return sb.toString().trim();
    }

    @Override
    @Transactional
    public String addItemToShoppingCart(String[] data) {

        if (this.loggedInUser == null) {
            return USER_MUST_BE_LOGGED_IN;
        }

        final String title = data[0];

        final Optional<Game> gameByTitle = this.gameRepository.findFirstByTitle(title);

        if (gameByTitle.isEmpty()) {
            return String.format(GAME_TITLE_DOES_NOT_EXISTS, title);
        }

        final User user = this.userRepository.findUserById(this.loggedInUser.getId()).get();
        final Game game = gameByTitle.get();

        if (hasUserBoughtTitle(title, user)) {
            return String.format(USER_ALREADY_BOUGHT_TITLE, title);
        }

        for (Game currentGame : user.getShoppingCart()) {
            if (currentGame.getTitle().equals(title)) {
                return String.format(GAME_ALREADY_IN_CART, title);
            }
        }

        user.getShoppingCart().add(game);

        this.userRepository.save(user);

        return String.format(GAME_ADDED_TO_CART_SUCCESSFULLY, title);
    }

    @Override
    @Transactional
    public String removeItemFromShoppingCart(String[] data) {

        if (this.loggedInUser == null) {
            return USER_MUST_BE_LOGGED_IN;
        }

        final String title = data[0];

        final Optional<Game> gameByTitle = this.gameRepository.findFirstByTitle(title);

        if (gameByTitle.isEmpty()) {
            return String.format(GAME_TITLE_DOES_NOT_EXISTS, title);
        }

        final User user = this.userRepository.findUserById(this.loggedInUser.getId()).get();

        if (user.getShoppingCart().isEmpty()) {
            return SHOPPING_CART_EMPTY;
        }

        for (Game currentGame : user.getShoppingCart()) {
            if (currentGame.getTitle().equals(title)) {
                user.getShoppingCart().remove(currentGame);
                break;
            }
        }

        this.userRepository.save(user);

        return String.format(GAME_REMOVED_FROM_CART_SUCCESSFULLY, title);
    }

    @Override
    @Transactional
    public String buyItemsFromShoppingCart() {

        if (this.loggedInUser == null) {
            return USER_MUST_BE_LOGGED_IN;
        }

        final User user = this.userRepository.findUserById(this.loggedInUser.getId()).get();

        if (user.getShoppingCart().isEmpty()) {
            return SHOPPING_CART_EMPTY;
        }

        final Set<Game> shoppingCart = new LinkedHashSet<>(user.getShoppingCart());

        this.orderService.createOrder(user, shoppingCart);

        user.getGames().addAll(user.getShoppingCart());

        user.getShoppingCart().clear();

        this.userRepository.save(user);

        final StringBuilder sb = new StringBuilder();

        shoppingCart
                .forEach(e -> sb.append(String.format(" -%s", e.getTitle()))
                        .append(System.lineSeparator()));

        return String.format(SUCCESSFULLY_BOUGHT_GAMES, sb.toString().trim().replaceAll("(?m)^[ \t]*\r?\n", ""));
    }

    @Override
    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    private static boolean hasUserBoughtTitle(String title, User user) {

        final Set<Game> games = user.getGames();

        for (Game current : games) {
            if (current.getTitle().equals(title)) {
                return true;
            }
        }

        return false;
    }
}