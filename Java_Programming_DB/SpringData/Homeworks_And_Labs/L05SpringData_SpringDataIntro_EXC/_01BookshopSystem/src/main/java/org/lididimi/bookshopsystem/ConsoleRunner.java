package org.lididimi.bookshopsystem;

import org.lididimi.bookshopsystem.service.QueryService;
import org.lididimi.bookshopsystem.service.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static org.lididimi.bookshopsystem.constants.Constants.ENTER_QUERY_NUMBER;

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
                //1. Get all books after the year 2000. Print only their titles.
                case 1 -> queryService.getAllBooksAfterGivenYear();
                //2. Get all authors with at least one book with a release date before 1990. Print their first name and last name.
                case 2 -> queryService.getAllAuthorsWithBookBeforeGivenYear();
                //3. Get all authors, ordered by the number of their books (descending). Print their first name, last name and book count.
                case 3 -> queryService.getAllAuthorsByNumberOfBooks();
                //4. Get all books from author George Powell, ordered by their release date (descending),
                //then by book title (ascending). Print the book's title, release date and copies.
                case 4 -> queryService.getAllBooksByAuthor();
            }

            System.out.print(ENTER_QUERY_NUMBER);
            queryNumber = Integer.parseInt(scanner.nextLine());
        }
    }
}

