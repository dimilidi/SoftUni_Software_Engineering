package org.lididimi.jsonparsing._02CarDealer.servicies;

import org.lididimi.jsonparsing._02CarDealer.entities.Sale;

import java.io.IOException;
import java.util.Set;

public interface SaleService {

    boolean isSaleRepositoryNotEmpty();

    void saveAll(Set<Sale> sales);

    String findAllSalesWithInformationAboutCarAndCustomer() throws IOException;
}
