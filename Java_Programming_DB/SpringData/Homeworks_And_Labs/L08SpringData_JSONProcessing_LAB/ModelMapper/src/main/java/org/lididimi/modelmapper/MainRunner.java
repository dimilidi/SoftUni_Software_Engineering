package org.lididimi.modelmapper;

import com.google.gson.Gson;
import org.lididimi.modelmapper.dtos.addresses.CreateAddressDTO;
import org.lididimi.modelmapper.dtos.CompanyDTO;
import org.lididimi.modelmapper.dtos.CreateEmployeeDTO;
import org.lididimi.modelmapper.service.AddressService;
import org.lididimi.modelmapper.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class MainRunner implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final Scanner scanner;
    private final Gson gson;
    private final AddressService addressService;

    public MainRunner(EmployeeService employeeService, Scanner scanner, Gson gson, AddressService addressService) {
        this.employeeService = employeeService;
        this.scanner = scanner;
        this.gson = gson;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create and display company data with employees and addresses in JSON
        createAndDisplayJSONCompanyData();

        // Parse Company from JSON into CompanyDTO
        parseCompany();

        // Convert AddressDTO and EmployeeDTO objects to JSON and print them
        convertAndPrintDTOsToJson();

        // Parse JSON input from the user to Java objects
        parseJsonInput();
    }

    private void parseCompany() {
        CompanyDTO companyDTO = gson.fromJson(scanner.nextLine(), CompanyDTO.class);
        System.out.println(companyDTO);
    }

    private void createAndDisplayJSONCompanyData() {
        CreateAddressDTO address1 = createAddressDTO("Bulgaria", "Sofia");
        CreateAddressDTO address2 = createAddressDTO("Bulgaria", "Varna");
        CreateAddressDTO address3 = createAddressDTO("Bulgaria", "Plovdiv");

        CreateEmployeeDTO employee1 = createEmployeeDTO("First", BigDecimal.TEN, LocalDate.now(), address1);
        CreateEmployeeDTO employee2 = createEmployeeDTO("Second", BigDecimal.TEN, LocalDate.now(), address2);
        CreateEmployeeDTO employee3 = createEmployeeDTO("Third", BigDecimal.TEN, LocalDate.now(), address3);

        // Create company with employees
        CompanyDTO company = new CompanyDTO("Mega", List.of(employee1, employee2, employee3));

        // Print the company JSON
        System.out.println(gson.toJson(company));
    }

    private void convertAndPrintDTOsToJson() {
        CreateAddressDTO address1 = createAddressDTO("Bulgaria", "Sofia");
        CreateEmployeeDTO employee1 = createEmployeeDTO("First", BigDecimal.TEN, LocalDate.now(), address1);
        CreateAddressDTO address2 = createAddressDTO("Bulgaria", "Varna");
        CreateAddressDTO address3 = createAddressDTO("Bulgaria", "Plovdiv");

        // Convert AddressDTO and EmployeeDTO to JSON and print
        System.out.println(gson.toJson(address1));
        System.out.println(gson.toJson(employee1));

        // Print JSON array of AddressDTOs
        List<CreateAddressDTO> addressList = List.of(address1, address2, address3);
        System.out.println(gson.toJson(addressList));
    }


    private void parseJsonInput() {
        // Parse CreateEmployeeDTO from JSON input
        String jsonEmployee = scanner.nextLine();
        CreateEmployeeDTO parsedEmployee = gson.fromJson(jsonEmployee, CreateEmployeeDTO.class);
        System.out.println(parsedEmployee);

        // Parse AddressDTO array from JSON input
        String jsonAddress = scanner.nextLine();
        CreateAddressDTO[] parsedAddressArray = gson.fromJson(jsonAddress, CreateAddressDTO[].class);
        for (CreateAddressDTO address : parsedAddressArray) {
            System.out.println(address);
        }
    }

    private CreateAddressDTO createAddressDTO(String country, String city) {
        return CreateAddressDTO.builder()
                .country(country)
                .city(city)
                .build();
    }

    private CreateEmployeeDTO createEmployeeDTO(String firstName, BigDecimal salary, LocalDate birthday, CreateAddressDTO address) {
        return CreateEmployeeDTO.builder()
                .firstName(firstName)
                .salary(salary)
                .birthday(birthday)
                .address(address)
                .build();
    }
}