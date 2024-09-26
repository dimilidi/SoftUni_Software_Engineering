import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static final String PERSISTENCE_UNIT_NAME = "jpa_db";
    private static EntityManagerFactory factory;

    // Initialize the EntityManagerFactory
    static {
        try {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial EntityManagerFactory creation failed");
        }
    }

    // Provide the EntityManager
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    // Close the factory when shutting down the application
    public static void closeFactory() {
        if (factory != null) {
            factory.close();
        }
    }
}
