package org.lididimi.springdata_xmlprocessing_exc._01ProductShop;

import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.servicies.CategoryService;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.servicies.ProductService;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.servicies.SeedService;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.servicies.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

import static org.lididimi.springdata_xmlprocessing_exc._01ProductShop.constants.Constants.*;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final Scanner scanner;

    public ConsoleRunner(
            SeedService seedService,
            ProductService productService,
            UserService userService,
            CategoryService categoryService,
            Scanner scanner
    ) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) throws Exception {
        int taskNumber;
        String output;

        System.out.print(ENTER_TASK_NUMBER);
        taskNumber = Integer.parseInt(this.scanner.nextLine());

        while (taskNumber != 0) {

            switch (taskNumber) {

                case 1 -> output = this.seedService.seedAllData();

                case 2 -> {
                    System.out.print(ENTER_MIN_PRICE_RANGE);
                    final BigDecimal minRangePrice = BigDecimal.valueOf(Double.parseDouble(this.scanner.nextLine()));

                    System.out.print(ENTER_MAX_PRICE_RANGE);
                    final BigDecimal maxRangePrice = BigDecimal.valueOf(Double.parseDouble(this.scanner.nextLine()));

                    output = this.productService.findAllProductsInPriceRange(minRangePrice, maxRangePrice);
                }

                case 3 -> output = this.userService.findAllUsersWithSoldProductsToAtLeastOneBuyer();

                case 4 -> output = this.categoryService.getCategoriesByProductSummary();

                case 5 -> output = this.userService.findUsersWithSoldProductsAndCount();

                default -> output = String.format(TASK_NUMBER_DOES_NOT_EXISTS, taskNumber);
            }

            System.out.println(output);

            System.out.print(ENTER_TASK_NUMBER);
            taskNumber = Integer.parseInt(this.scanner.nextLine());
        }
    }
}