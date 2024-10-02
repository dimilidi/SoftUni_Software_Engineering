package org.lididimi.bookshopsystem._01BookshopSystem.service;

import java.io.IOException;

public interface SeedService {

    void seedAuthors() throws IOException;

    void seedCategories() throws IOException;

    void seedBooks() throws IOException;

    default void seedAllData() throws IOException {

        this.seedAuthors();

        this.seedCategories();

        this.seedBooks();
    }
}