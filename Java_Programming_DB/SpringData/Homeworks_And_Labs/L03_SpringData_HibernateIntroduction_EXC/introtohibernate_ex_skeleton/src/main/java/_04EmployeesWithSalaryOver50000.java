
/*_04EmployeesWithSalaryOver50000
Write a program that gets the first name of all employees who have a salary over 50 000.*/

import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class _04EmployeesWithSalaryOver50000 {

    public static final String GET_EMPLOYEES_FIRSTNAME_WITH_SALARY_GREATER_THAN = "FROM Employee WHERE salary > 50000";

    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        manager.createQuery(GET_EMPLOYEES_FIRSTNAME_WITH_SALARY_GREATER_THAN, Employee.class)
                .getResultList()
                .stream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);

        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }

}
