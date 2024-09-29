package service;

import entities._02Sales.Customer;
import entities._02Sales.Product;
import entities._02Sales.Sale;
import entities._02Sales.StoreLocation;
import jakarta.persistence.EntityManager;
import utils.JpaUtil;

import java.math.BigDecimal;

import static constants.Constants.*;
import static enums.PersistenceUnitName.SALES;

public class SalesService {
    private static SalesService instance;

    private SalesService() {
    }

    public static SalesService getInstance() {
        if (instance == null) {
            instance = new SalesService();
        }
        return instance;
    }

    public void executeTaskTwo() {
        EntityManager manager = JpaUtil.getDBConnection(SALES.getPersistenceUnitName());
        manager.getTransaction().begin();

        //Bonus code to populate the DB
        populateDB(manager);

        manager.getTransaction().commit();
    }

    private void populateDB(EntityManager manager) {
        Product product = createProduct(PRODUCT_ONE, QUANTITY, PRICE);
        Customer customer = createCustomer(CUSTOMER_ONE, EMAIL, CARD_NUMBER);
        StoreLocation storeLocation = createStoreLocation(LOCATION_NAME);
        Sale saleOne = createSale(product, customer, storeLocation);
        Sale saleTwo = createSale(product, customer, storeLocation);

        manager.persist(product);
        manager.persist(customer);
        manager.persist(storeLocation);
        manager.persist(saleOne);
        manager.persist(saleTwo);
    }

    private Product createProduct(String productName, double quantity, BigDecimal price) {
        return new Product(productName, quantity, price);
    }

    private Customer createCustomer(String name, String email, String cardNumber) {
        return new Customer(name, email, cardNumber);
    }

    private StoreLocation createStoreLocation(String locationName) {
        return new StoreLocation(locationName);
    }

    private Sale createSale(Product product, Customer customer, StoreLocation storeLocation) {
        return new Sale(product, customer, storeLocation);
    }
}
