/*_09FindLatest10Projects
Write a program that prints the last 10 started projects.
Print their name, description, start and end date and sort them by name lexicographically.
For the output, check the format from the example.*/

import entities.Project;

import javax.persistence.EntityManager;
import java.util.Comparator;


public class _09FindLatest10Projects {

    public static final String GET_LAST_STARTED_PROJECTS = "FROM Project p ORDER BY p.startDate DESC";

    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        manager.createQuery(GET_LAST_STARTED_PROJECTS, Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream().sorted(Comparator.comparing(Project::getName))
                .forEach(System.out::println);

        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }
}
