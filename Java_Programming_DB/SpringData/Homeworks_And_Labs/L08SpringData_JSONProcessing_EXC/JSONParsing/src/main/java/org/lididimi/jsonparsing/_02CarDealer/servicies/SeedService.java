package org.lididimi.jsonparsing._02CarDealer.servicies;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.lididimi.jsonparsing._02CarDealer.constants.Constants.DATA_SEEDING_PROCEDURE_FINISHED;

public interface SeedService {

    String seedSuppliers() throws IOException;

    String seedParts() throws IOException;

    String seedCars() throws IOException;

    String seedCustomers() throws IOException;

    String populateSales();

    default String seedAllData() throws IOException {

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
