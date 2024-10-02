package org.lididimi.bookshopsystem.repository;

import org.lididimi.bookshopsystem.entitty.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);

    Optional<List<Author>> findAllByBooksReleaseDateBefore(LocalDate date);

    /*  @Query(value = "SELECT a, COUNT(b.author.id) AS book_count FROM Author AS a " +
              "JOIN Book b ON a.id = b.author.id " +
              "GROUP BY b.author.id " +
              "ORDER BY COUNT(b.author.id) DESC")*/
      @Query("FROM Author a ORDER BY SIZE(a.books) DESC")
    Optional<List<Author>> findAllAuthorsByNumberOfTheirBooks();
}