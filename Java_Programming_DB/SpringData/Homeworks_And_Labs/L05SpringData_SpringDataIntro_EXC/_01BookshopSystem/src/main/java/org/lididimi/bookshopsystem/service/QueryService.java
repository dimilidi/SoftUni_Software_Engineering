package org.lididimi.bookshopsystem.service;

public interface QueryService {
    void getAllBooksByAuthor();

    void getAllAuthorsByNumberOfBooks();

    void getAllAuthorsWithBookBeforeGivenYear();

    void getAllBooksAfterGivenYear();
}
