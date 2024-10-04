package org.lididimi.bookshopsystem._01BookshopSystem.service.impl;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Author;
import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Book;
import org.lididimi.bookshopsystem._01BookshopSystem.entitty.enums.AgeRestrictionType;
import org.lididimi.bookshopsystem._01BookshopSystem.entitty.enums.BookEditionType;
import org.lididimi.bookshopsystem._01BookshopSystem.repository.BookRepository;
import org.lididimi.bookshopsystem._01BookshopSystem.service.AuthorService;
import org.lididimi.bookshopsystem._01BookshopSystem.service.BookService;
import org.lididimi.bookshopsystem._01BookshopSystem.service.CategoryService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

import static org.lididimi.bookshopsystem._01BookshopSystem.constants.Constants.*;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAllAndFlush(books);
    }

    @Override
    public boolean isDataSeeded() {
        return this.bookRepository.count() > 0;
    }


    @Override
    public List<String> findByAgeRestriction(String restriction) {
        AgeRestrictionType ageRestrictionType = AgeRestrictionType.valueOf(restriction.toUpperCase());
        return bookRepository.findAllByAgeRestriction(ageRestrictionType)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(Book::getTitle)
                .toList();
    }

    @Override
    public List<String> findByEditionTypeGoldAndCopies(String editionType, Integer copies) {
        return bookRepository.findAllByBookEditionTypeAndCopiesLessThan(BookEditionType.valueOf(editionType), copies)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(Book::getTitle)
                .toList();
    }

    @Override
    public List<String> findByPriceLessOrGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice) {
        return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lowerPrice, higherPrice)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(b -> String.format(BOOK_TITLE_PRICE_FORMAT, b.getTitle(), b.getPrice()))
                .toList();
    }

    @Override
    public List<String> findByReleaseYearIsNot(int releaseYear) {
        return bookRepository.findAllByReleaseYearNot(releaseYear)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(Book::getTitle)
                .toList();
    }

    @Override
    public List<String> findByReleaseDateBefore(String date) {
        return bookRepository.findAllByReleaseDateBefore(getLocalDate(date, "-"))
                .orElseGet(Collections::emptyList)
                .stream()
                .map(b -> String.format(BOOK_TITLE_EDITION_PRICE_FORMAT,
                        b.getTitle(),
                        b.getBookEditionType().toString(),
                        b.getPrice()))
                .toList();
    }

    @Override
    public List<String> findByLettersInTitle(String letters) {
        return bookRepository.findAllByTitleContainingIgnoreCase(letters)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(b -> String.format(b.getTitle()))
                .toList();
    }

    @Override
    public List<String> findBooksByAuthorLastNameStartsWith(String letters) {
        return bookRepository.findAllByAuthorLastNameStartsWithIgnoreCase(letters)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(b -> String.format(BOOK_TITLE_AUTHOR_NAME_FORMAT,
                        b.getTitle(),
                        b.getAuthor().getFirstName(),
                        b.getAuthor().getLastName()))
                .toList();
    }

    @Override
    public Long findCountByTitleLongerThan(int length) {
        return bookRepository.countAllByTitleLongerThan(length);
    }

    @Override
    public List<String> findCopiesCountByAuthor() {
       return bookRepository.findTotalBooksCopiesByAuthor()
                .orElseGet(Collections::emptyList)
                .stream()
                .map(o -> String.format(AUTHOR_NAME_COUNT_FORMAT,
                        ((Author) o[0]).getFirstName(),
                        ((Author) o[0]).getLastName(),
                        (Long) o[1]))
                .toList();
    }

    @Override
    public String[] findBookInfoByTitle(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Integer updateCopiesCountWithReleaseDateAfter(Integer number, String releaseDate) {
        return bookRepository.updateBooksCopies(number, getFormatedDate(releaseDate, "-"));
    }

    @Override
    public Integer deleteByCopiesCountLess(Integer numberOfCopies) {
       return bookRepository.deleteBooksByCopiesLessThan(numberOfCopies);
    }

    @Override
    public Integer getBookCountByAuthor(String firstName, String lastName) {
        return bookRepository.getAuthorBookCount(firstName, lastName);
    }

    private LocalDate getLocalDate(String date, String delimiter) {
        int day = Integer.parseInt(getSplit(date, delimiter)[0]);
        int month = Integer.parseInt(getSplit(date, delimiter)[1]);
        int year = Integer.parseInt(getSplit(date, delimiter)[2]);
        return  LocalDate.of(year, month, day);
    }

    private LocalDate getFormatedDate(String date, String delimiter) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MMM-yyyy")
                .toFormatter(Locale.ENGLISH);

        date = date.trim().replaceAll(" ", delimiter);

        return LocalDate.parse(date, formatter);
    }

    private static String[] getSplit(String date, String delimiter) {
        return date.split(delimiter);
    }

}
