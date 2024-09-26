/*_10IncreaseSalaries
Write a program that increases the salaries of all employees,
who are in the Engineering, Tool Design, Marketing, or Information Services departments
by 12%.
Then print the first name, the last name and the salary for the employees,
whose salary was increased.*/

import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public class _10IncreaseSalaries {

    public static final List<String> DEPARTMENTS = Arrays.asList("Engineering", "Tool Design", "Marketing", "Information Services");
    public static final String FIND_EMPLOYEES_BY_DEPARTMENTS = "SELECT e FROM Employee e " +
            "JOIN e.department d " +
            "WHERE d.name IN(:departments)";

    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        List<Employee> employees = findEmployeesByDepartments(manager);
        employees.forEach(Employee::increaseSalary);

        manager.getTransaction().commit();

        reportSalaryIncrease(employees);

        manager.close();
        JpaUtil.closeFactory();

    }

    private static void reportSalaryIncrease(List<Employee> employees) {
        employees.forEach(employee -> System.out.println(employee.report()));
    }

    private static List<Employee> findEmployeesByDepartments(EntityManager manager) {
        return manager.createQuery(FIND_EMPLOYEES_BY_DEPARTMENTS, Employee.class)
                .setParameter("departments", DEPARTMENTS)
                .getResultList();
    }
}
