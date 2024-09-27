/*_12EmployeesMaximumSalaries
Write a program that finds the max salary for each department.
Filter the departments, which max salaries are not in the range between 30000 and 70000.*/

import entities.Department;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class _12EmployeesMaximumSalaries {

    public static final String GET_DEPARTMENTS_MAX_SALARIES = "SELECT e.department, MAX(e.salary) " +
            "FROM Employee e " +
            "GROUP BY e.department " +
            "HAVING MAX(e.salary) NOT BETWEEN :firstSalaryThreshold AND :secondSalaryThreshold";
    public static final BigDecimal SECOND_SALARY_THRESHOLD = new BigDecimal("70000");
    public static final BigDecimal FIRST_SALARY_THRESHOLD = new BigDecimal("30000");


    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        manager
                .createQuery(GET_DEPARTMENTS_MAX_SALARIES, Object[].class)
                .setParameter("firstSalaryThreshold", FIRST_SALARY_THRESHOLD)
                .setParameter("secondSalaryThreshold", SECOND_SALARY_THRESHOLD)
                .getResultList()
                .forEach(result -> {
                    Department department = (Department) result[0];
                    BigDecimal maxSalary = (BigDecimal) result[1];
                    System.out.printf("%s %s%n", department.getName(), maxSalary);
                });

        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }
}
