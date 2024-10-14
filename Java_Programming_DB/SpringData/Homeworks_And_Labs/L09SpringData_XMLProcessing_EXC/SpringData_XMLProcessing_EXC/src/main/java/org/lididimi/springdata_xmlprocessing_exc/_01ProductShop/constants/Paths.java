package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.constants;

import java.nio.file.Path;

public class Paths {
    public static final Path USER_XML_FILE_PATH =

            Path.of("src", "main", "resources", "files", "users.xml");

    public static final Path CATEGORIES_XML_FILE_PATH =
            Path.of("src", "main", "resources", "files", "categories.xml");

    public static final Path PRODUCTS_XML_FILE_PATH =
            Path.of("src", "main", "resources", "files", "products.xml");

    public static final Path PRODUCTS_IN_RANGE_FILE_PATH =
            Path.of("src", "main", "resources", "exports", "products-in-range.xml");
    public static final Path USER_WITH_SOLD_PRODUCTS_FILE_PATH =
            Path.of("src", "main", "resources", "exports", "users-sold-products.xml");
    public static final Path CATEGORIES_BY_PRODUCT_COUNT_FILE_PATH =
            Path.of("src", "main", "resources", "exports", "categories-by-products.xml");
    public static final Path USERS_AND_PRODUCTS_FILE_PATH =
            Path.of("src", "main", "resources", "exports", "users-and-products.xml");

}
