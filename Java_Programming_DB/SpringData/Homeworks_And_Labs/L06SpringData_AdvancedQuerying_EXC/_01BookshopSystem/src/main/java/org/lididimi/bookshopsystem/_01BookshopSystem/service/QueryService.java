package org.lididimi.bookshopsystem._01BookshopSystem.service;

public interface QueryService {

    void getAllBooksByAgeRestriction();

    void getAllBooksByEditionTypeGoldAndCopies5k();

    void getAllBooksByPriceRange();

    void getAllBooksByReleaseYearNot();

    void getBooksReleasedBeforeDate();

    void getAuthorsByFirstNameEndingWith();

    void getBooksByLettersInTitle();

    void getBooksByStartOfAuthorsLastName();

    void getBooksCountWithTitleLongerThan();

    void getBooksTotalCopiesByAuthor();

    void getTitLeEditionRestrictionPriceOfBook();

    void updateCopiesOfBooksReleasedBeforeDate();

    void deleteAllBooksByCopiesCountLess();

    void getBookCountByAuthor();
}
