package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.customer.CustomerImportDTO;
import exam.model.entity.Customer;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import exam.util.validation.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CustomerServiceImpl implements CustomerService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/customers.json");
    public static final String INVALID_CUSTOMER = "Invalid Customer";
    public static final String SUCCESSFULLY_IMPORTED_CUSTOMER = "Successfully imported Customer %s %s - %s ";
    private final StringBuilder sb;
    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            @Qualifier("gson") Gson gson,
            ValidationUtils validationUtils,
            ModelMapper modelMapper,
            TownRepository townRepository
    ) {
        this.sb = new StringBuilder();
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importCustomers() throws IOException {
        CustomerImportDTO[] customerImportDTOs = gson.fromJson(readCustomersFileContent(), CustomerImportDTO[].class);
        for (CustomerImportDTO dto : customerImportDTOs) {
            if(validationUtils.isValid(dto) ||
                    customerRepository.findByEmail(dto.getEmail()).isPresent()
            ) {
                appendInvalidMessage();
                continue;
            }

            Customer customer = mapToCustomer(dto);
            customerRepository.save(customer);
            appendSuccessfullyImportedMessage(customer);
        }
        return sb.toString().trim();
    }

    private Customer mapToCustomer(CustomerImportDTO dto) {
        Customer customer = modelMapper.map(dto, Customer.class);
        townRepository.findByName(dto.getTown().getName()).ifPresent(customer::setTown);
        return customer;
    }

    private void appendInvalidMessage() {
        sb.append(INVALID_CUSTOMER).append(System.lineSeparator());
    }

    private void appendSuccessfullyImportedMessage(Customer customer) {
        sb.append(String.format(SUCCESSFULLY_IMPORTED_CUSTOMER,
                customer.getFirstName(), customer.getLastName(), customer.getEmail()))
                .append(System.lineSeparator());
    }
}
