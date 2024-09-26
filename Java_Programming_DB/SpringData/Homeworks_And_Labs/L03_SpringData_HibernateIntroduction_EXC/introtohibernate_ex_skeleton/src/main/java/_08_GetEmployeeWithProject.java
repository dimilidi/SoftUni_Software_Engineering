/*_08_GetEmployeeWithProject
Get an employee by their id.
Print only his/her first name, last name, job title and projects (only their names).
The projects should be ordered by name (ascending).
The output should be printed in the format given in the example.*/

import entities.Employee;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _08_GetEmployeeWithProject {

    public static final String FIND_EMPLOYEE_BY_ID = "FROM Employee e WHERE e.id = :id";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        Integer employeeId = getEmployeeId(reader);
        findEmployeeById(manager, employeeId);

        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }

    private static Integer getEmployeeId(BufferedReader reader) throws IOException {
        System.out.print("Enter employee ID: ");
        return  Integer.parseInt(reader.readLine());
    }

    private static void findEmployeeById(EntityManager manager, Integer employeeId) {
        manager.createQuery(FIND_EMPLOYEE_BY_ID, Employee.class)
                .setParameter("id", employeeId)
                .getResultList()
                .forEach(System.out::println);
    }
}
