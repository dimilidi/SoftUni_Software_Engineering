package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.servicies;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

import static org.lididimi.springdata_xmlprocessing_exc._01ProductShop.constants.Constants.ALL_DATA_SEEDED_SUCCESSFULLY;

public interface SeedService {

    String seedUsers() throws IOException, JAXBException;
    String seedCategories() throws IOException, JAXBException;

    String seedProducts() throws IOException, JAXBException;

    default String seedAllData() throws IOException, JAXBException {

        this.seedUsers();
        this.seedCategories();
        this.seedProducts();

        return ALL_DATA_SEEDED_SUCCESSFULLY;
    }
}
