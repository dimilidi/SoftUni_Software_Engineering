/*_05EmployeesFromDepartment
Extract all employees from the Research and Development department.
Order them by salary (in ascending order), then by id (in ascending order).
Print only their first name, last name, department name and salary.*/

import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class _05EmployeesFromDepartment {

    public static final String DEPARTMENT_NAME = "Research and Development";
    public static final String GET_EMPLOYEES_FROM_DEPARTMENT = "SELECT e FROM Employee e " +
                                                                "WHERE e.department.name = :departmentName " +
                                                                "ORDER BY e.salary, e.id";

    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        List<Employee> employees = getEmployeesFromDepartment(manager, DEPARTMENT_NAME);

        printEmployees(employees);

        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }

    private static void printEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.printf("%s %s from %s - $%s%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getSalary());
        }
    }

    private static List<Employee> getEmployeesFromDepartment(EntityManager manager, String departmentName) {
        return manager.createQuery(GET_EMPLOYEES_FROM_DEPARTMENT, Employee.class)
                .setParameter("departmentName", departmentName)
                .getResultList();
    }
}
