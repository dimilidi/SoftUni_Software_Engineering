package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies.impl;

import jakarta.xml.bind.JAXBException;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.Customer;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.customer.CustomerInfoOrderedDTO;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.customer.CustomerWithTotalSalesDTO;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.customer.wrappers.CustomerInfoOrderedWrapperDTO;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.customer.wrappers.CustomersWithTotalSalesWrapperDTO;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.repositories.CustomerRepository;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies.CustomerService;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.constants.Constants.CUSTOMERS_TOTAL_SALES_SAVED_SUCCESSFULLY;
import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.constants.Constants.ORDERED_CUSTOMERS_SAVED_SUCCESSFULLY;
import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.constants.Paths.CUSTOMERS_TOTAL_SALES_FILE_PATH;
import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.constants.Paths.ORDERED_CUSTOMERS_FILE_PATH;
import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.utils.Utils.writeXMLIntoFile;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Random random;
    private final ModelMapper mapper;

    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            Random random,
            ModelMapper mapper
            ) {
        this.customerRepository = customerRepository;
        this.random = random;
        this.mapper = mapper;
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public boolean isCustomerRepositoryEmpty() {
        return customerRepository.count() == 0;
    }

    @Override
    public boolean isCustomerRepositoryNotEmpty() {
        return customerRepository.count() > 0;
    }

    @Override
    public void saveAll(List<Customer> customers) {
        customerRepository.saveAllAndFlush(customers);
    }

    @Override
    public long getRandomCustomerId() {
        return Utils.getRandomEntityId(customerRepository, random);
    }

    @Override
    public String findAllCustomersAndOrderByCriteria() throws IOException, JAXBException {

        final List<CustomerInfoOrderedDTO> customers =
                this.customerRepository.findAllByOrderByBirthDateAscIsYoungDriverAsc()
                        .orElseThrow(NoSuchElementException::new)
                        .stream()
                        .map(customer -> this.mapper.map(customer, CustomerInfoOrderedDTO.class))
                        .toList();

        CustomerInfoOrderedWrapperDTO customerWrapperDTO = new CustomerInfoOrderedWrapperDTO(customers);
        writeXMLIntoFile(customerWrapperDTO, ORDERED_CUSTOMERS_FILE_PATH);

        return ORDERED_CUSTOMERS_SAVED_SUCCESSFULLY;
    }

    @Override
    public String findAllCustomersWithTotalSalesAndMoneySpent() throws IOException, JAXBException {
        final List<CustomerWithTotalSalesDTO> allCustomersByTotalSalesCountAndMoneySpent =
                this.customerRepository.findAllBoughtCars()
                        .orElseThrow(NoSuchElementException::new);

        CustomersWithTotalSalesWrapperDTO customersWithTotalSalesWrapperDTO = new CustomersWithTotalSalesWrapperDTO(allCustomersByTotalSalesCountAndMoneySpent);
        writeXMLIntoFile(customersWithTotalSalesWrapperDTO, CUSTOMERS_TOTAL_SALES_FILE_PATH);

        return CUSTOMERS_TOTAL_SALES_SAVED_SUCCESSFULLY;
    }
}
