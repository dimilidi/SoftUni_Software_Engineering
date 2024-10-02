package org.lididimi.bookshopsystem._01BookshopSystem.repository;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Author;
import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<List<Book>> findBooksByReleaseDateAfter(LocalDate date);

    Optional<List<Book>> findAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
