package org.lididimi.bookshopsystem._02UserSystem.services;

import java.io.IOException;

public interface SeedService {

    void seedTowns() throws IOException;

    void seedUsers() throws IOException;

    default void seedAll() throws IOException {

        this.seedTowns();

        this.seedUsers();
    }
}
