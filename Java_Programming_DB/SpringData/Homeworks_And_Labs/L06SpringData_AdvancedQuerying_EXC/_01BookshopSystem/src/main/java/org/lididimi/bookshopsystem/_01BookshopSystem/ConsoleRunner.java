package org.lididimi.bookshopsystem._01BookshopSystem;

import org.lididimi.bookshopsystem._01BookshopSystem.service.QueryService;
import org.lididimi.bookshopsystem._01BookshopSystem.service.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static org.lididimi.bookshopsystem._01BookshopSystem.constants.Constants.*;


@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final QueryService queryService;


    public ConsoleRunner(SeedService seedService, QueryService queryService) {
        this.seedService = seedService;
        this.queryService = queryService;
    }

    @Override
    public void run(String... args) throws Exception {
        //Populates the DB with the given files
        this.seedService.seedAllData();

        // Query DB
        Scanner scanner = new Scanner(System.in);
        System.out.print(ENTER_QUERY_NUMBER);
        int queryNumber = Integer.parseInt(scanner.nextLine());

        while(queryNumber !=0){

            switch (queryNumber) {
                //1. Print the titles of all books, for which the age restriction matches the given input
                // (minor, teen or adult). Ignore the casing of the input.
                case 1 -> queryService.getAllBooksByAgeRestriction();
                // Write a program that prints the titles of the golden edition books,
                // which have less than 5000 copies.
                case 2 -> queryService.getAllBooksByEditionTypeGoldAndCopies5k();
                // Write a program that prints the titles and prices of books with
                // price lower than 5 and higher than 40.
                case 3 -> queryService.getAllBooksByPriceRange();
                // Write a program that prints the titles of all books that are
                // NOT released in a given year.
                case 4 -> queryService.getAllBooksByReleaseYearNot();
                // Write a program that prints the title, the edition type and the price of books,
                // which are released before a given date. The date will be in the format dd-MM-yyyy.
                case 5 -> queryService.getBooksReleasedBeforeDate();
                // Write a program that prints the names of those authors,
                // whose first name ends with a given string.
                case 6 -> queryService.getAuthorsByFirstNameEndingWith();
                // Write a program that prints the titles of books,
                // which contain a given string (regardless of the casing).
                case 7 -> queryService.getBooksByLettersInTitle();
                // Write a program that prints the titles of books,
                // which are written by authors,
                // whose last name starts with a given string.
                case 8 -> queryService.getBooksByStartOfAuthorsLastName();
                // Write a program that prints the number of books,
                // whose title is longer than a given number.
                case 9 -> queryService.getBooksCountWithTitleLongerThan();
                // Write a program that prints the total number of book copies by author.
                // Order the results descending by total book copies.
                case 10 -> queryService.getBooksTotalCopiesByAuthor();
                // Write a program that prints information (title, edition type, age restriction and price)
                // for a book by given title.
                case 11 -> queryService.getTitLeEditionRestrictionPriceOfBook();
                // Write a program that increases the copies of all books
                // released after a given date with a given number.
                // Print the total amount of book copies that were added.
                case 12 -> queryService.updateCopiesOfBooksReleasedBeforeDate();
                // Write a program that removes from the database those books,
                // which copies are lower than a given number.
                // Print the number of books that were deleted from the database.
                case 13 -> queryService.deleteAllBooksByCopiesCountLess();
                // Using Workbench (or other similar tool) create a stored procedure,
                // which receives an author's first and last name and
                // returns the total amount of books the author has written.
                // Then write a program that receives author's name and
                // prints the total number of books the author has written
                // by using the stored procedure you've just created.
                case 14 -> queryService.getBookCountByAuthor();
                //No valid task number
                default -> System.out.printf(NOT_VALID_QUERY_NUMBER, queryNumber);

            }

            System.out.print(ENTER_QUERY_NUMBER);
            queryNumber = Integer.parseInt(scanner.nextLine());
        }
    }
}

