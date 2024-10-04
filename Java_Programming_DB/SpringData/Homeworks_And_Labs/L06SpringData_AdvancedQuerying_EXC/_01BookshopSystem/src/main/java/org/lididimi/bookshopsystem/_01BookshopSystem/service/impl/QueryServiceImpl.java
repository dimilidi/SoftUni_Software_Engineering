package org.lididimi.bookshopsystem._01BookshopSystem.service.impl;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.enums.BookEditionType;
import org.lididimi.bookshopsystem._01BookshopSystem.service.AuthorService;
import org.lididimi.bookshopsystem._01BookshopSystem.service.BookService;
import org.lididimi.bookshopsystem._01BookshopSystem.service.QueryService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Scanner;

import static org.lididimi.bookshopsystem._01BookshopSystem.constants.Constants.*;

@Service
public class QueryServiceImpl implements QueryService {

    private final AuthorService authorService;
    private final BookService bookService;
    Scanner scanner = new Scanner(System.in);

    public QueryServiceImpl(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void getAllBooksByAgeRestriction() {
        System.out.print(ENTER_AGE_RESTRICTION);
        String agerRestriction = scanner.nextLine();

        bookService.findByAgeRestriction(agerRestriction)
                .forEach(System.out::println);
    }

    @Override
    public void getAllBooksByEditionTypeGoldAndCopies5k() {
        String editionTypeGold = BookEditionType.GOLD.toString();
        final int copies = 5000;
        bookService.findByEditionTypeGoldAndCopies(editionTypeGold, copies)
                .forEach(System.out::println);
    }

    @Override
    public void getAllBooksByPriceRange() {
        BigDecimal lowerPrice = BigDecimal.valueOf(5);
        BigDecimal upperPrice = BigDecimal.valueOf(40);

        bookService.findByPriceLessOrGreaterThan(lowerPrice, upperPrice)
                .forEach(System.out::println);
    }

    @Override
    public void getAllBooksByReleaseYearNot() {
        System.out.print(ENTER_YEAR_OF_BOOK_RELEASE);
        final int year = Integer.parseInt(scanner.nextLine());

        bookService.findByReleaseYearIsNot(year)
                .forEach(System.out::println);
    }

    @Override
    public void getBooksReleasedBeforeDate() {
        System.out.print(ENTER_DATE_IN_FORMAT_1);
        final String date = scanner.nextLine();

        bookService.findByReleaseDateBefore(date)
                .forEach(System.out::println);

    }

    @Override
    public void getAuthorsByFirstNameEndingWith() {
        System.out.print(ENTER_LETTERS);
        final String letters = scanner.nextLine();

        authorService.findAuthorsByFirstNameEnding(letters)
                .forEach(System.out::println);
    }

    @Override
    public void getBooksByLettersInTitle() {
        System.out.print(ENTER_STRING);
        final String input = scanner.nextLine();

        bookService.findByLettersInTitle(input)
                .forEach(System.out::println);
    }

    @Override
    public void getBooksByStartOfAuthorsLastName() {
        System.out.print(ENTER_LETTERS);
        final String letters = scanner.nextLine();

        bookService.findBooksByAuthorLastNameStartsWith(letters)
                .forEach(System.out::println);
    }

    @Override
    public void getBooksCountWithTitleLongerThan() {
        System.out.print(ENTER_BOOK_TITLE_LENGTH);
        final int length = Integer.parseInt(scanner.nextLine());

        final long count = bookService.findCountByTitleLongerThan(length);
        System.out.println(count);
    }

    @Override
    public void getBooksTotalCopiesByAuthor() {
        bookService.findCopiesCountByAuthor()
                .forEach(System.out::println);
    }

    @Override
    public void getTitLeEditionRestrictionPriceOfBook() {
        System.out.print(ENTER_BOOK_TITLE);
        String title = scanner.nextLine();

        final String[] bookInfo = this.bookService.findBookInfoByTitle(title);
        System.out.printf("%s%n", bookInfo[0].replaceAll(",", " "));
    }

    @Override
    public void updateCopiesOfBooksReleasedBeforeDate() {
        System.out.print(ENTER_DATE_IN_FORMAT_2);
        final String date = scanner.nextLine();

        System.out.print(ENTER_NUMBER_OF_COPIES);
        final int copies = Integer.parseInt(scanner.nextLine());

        final int updatedBooks =
                this.bookService.updateCopiesCountWithReleaseDateAfter(copies, date);

        System.out.println(updatedBooks * copies);
    }

    @Override
    public void deleteAllBooksByCopiesCountLess() {
        System.out.print(ENTER_NUMBER_OF_COPIES);
        final int copies = Integer.parseInt(scanner.nextLine());

        int deletedBooksCount = this.bookService.deleteByCopiesCountLess(copies);
        System.out.println(deletedBooksCount);
    }

    @Override
    public void getBookCountByAuthor() {
        System.out.print(ENTER_AUTHOR_NAME);
        final String fullName = scanner.nextLine();
        String[] tokens = fullName.split(" ");
        System.out.println(fullName);
        System.out.println(tokens[0]);

        Integer count = bookService.getBookCountByAuthor(tokens[0], tokens[1]);
        System.out.printf(AUTHOR_NAME_BOOKS_COUNT_FORMAT, fullName, count);
    }

}
