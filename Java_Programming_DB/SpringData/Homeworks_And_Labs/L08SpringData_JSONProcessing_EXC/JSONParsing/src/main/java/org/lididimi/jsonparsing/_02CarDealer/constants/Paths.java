package org.lididimi.jsonparsing._02CarDealer.constants;

import java.nio.file.Path;

public class Paths {
    public static final Path SUPPLIERS_FILE_PATH =
            Path.of("src", "main", "resources", "files", "suppliers.json");
    public static final Path PARTS_FILE_PATH =
            Path.of("src", "main", "resources", "files", "parts.json");
    public static final Path CARS_FILE_PATH =
            Path.of("src", "main", "resources", "files", "cars.json");
    public static final Path CUSTOMERS_FILE_PATH =
            Path.of("src", "main", "resources", "files", "customers.json");
    public static final Path ORDERED_CUSTOMERS_FILE_PATH =
            Path.of("src", "main", "resources", "exports", "ordered-customers.json");
    public static final Path ALL_TOYOTA_CARS_FILE_PATH =
            Path.of( "src", "main", "resources", "exports", "toyota-cars.json");
    public static final Path LOCAL_SUPPLIERS_FILE_PATH =
            Path.of( "src", "main", "resources", "exports", "local-suppliers.json");
    public static final Path CARS_AND_PARTS_FILE_PATH =
            Path.of( "src", "main", "resources", "exports", "cars-and-parts.json");
    public static final Path CUSTOMERS_TOTAL_SALES_FILE_PATH =
            Path.of( "src", "main", "resources", "exports", "customers-total-sales.json");
    public static final Path SALES_DISCOUNTS_FILE_PATH =
            Path.of( "src", "main", "resources", "exports", "sales-discounts.json");

}
