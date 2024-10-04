package org.lididimi.bookshopsystem._01BookshopSystem.repository;

import jakarta.transaction.Transactional;
import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Book;
import org.lididimi.bookshopsystem._01BookshopSystem.entitty.enums.AgeRestrictionType;
import org.lididimi.bookshopsystem._01BookshopSystem.entitty.enums.BookEditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findAllByAgeRestriction(AgeRestrictionType ageRestriction);

    Optional<List<Book>> findAllByBookEditionTypeAndCopiesLessThan(BookEditionType bookEditionType,  int copies);

    Optional<List<Book>> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice);

    @Query("SELECT b FROM Book b WHERE YEAR(b.releaseDate) <> :year")
    Optional<List<Book>> findAllByReleaseYearNot(int year);

    Optional<List<Book>> findAllByReleaseDateBefore(LocalDate date);

    Optional<List<Book>> findAllByTitleContainingIgnoreCase(String letters);

    Optional<List<Book>> findAllByAuthorLastNameStartsWithIgnoreCase(String string);

    @Query("SELECT COUNT(b.id) FROM Book b WHERE LENGTH(b.title) > :length")
    Long countAllByTitleLongerThan(int length);

    @Query("SELECT a, SUM(b.copies) AS sum_copies FROM Author AS a JOIN a.books AS b GROUP BY a.id ORDER BY sum_copies DESC")
    Optional<List<Object[]>> findTotalBooksCopiesByAuthor();

    @Query("SELECT b.title, b.bookEditionType, b.ageRestriction, b.price FROM Book b WHERE b.title = :title")
    Optional<String[]> findByTitle(String title);

    @Query("UPDATE Book b SET b.copies = b.copies + :number WHERE b.releaseDate > :date")
    @Modifying
    @Transactional
    Integer updateBooksCopies(int number, LocalDate date);

    @Modifying
    @Transactional
    Integer deleteBooksByCopiesLessThan(int copies);

    @Procedure(procedureName = "GetAuthorBookCount")
    int getAuthorBookCount(String firstName, String lastName);
}
