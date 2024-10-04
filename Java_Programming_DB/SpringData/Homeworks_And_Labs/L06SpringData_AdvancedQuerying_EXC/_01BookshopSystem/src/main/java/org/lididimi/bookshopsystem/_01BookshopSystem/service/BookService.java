package org.lididimi.bookshopsystem._01BookshopSystem.service;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    void seedBooks(List<Book> books);

    boolean isDataSeeded();

    List<String> findByAgeRestriction(String restriction);

    List<String> findByEditionTypeGoldAndCopies(String editionType, Integer copies);

    List<String> findByPriceLessOrGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice);

    List<String> findByReleaseYearIsNot(int releaseYear);

    List<String> findByReleaseDateBefore(String date);

    List<String> findByLettersInTitle(String letters);

    List<String> findBooksByAuthorLastNameStartsWith(String letters);

    Long findCountByTitleLongerThan(int length);

    List<String> findCopiesCountByAuthor();

    String[] findBookInfoByTitle(String title);

    Integer updateCopiesCountWithReleaseDateAfter(Integer numberOfCopies, String releaseDate);

    Integer deleteByCopiesCountLess(Integer numberOfCopies);

    Integer getBookCountByAuthor(String firstName, String lastName);


}
