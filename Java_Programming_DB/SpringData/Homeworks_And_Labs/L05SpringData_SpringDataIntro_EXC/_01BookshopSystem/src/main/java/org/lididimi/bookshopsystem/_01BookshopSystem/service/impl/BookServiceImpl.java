package org.lididimi.bookshopsystem._01BookshopSystem.service.impl;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Author;
import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Book;
import org.lididimi.bookshopsystem._01BookshopSystem.repository.BookRepository;
import org.lididimi.bookshopsystem._01BookshopSystem.service.AuthorService;
import org.lididimi.bookshopsystem._01BookshopSystem.service.BookService;
import org.lididimi.bookshopsystem._01BookshopSystem.service.CategoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
