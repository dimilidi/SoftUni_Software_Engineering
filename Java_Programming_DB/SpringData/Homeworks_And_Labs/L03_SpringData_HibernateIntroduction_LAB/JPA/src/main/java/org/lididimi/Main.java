package org.lididimi;

import org.lididimi.entities.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("general");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Teacher teacher = new Teacher();
        teacher.setName("Tom");
        em.persist(teacher);

        Teacher fromDB = em.find(Teacher.class, 2);
        System.out.println(fromDB);

        em.getTransaction().commit();
    }
}
