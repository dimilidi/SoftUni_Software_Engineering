package org.lididimi.modelmapper;

import org.lididimi.modelmapper.dtos.EmployeeDTO;
import org.lididimi.modelmapper.models.Address;
import org.lididimi.modelmapper.models.Employee;
import org.lididimi.modelmapper.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class MainRunner implements CommandLineRunner {
    private final EmployeeService employeeService;

    public MainRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {


        ModelMapper modelMapper = new ModelMapper();

        Address address = Address.builder()
                .country("Bulgaria")
                .city("Sofia")
                .build();

        Employee employee = Employee.builder()
                .firstName("John")
                .salary(BigDecimal.TEN)
                .birthday(LocalDate.now())
                .address(address)
                .build();

        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);


        System.out.println(employeeDTO);
    }
}
