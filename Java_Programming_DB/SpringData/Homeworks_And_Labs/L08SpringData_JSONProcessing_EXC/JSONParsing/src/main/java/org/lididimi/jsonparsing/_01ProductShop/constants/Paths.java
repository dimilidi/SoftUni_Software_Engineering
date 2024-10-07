package org.lididimi.jsonparsing._01ProductShop.constants;

import java.nio.file.Path;

public class Paths {
    public static final Path USER_JSON_FILE_PATH =

            Path.of("src", "main", "resources", "files", "users.json");

    public static final Path CATEGORIES_JSON_FILE_PATH =
            Path.of("src", "main", "resources", "files", "categories.json");

    public static final Path PRODUCTS_JSON_FILE_PATH =
            Path.of("src", "main", "resources", "files", "products.json");

    public static final Path PRODUCTS_IN_RANGE_FILE_PATH =
            Path.of("src", "main", "resources", "exports", "products-in-range.json");
    public static final Path USER_WITH_SOLD_PRODUCTS_FILE_PATH =
            Path.of("src", "main", "resources", "exports", "users-sold-products.json");
    public static final Path CATEGORIES_BY_PRODUCT_COUNT_FILE_PATH =
            Path.of("src", "main", "resources", "exports", "categories-by-products.json");
    public static final Path USERS_AND_PRODUCTS_FILE_PATH =
            Path.of("src", "main", "resources", "exports", "users-and-products.json");

}
