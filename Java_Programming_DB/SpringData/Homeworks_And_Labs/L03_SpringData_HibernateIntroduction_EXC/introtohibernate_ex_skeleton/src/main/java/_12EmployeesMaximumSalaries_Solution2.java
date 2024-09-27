import entities.Department;

import javax.persistence.EntityManager;

public class _12EmployeesMaximumSalaries_Solution2 {
    public static final String GET_DEPARTMENTS = "FROM Department";
    public static final double SECOND_SALARY_THRESHOLD = 70000;
    public static final double  FIRST_SALARY_THRESHOLD = 30000;


    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        manager
                .createQuery(GET_DEPARTMENTS, Department.class)
                .getResultList()
                .forEach(department -> {
                    double departmentMaxSalary = department.getEmployees()
                            .stream()
                            .mapToDouble(e -> e.getSalary().doubleValue())
                            .max()
                            .orElse(0);

                    if(departmentMaxSalary < FIRST_SALARY_THRESHOLD || departmentMaxSalary > SECOND_SALARY_THRESHOLD) {
                        System.out.printf("%s %.2f%n", department.getName(), departmentMaxSalary);
                    }
                });

        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }
}
