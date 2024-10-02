package org.lididimi.bookshopsystem._01BookshopSystem.service;

public interface QueryService {
    void getAllBooksByAuthor();

    void getAllAuthorsByNumberOfBooks();

    void getAllAuthorsWithBookBeforeGivenYear();

    void getAllBooksAfterGivenYear();
}
