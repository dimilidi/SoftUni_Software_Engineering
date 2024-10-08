package org.lididimi.jsonparsing._02CarDealer.servicies;

import org.lididimi.jsonparsing._02CarDealer.entities.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    Customer getCustomerById(Long id);

    boolean isCustomerRepositoryEmpty();

    boolean isCustomerRepositoryNotEmpty();

    void saveAll(List<Customer> customers);

    long getRandomCustomerId();

    String findAllCustomersAndOrderByCriteria() throws IOException;

    String findAllCustomersWithTotalSalesAndMoneySpent() throws IOException;
}
