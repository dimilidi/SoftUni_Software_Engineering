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
import java.util.NoSuchElementException;
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

        try {
            UserRegisterDTO userRegisterDTO = new UserRegisterDTO(email, password, confirmPassword, fullName);

            if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
                return EMAIL_ALREADY_EXISTS;
            }

            User user = this.mapper.map(userRegisterDTO, User.class);
            user.setAdministrator(this.userRepository.count() == 0);
            this.userRepository.save(user);

            return userRegisterDTO.successfulRegisteredUser();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }


    @Override
    public String loginUser(String[] userData) {
        if (this.loggedInUser != null) {
            return USER_ALREADY_LOGGED_IN;
        }

        UserLoginDTO userLoginDTO = new UserLoginDTO(userData[0], userData[1]);

        return userRepository.findByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword())
                .map(user -> {
                    this.loggedInUser = user;
                    return String.format(USER_LOGGED_IN_SUCCESSFULLY, this.loggedInUser.getFullName());
                })
                .orElse(String.format(USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE));
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

        User user = this.userRepository.findById(this.loggedInUser.getId())
                .orElseThrow(() -> new IllegalStateException(USER_MUST_BE_LOGGED_IN));

        Set<Game> ownedGames = user.getGames();

        if (ownedGames.isEmpty()) {
            return String.format(USER_DOES_NOT_OWN_GAMES, user.getFullName());
        }

        String ownedGamesTitles = ownedGames.stream()
                .map(game -> mapper.map(game, UserOwnedGameTitlesDTO.class).getTitle())
                .collect(Collectors.joining(System.lineSeparator()));

        return String.format(USER_OWNS_GAMES, user.getFullName()) + System.lineSeparator() + ownedGamesTitles;
    }


    @Override
    @Transactional
    public String addItemToShoppingCart(String[] data) {
        if (this.loggedInUser == null) {
            return USER_MUST_BE_LOGGED_IN;
        }

        final String title = data[0];
        final Game game = gameRepository.findFirstByTitle(title)
                .orElseThrow(() -> new NoSuchElementException(String.format(GAME_TITLE_DOES_NOT_EXISTS, title)));

        final User user = userRepository.findUserById(this.loggedInUser.getId()).get();

        if (hasUserBoughtTitle(title, user)) {
            return String.format(USER_ALREADY_BOUGHT_TITLE, title);
        }

        for (Game currentGame : user.getShoppingCart()) {
            if (currentGame.getTitle().equals(title)) {
                return String.format(GAME_ALREADY_IN_CART, title);
            }
        }

        user.getShoppingCart().add(game);
        userRepository.save(user);

        return String.format(GAME_ADDED_TO_CART_SUCCESSFULLY, title);
    }

    @Override
    @Transactional
    public String removeItemFromShoppingCart(String[] data) {
        if (this.loggedInUser == null) {
            return USER_MUST_BE_LOGGED_IN;
        }

        final String title = data[0];

        gameRepository.findFirstByTitle(title)
                .orElseThrow(() -> new NoSuchElementException(String.format(GAME_TITLE_DOES_NOT_EXISTS, title)));

        final User user = this.userRepository.findUserById(this.loggedInUser.getId()).get();

        if (user.getShoppingCart().isEmpty()) {
            return SHOPPING_CART_EMPTY;
        }

        user.getShoppingCart().removeIf(game -> game.getTitle().equals(title));
        userRepository.save(user);

        return String.format(GAME_REMOVED_FROM_CART_SUCCESSFULLY, title);
    }


    @Override
    @Transactional
    public String buyItemsFromShoppingCart() {
        if (this.loggedInUser == null) {
            return USER_MUST_BE_LOGGED_IN;
        }

        User user = this.userRepository.findById(this.loggedInUser.getId())
                .orElseThrow(() -> new IllegalStateException(USER_MUST_BE_LOGGED_IN));

        Set<Game> shoppingCart = user.getShoppingCart();

        if (shoppingCart.isEmpty()) {
            return SHOPPING_CART_EMPTY;
        }

        // Create an order and add the games to user's owned games
        this.orderService.createOrder(user, new LinkedHashSet<>(shoppingCart));
        user.getGames().addAll(shoppingCart);
        shoppingCart.clear();
        this.userRepository.save(user);

        // Format the titles of purchased games
        String boughtGames = shoppingCart.stream()
                .map(Game::getTitle)
                .map(title -> String.format(" -%s", title))
                .collect(Collectors.joining(System.lineSeparator()));

        return String.format(SUCCESSFULLY_BOUGHT_GAMES, boughtGames);
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