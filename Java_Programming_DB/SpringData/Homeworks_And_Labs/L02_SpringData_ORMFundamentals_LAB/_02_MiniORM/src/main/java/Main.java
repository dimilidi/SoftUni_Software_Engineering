import entities.Order;
import entities.Product;
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        MyConnector.createConnection("root", "", "mini_orm");
        Connection connection = MyConnector.getConnection();

        // Handle user operations
        manageUsers(connection);

        // Handle order operations
        manageOrders(connection);

        // Handle product operations
        manageProducts(connection);
    }

    private static void manageUsers(Connection connection) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        EntityManager<User> userEntityManager = new EntityManager<>(connection);

        // Create user table
        userEntityManager.createTable(User.class);

        // Create a new user
        User anna = new User("anna", 26, LocalDate.now());
        anna.setEmail("anna@abv.bg");

        // Alter the table
        userEntityManager.doAlter(anna);

        // Persist the user
        userEntityManager.persist(anna);

        // Retrieve and delete the first user
        User firstUser = userEntityManager.findFirst(User.class);
        if (firstUser != null) {
            userEntityManager.delete(firstUser);
        }

        // Retrieve and display the first user after deletion
        User remainingUser = userEntityManager.findFirst(User.class);
        if (remainingUser != null) {
            System.out.println("Remaining user: " + remainingUser.getUsername());
        }
    }

    private static void manageOrders(Connection connection) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        EntityManager<Order> orderEntityManager = new EntityManager<>(connection);

        // Create order table i
        orderEntityManager.createTable(Order.class);

        // Create a new order and persist it
        Order order = new Order("m324", LocalDate.now());
        orderEntityManager.persist(order);

        System.out.println("Order persisted successfully.");
    }

    private static void manageProducts(Connection connection) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        EntityManager<Product> productEntityManager = new EntityManager<>(connection);

        // Create product table
        productEntityManager.createTable(Product.class);

        // Create and persist a product
        Product pen = new Product("pen", 2.34);
        productEntityManager.persist(pen);

        System.out.println("Product persisted successfully.");
    }
}
