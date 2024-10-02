package org.lididimi.bookshopsystem.service.impl;

import org.lididimi.bookshopsystem.entitty.Author;
import org.lididimi.bookshopsystem.entitty.Book;
import org.lididimi.bookshopsystem.entitty.Category;
import org.lididimi.bookshopsystem.entitty.enums.AgeRestrictionType;
import org.lididimi.bookshopsystem.entitty.enums.BookEditionType;
import org.lididimi.bookshopsystem.repository.BookRepository;
import org.lididimi.bookshopsystem.service.AuthorService;
import org.lididimi.bookshopsystem.service.BookService;
import org.lididimi.bookshopsystem.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<Book> findBooksByReleaseDateAfter(LocalDate date) {

        return bookRepository.findBooksByReleaseDateAfter(date)
                .orElseThrow(NoSuchElementException::new);
    }


    @Override
    public List<Book> findAllByAuthorOrderByReleaseDateDescTitleAsc(Author author) {

        return this.bookRepository.findAllByAuthorOrderByReleaseDateDescTitleAsc(author)
                .orElseThrow(NoSuchElementException::new);
    }

}
