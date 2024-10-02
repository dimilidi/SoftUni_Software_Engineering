package org.lididimi.bookshopsystem._01BookshopSystem.service.impl;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Author;
import org.lididimi.bookshopsystem._01BookshopSystem.service.AuthorService;
import org.lididimi.bookshopsystem._01BookshopSystem.service.BookService;
import org.lididimi.bookshopsystem._01BookshopSystem.service.QueryService;
import org.springframework.stereotype.Service;

import static org.lididimi.bookshopsystem._01BookshopSystem.constants.Constants.*;

@Service
public class QueryServiceImpl implements QueryService {

    private final AuthorService authorService;
    private final BookService bookService;

    public QueryServiceImpl(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void getAllBooksByAuthor() {
        final String firstName = FULL_NAME.split("\\s+")[0];
        final String lastName = FULL_NAME.split("\\s+")[1];
        final Author author = authorService.findAuthorByFirstNameAndLastName(firstName, lastName);

        bookService.findAllByAuthorOrderByReleaseDateDescTitleAsc(author)
                .forEach(e -> System.out.printf("Title: %s, Release date: %s, Copies: %d\n", e.getTitle(), e.getReleaseDate(), e.getCopies()));
    }

    @Override
    public void getAllAuthorsByNumberOfBooks() {
        authorService.findAllAuthorsByNumberOfTheirBooks()
                .forEach(e -> System.out.printf("%s %s - %d%n", e.getFirstName(), e.getLastName(), e.getBooks().size()));
    }

    @Override
    public void getAllAuthorsWithBookBeforeGivenYear() {
        authorService.findAllByBooksReleaseDateBefore(BOOK_BEFORE_YEAR)
                .forEach(e -> System.out.printf("%s %s\n", e.getFirstName(), e.getLastName()));
    }

    @Override
    public void getAllBooksAfterGivenYear() {
        this.bookService.findBooksByReleaseDateAfter(BOOK_AFTER_YEAR)
                .forEach(e -> System.out.printf("Title: %s\n", e.getTitle()));
    }

}
