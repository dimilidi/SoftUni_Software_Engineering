package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies;

import jakarta.xml.bind.JAXBException;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.constants.Constants.DATA_SEEDING_PROCEDURE_FINISHED;

public interface SeedService {

    String seedSuppliers() throws IOException, JAXBException;

    String seedParts() throws IOException, JAXBException;

    String seedCars() throws IOException, JAXBException;

    String seedCustomers() throws IOException, JAXBException;

    String populateSales();

    default String seedAllData() throws IOException, JAXBException {

        final StringBuilder sb = new StringBuilder();

        sb.append(this.seedSuppliers()).append(System.lineSeparator());
        sb.append(this.seedParts()).append(System.lineSeparator());
        sb.append(this.seedCars()).append(System.lineSeparator());
        sb.append(this.seedCustomers()).append(System.lineSeparator());
        sb.append(this.populateSales()).append(System.lineSeparator());
        sb.append(DATA_SEEDING_PROCEDURE_FINISHED).append(System.lineSeparator());

        return sb.toString().trim();
    }
}
