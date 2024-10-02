package org.lididimi.bookshopsystem.service;

import org.lididimi.bookshopsystem.entitty.Author;
import org.lididimi.bookshopsystem.entitty.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookService {

    void seedBooks(List<Book> books);

    boolean isDataSeeded();

    List<Book> findAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);
    List<Book> findBooksByReleaseDateAfter(LocalDate date);




}
