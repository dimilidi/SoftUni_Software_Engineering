import composition.Company;
import composition.PlateNumber;
import inheritance.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory mainFactory = Persistence.createEntityManagerFactory("main");
        EntityManager entityManager = mainFactory.createEntityManager();

        entityManager.getTransaction().begin();

        persistAll(entityManager);

        find(entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    private static void find(EntityManager entityManager) {
        Company foundCompany = entityManager.find(Company.class, 1);

        List<Plane> planes = foundCompany.getPlanes();

        for (Vehicle p : planes) {
            System.out.println(p.getModel());
        }
    }

    private static void persistAll(EntityManager entityManager) {
        PlateNumber plateNumber = new PlateNumber("admafaf");

        Company company = new Company("Airline1");

        Vehicle car = new Car("Corsa", BigDecimal.TEN, "Petrol", 5, plateNumber);
        Vehicle bike = new Bike("BMX", BigDecimal.ONE, "None");
        Vehicle plane = new Plane("Boeing", BigDecimal.TWO, "PlaneFuel", 100, company);
        Vehicle truck = new Truck("Truck", BigDecimal.ONE, "Diesel", 40);

        entityManager.persist(company);
        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);
        entityManager.persist(truck);
    }
}
