package org.lididimi.bookshopsystem._02UserSystem;

import org.lididimi.bookshopsystem._02UserSystem.entities.User;
import org.lididimi.bookshopsystem._02UserSystem.services.SeedService;
import org.lididimi.bookshopsystem._02UserSystem.services.TownService;
import org.lididimi.bookshopsystem._02UserSystem.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static org.lididimi.bookshopsystem._02UserSystem.constants.Constants.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final Scanner scanner = new Scanner(System.in);
    private SeedService seedService;
    private TownService townService;
    private UserService userService;

    public ConsoleRunner(SeedService seedService, TownService townService, UserService userService) {
        this.seedService = seedService;
        this.townService = townService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.seedService.seedAll();

        //Get users by email provider
        this.getAllUsersByEmailProvider();

        //Remove Inactive Users
        this.removeInactiveUsers();
    }

    private void removeInactiveUsers() {
        System.out.print(ENTER_DATE_IN_FORMAT);
        final String date = scanner.nextLine();

        //Setting users for deletion
        final List<User> usersNotLoggedInAfterGivenDate = setUsersForDeletion(date);

        //Deleting the users
        userService.deleteUsers(usersNotLoggedInAfterGivenDate);
    }


    private List<User> setUsersForDeletion(String date) {

        final LocalDate givenDate = LocalDate.of(Integer.parseInt(date.split("-")[2]),
                Integer.parseInt(date.split("-")[1]), Integer.parseInt(date.split("-")[0]));

        final List<User> usersNotLoggedInAfterGivenDate = this.userService.findAllByLastTimeLoggedInBefore(givenDate);

        usersNotLoggedInAfterGivenDate.forEach(e -> e.setDeleted(true));

        System.out.printf(USERS_SET_AS_DELETED, usersNotLoggedInAfterGivenDate.size());

        return usersNotLoggedInAfterGivenDate;
    }

    private void getAllUsersByEmailProvider() {
        System.out.print(ENTER_EMAIL_PROVIDER);
        final String emailProvider = scanner.nextLine();

        final List<User> allByEmailLike = this.userService.findAllByEmailLike(emailProvider);

        allByEmailLike
                .forEach(e -> System.out.printf(PRINT_USER_EMAIL_FORMAT, e.getUsername(), e.getEmail()));
    }
}