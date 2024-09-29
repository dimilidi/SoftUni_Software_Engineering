package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    public static EntityManager getDBConnection(String persistenceUnitName) {
       EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
            return managerFactory.createEntityManager();

    }
}
