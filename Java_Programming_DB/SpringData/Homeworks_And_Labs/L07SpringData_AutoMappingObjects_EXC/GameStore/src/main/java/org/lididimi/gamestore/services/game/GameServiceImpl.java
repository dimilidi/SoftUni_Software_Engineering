package org.lididimi.gamestore.services.game;

import jakarta.transaction.Transactional;
import org.lididimi.gamestore.domain.dtos.game.GameDTO;
import org.lididimi.gamestore.domain.dtos.game.GameDetailedInfoDTO;
import org.lididimi.gamestore.domain.dtos.game.GameTitlePriceDTO;
import org.lididimi.gamestore.domain.entities.Game;
import org.lididimi.gamestore.domain.entities.User;
import org.lididimi.gamestore.repositories.GameRepository;
import org.lididimi.gamestore.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.lididimi.gamestore.constants.Messages.*;

@Service
public class GameServiceImpl implements GameService {

    private final ModelMapper mapper;
    private final GameRepository gameRepository;
    private final UserService userService;


    public GameServiceImpl(ModelMapper mapper, GameRepository gameRepository, UserService userService) {
        this.mapper = mapper;
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public String addGame(String[] gameData) {

        if (!isUserLoggedInAdministrator()) {
            return USER_MUST_BE_LOGGED_OR_ADMIN_TO_ADD_OR_EDIT_GAME;
        }

        final String title = gameData[0];
        if ( existByTitle(title)) return String.format(GAME_ALREADY_EXISTS, title);

        final BigDecimal price = BigDecimal.valueOf(Double.parseDouble(gameData[1]));
        final Double size = Double.parseDouble(gameData[2]);
        final String trailerId = gameData[3];
        final String thumbnailUrl = gameData[4];
        final String description = gameData[5];
        final LocalDate releaseDate = LocalDate.parse(gameData[6], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        final GameDTO gameDTO;

        try {
            gameDTO = new GameDTO(title, trailerId, thumbnailUrl, size, price, description, releaseDate);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        final Game gameToSave = this.mapper.map(gameDTO, Game.class);
        this.gameRepository.save(gameToSave);

        return String.format(GAME_ADDED_SUCCESSFULLY, title);
    }

    private boolean existByTitle(String title) {
        return gameRepository.findFirstByTitle(title).isPresent();
    }

    @Override
    @Transactional
    public String editGame(String[] gameData) {
        // Check user authorization
        if (!isUserLoggedInAdministrator()) {
            return USER_MUST_BE_LOGGED_OR_ADMIN_TO_ADD_OR_EDIT_GAME;
        }

        // Parse and validate ID
        Long gameId = parseGameId(gameData[0]);

        try {
            // Fetch and check if the game exists (throws exception if not found)
            Game game = findGameById(gameId);
            // Map Game entity to GameDTO
            GameDTO gameDTO = mapper.map(game, GameDTO.class);
            // Update game fields using the DTO
            updateGameFields(gameDTO, gameData);
            // Map updated DTO back to the entity
            mapper.map(gameDTO, game);
            gameRepository.save(game);

            return String.format(GAME_EDITED_SUCCESSFULLY, game.getTitle());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    @Transactional
    public String deleteGame(String[] gameData) {
        if (!isUserLoggedInAdministrator()) {
            return USER_MUST_BE_LOGGED_OR_ADMIN_TO_ADD_OR_EDIT_GAME;
        }

        Long id = parseGameId(gameData[0]);

        try {
            Game game = findGameById(id);
            gameRepository.deleteById(id);
            return String.format(GAME_DELETED_SUCCESSFULLY, game.getTitle());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String getAllGames() {
        final List<Game> allGames = gameRepository.findAll();

         return allGames.isEmpty()
                 ? GAME_STORE_IS_EMPTY
                 : allGames
                        .stream()
                        .map(game -> mapper.map(game, GameTitlePriceDTO.class))
                        .map(GameTitlePriceDTO::toString)
                        .collect(Collectors.joining(""));
    }


    @Override
    public String getInfoAboutAGame(String[] data) {
        return gameRepository.findFirstByTitle(data[0])
                .map(game -> mapper.map(game, GameDetailedInfoDTO.class).toString())
                .orElseThrow(() -> new NoSuchElementException(String.format(GAME_TITLE_DOES_NOT_EXISTS, data[0])));
    }


    private void updateGameFields(GameDTO gameDTO, String[] gameData) throws Exception {

        for (String data : Arrays.stream(gameData).skip(1).toArray(String[]::new)) {
            String[] splitData = data.split("=");
            String field = splitData[0];
            String value = splitData[1];

            switch (field) {
                case "title":
                    gameDTO.setTitle(value);
                    break;
                case "trailerId":
                    gameDTO.setTrailerId(value);
                    break;
                case "thumbnailUrl":
                    gameDTO.setThumbnailUrl(value);
                    break;
                case "description":
                    gameDTO.setDescription(value);
                    break;
                case "price":
                    gameDTO.setPrice(new BigDecimal(value));
                    break;
                case "size":
                    gameDTO.setSize(Double.parseDouble(value));
                    break;
                case "releaseDate":
                    gameDTO.setReleaseDate(LocalDate.parse(value, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    break;
                default:
                    throw new Exception(String.format("Invalid field: %s", field));
            }
        }
    }

    private Long parseGameId(String idString) {
        try {
            return Long.valueOf(idString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ENTER_VALID_ID);
        }
    }

    private Game findGameById(Long gameId) {
        return this.gameRepository.findById(gameId)
                .orElseThrow(() -> new NoSuchElementException(String.format(GAME_DOES_NOT_EXISTS, gameId)));
    }


    private boolean isUserLoggedInAdministrator() {
        User loggedInUser = userService.getLoggedInUser();
        return loggedInUser != null && loggedInUser.getAdministrator();
    }

}