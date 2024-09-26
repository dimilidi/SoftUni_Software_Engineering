/*_12EmployeesMaximumSalaries
Write a program that finds the max salary for each department.
Filter the departments, which max salaries are not in the range between 30000 and 70000.*/

import entities.Department;

import javax.persistence.EntityManager;
import java.util.List;

public class _12EmployeesMaximumSalaries {
    public static void main(String[] args) {

        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        List<Department> departments = manager.createQuery("FROM Department d ", Department.class).getResultList();

        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }
}
