package org.lididimi.bookshopsystem._02UserSystem.services.impl;

import org.lididimi.bookshopsystem._02UserSystem.entities.Town;
import org.lididimi.bookshopsystem._02UserSystem.entities.User;
import org.lididimi.bookshopsystem._02UserSystem.services.SeedService;
import org.lididimi.bookshopsystem._02UserSystem.services.TownService;
import org.lididimi.bookshopsystem._02UserSystem.services.UserService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.stream.Collectors;

import static org.lididimi.bookshopsystem._02UserSystem.constants.FilePath.TOWN_FILE_NAME;
import static org.lididimi.bookshopsystem._02UserSystem.constants.FilePath.USER_FILE_NAME;


@Service
public class SeedServiceImpl implements SeedService {

    private final TownService townService;
    private final UserService userService;

    public SeedServiceImpl(TownService townService, UserService userService) {
        this.townService = townService;
        this.userService = userService;
    }

    @Override
    public void seedTowns() throws IOException {

        if (!this.townService.isDataSeeded()) {

            final String regex = ",\\s+";

            this.townService
                    .seedTowns(Files.readAllLines
                                    (Path.of(TOWN_FILE_NAME))
                            .stream()
                            .filter(e -> !e.isBlank())
                            .map(row -> {
                                String[] parts = row.split(regex);
                                return new Town(parts[0], parts[1]);
                            })
                            .collect(Collectors.toList()));
        }
    }

    @Override
    public void seedUsers() throws IOException {

        if (!this.userService.isDataSeeded()) {
            this.userService
                    .seedUsers(Files.readAllLines(Path.of(USER_FILE_NAME))
                            .stream()
                            .filter(e -> !e.isBlank())
                            .map(this::createUser)
                            .collect(Collectors.toList()));
        }
    }

    private User createUser(String row) {
        final String regex = "\\s+";

        String[] parts = row.split(regex);
        String username = parts[0];
        String password = parts[1];
        String email = parts[2];
        LocalDate registeredOn = LocalDate.now();
        LocalDate lastTimeLoggedIn = LocalDate.now();
        Integer age = Integer.parseInt(row.split(regex)[3]);
        Boolean isDeleted = false;
        Town bornInTown = townService.getRandomTown();
        Town currentlyLivingInTown = townService.getRandomTown();

        return new User(
                username,
                password,
                email,
                registeredOn,
                lastTimeLoggedIn,
                age,
                isDeleted,
                bornInTown,
                currentlyLivingInTown);
    }
}