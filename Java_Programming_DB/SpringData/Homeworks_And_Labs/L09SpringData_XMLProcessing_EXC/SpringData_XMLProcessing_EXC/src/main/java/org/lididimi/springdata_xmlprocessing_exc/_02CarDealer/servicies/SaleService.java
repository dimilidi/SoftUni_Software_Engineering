package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies;

import jakarta.xml.bind.JAXBException;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.Sale;

import java.io.IOException;
import java.util.Set;

public interface SaleService {

    boolean isSaleRepositoryNotEmpty();

    void saveAll(Set<Sale> sales);

    String findAllSalesWithInformationAboutCarAndCustomer() throws IOException, JAXBException;
}
