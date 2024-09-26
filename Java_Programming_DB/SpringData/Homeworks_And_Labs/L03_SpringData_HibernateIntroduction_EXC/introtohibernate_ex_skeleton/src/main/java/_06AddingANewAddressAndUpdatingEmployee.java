/*_06AddingANewAddressAndUpdatingEmployee
Create a new address with the text "Vitoshka 15".
Set that address to an employee with a last name, given as input.*/

import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _06AddingANewAddressAndUpdatingEmployee {

    public static final String GET_EMPLOYEE_BY_LASTNAME = "FROM Employee e WHERE e.lastName = :employeeLastName";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        String employeeLastName = getLastName(reader);

        Town sofia = manager.find(Town.class, 32);
        Employee employee = getEmployeeByLastName(manager, employeeLastName);
        Address address = createAddress(sofia, manager);
        updateEmployee(employee, address, manager);

        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }

    private static String getLastName(BufferedReader reader) throws IOException {
        System.out.print("Enter the last name: ");
        return reader.readLine();
    }

    private static Employee getEmployeeByLastName(EntityManager manager, String employeeLastName) {
        return manager.createQuery(GET_EMPLOYEE_BY_LASTNAME, Employee.class)
                .setParameter("employeeLastName", employeeLastName)
                .getResultList().get(0);
    }

    private static void updateEmployee(Employee employee, Address address, EntityManager manager) {
        employee.setAddress(address);
        manager.persist(employee);
    }

    private static Address createAddress(Town town, EntityManager manager) {
        Address address = new Address();
        address.setText("Vitoshka 10");
        address.setTown(town);
        manager.persist(address);
        return address;
    }
}
