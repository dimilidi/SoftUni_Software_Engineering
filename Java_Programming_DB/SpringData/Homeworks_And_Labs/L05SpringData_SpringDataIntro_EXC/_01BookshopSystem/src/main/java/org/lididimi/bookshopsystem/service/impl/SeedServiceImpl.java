package org.lididimi.bookshopsystem.service.impl;

import org.lididimi.bookshopsystem.entitty.Author;
import org.lididimi.bookshopsystem.entitty.Book;
import org.lididimi.bookshopsystem.entitty.Category;
import org.lididimi.bookshopsystem.entitty.enums.AgeRestrictionType;
import org.lididimi.bookshopsystem.entitty.enums.BookEditionType;
import org.lididimi.bookshopsystem.service.AuthorService;
import org.lididimi.bookshopsystem.service.BookService;
import org.lididimi.bookshopsystem.service.CategoryService;
import org.lididimi.bookshopsystem.service.SeedService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.lididimi.bookshopsystem.constants.FilePath.*;

@Service
public class SeedServiceImpl implements SeedService {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }


    @Override
    public void seedAuthors() throws IOException {
        if (!authorService.isDataSeeded()) {
            List<Author> authorsList = new ArrayList<>();

            Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                    .stream()
                    .filter(line -> !line.isEmpty())
                    .forEach(line -> {
                        String[] authors = line.split("\\s+");

                        authorsList.add(new Author(authors[0], authors[1]));
                    });

            authorService.seedAuthors(authorsList);
        }
    }

    @Override
    public void seedCategories() throws IOException {
        if(!categoryService.isDataSeeded()) {
            List<Category> categoriesList = new ArrayList<>();

            Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                    .stream()
                    .filter(line -> !line.isEmpty())
                    .forEach(line -> categoriesList.add(new Category(line)));

            categoryService.seedCategories(categoriesList);
        }
    }

    @Override
    public void seedBooks() throws IOException {
        if (!bookService.isDataSeeded()) {
            List<Book> booksList = new ArrayList<>();

            Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                    .stream()
                    .filter(line -> !line.isEmpty())
                    .forEach(line -> {
                        String[] data = line.split("\\s+");

                        Author author = authorService.getRandomAuthor();
                        BookEditionType editionType = BookEditionType.values()[Integer.parseInt(data[0])];
                        LocalDate releaseDate = LocalDate.parse(data[1],
                                DateTimeFormatter.ofPattern("d/M/yyyy"));
                        int copies = Integer.parseInt(data[2]);
                        BigDecimal price = new BigDecimal(data[3]);
                        AgeRestrictionType ageRestriction = AgeRestrictionType
                                .values()[Integer.parseInt(data[4])];
                        String title = Arrays.stream(data)
                                .skip(5)
                                .collect(Collectors.joining(" "));
                        Set<Category> categories = categoryService.getRandomCategories();

                        booksList.add(new Book(title, editionType, price, copies, releaseDate, ageRestriction, author, categories));

                    });
            bookService.seedBooks(booksList);
        }
    }
}
