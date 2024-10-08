package org.lididimi.jsonparsing._02CarDealer;

import org.lididimi.jsonparsing._02CarDealer.servicies.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static org.lididimi.jsonparsing._02CarDealer.constants.Constants.ENTER_TASK_NUMBER_OR_EXIT;
import static org.lididimi.jsonparsing._02CarDealer.constants.Constants.INVALID_TASK_NUMBER;


@Component
public class ConsoleRunner implements CommandLineRunner {

    private final Scanner scanner;
    private final SeedService seedService;
    private final CustomerService customerService;
    private final CarService carService;
    private final SupplierService supplierService;
    private final SaleService saleService;

    public ConsoleRunner(Scanner scanner, SeedService seedService, CustomerService customerService, CarService carService, SupplierService supplierService, SaleService saleService) {
        this.scanner = scanner;
        this.seedService = seedService;
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        int taskNumber;
        String output;

        System.out.print(ENTER_TASK_NUMBER_OR_EXIT);
        taskNumber = Integer.parseInt(this.scanner.nextLine());

        while (taskNumber != 0) {

            switch (taskNumber) {

                case 1 -> output = this.seedService.seedAllData();

                case 2 -> output = this.customerService.findAllCustomersAndOrderByCriteria();

                case 3 -> output = this.carService.findAllCarsFromMakeToyota();

                case 4 -> output = this.supplierService.findAllLocalSuppliers();

                case 5 -> output = this.carService.findAllCarsAndTheirParts();

                case 6 -> output = this.customerService.findAllCustomersWithTotalSalesAndMoneySpent();

                case 7 -> output = this.saleService.findAllSalesWithInformationAboutCarAndCustomer();

                default -> output = String.format(INVALID_TASK_NUMBER, taskNumber);
            }

            System.out.println(output);

            System.out.print(ENTER_TASK_NUMBER_OR_EXIT);
            taskNumber = Integer.parseInt(this.scanner.nextLine());
        }
    }
}