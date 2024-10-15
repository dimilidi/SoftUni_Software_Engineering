package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies;

import jakarta.xml.bind.JAXBException;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    Customer getCustomerById(Long id);

    boolean isCustomerRepositoryEmpty();

    boolean isCustomerRepositoryNotEmpty();

    void saveAll(List<Customer> customers);

    long getRandomCustomerId();

    String findAllCustomersAndOrderByCriteria() throws IOException, JAXBException;

    String findAllCustomersWithTotalSalesAndMoneySpent() throws IOException, JAXBException;
}
