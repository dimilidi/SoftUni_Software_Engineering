package org.lididimi.bookshopsystem._01BookshopSystem.service;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Author;
import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    void seedBooks(List<Book> books);

    boolean isDataSeeded();

    List<Book> findAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);

    List<Book> findBooksByReleaseDateAfter(LocalDate date);
}
