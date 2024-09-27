/*
_11FindEmployeesByFirstName
Write a program that finds all employees,
whose first name starts with a pattern given as input from the console.
Print their first and last names, their job title and salary.
*/

import entities.Employee;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11FindEmployeesByFirstName {

    public static final String FIND_USER_BY_FIRSTNAME_PATTERN = "FROM Employee e WHERE e.firstName LIKE CONCAT(:pattern, '%')";

    public static void main(String[] args) throws IOException {
        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String patternInput = getPatternInput(reader);
        getEmployees(manager, patternInput);

        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }

    private static String getPatternInput(BufferedReader reader) throws IOException {
        System.out.print("Enter the first name pattern : ");
        return reader.readLine();
    }

    private static void getEmployees(EntityManager manager, String patternInput) {
         manager.createQuery(FIND_USER_BY_FIRSTNAME_PATTERN, Employee.class)
                .setParameter("pattern", patternInput)
                .getResultList()
                .forEach(e -> System.out.println(e.report(true)));;
    }
}
