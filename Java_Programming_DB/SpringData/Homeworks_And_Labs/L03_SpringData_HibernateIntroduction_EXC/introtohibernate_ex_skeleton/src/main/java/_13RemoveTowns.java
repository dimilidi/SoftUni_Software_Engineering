/*_13RemoveTowns
Write a program that deletes a town, which name is given as an input.
The program should delete all addresses that are in the given town.
Print on the console the number of addresses that were deleted.
Check the example for the output format.*/

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _13RemoveTowns {

    public static final String UPDATE_EMPLOYEE_ADDRESS = "UPDATE Employee e " +
            "SET e.address = null WHERE e.address IN " +
            "(SELECT a FROM Address a WHERE a.town.name = :name)";
    public static final String DELETE_TOWN = "DELETE FROM Town t WHERE t.name = :name";
    public static final String DELETE_ADDRESSES = "DELETE FROM Address a " +
            "WHERE a.town IN (SELECT t FROM Town t WHERE t.name = :name)";
    public static final String CHECK_TOWN_EXISTS = "SELECT COUNT(t) FROM Town t WHERE t.name = :name";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter a town name: ");
        String townName = reader.readLine();

        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        // Step 1: Check if the town exists
        Long townCount = getTownCount(manager, townName);

        if (townCount == 0) {
            System.out.printf("Town %s does not exist.%n", townName);
            manager.getTransaction().rollback(); // Rollback the transaction
            manager.close();
            JpaUtil.closeFactory();
            return;
        }

        // Step 2: Set the address field to null for employees in the addresses to be deleted
        updateEmployeeAddressByTownDeletion(manager, townName);

        // Step 3: Delete the addresses
        int deletedAddressesCount = deleteAddresses(manager, townName);

        // Step 4: Delete the town
        deleteTown(manager, townName);

        // Step 5: Print the number of deleted addresses
        System.out.printf("%d addresses in %s were deleted%n", deletedAddressesCount, townName);
        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }

    private static Long getTownCount(EntityManager manager, String townName) {
        return (Long) manager.createQuery(CHECK_TOWN_EXISTS)
                .setParameter("name", townName)
                .getSingleResult();
    }

    private static void deleteTown(EntityManager manager, String townName) {
        manager.createQuery(DELETE_TOWN)
                .setParameter("name", townName)
                .executeUpdate();
    }

    private static int deleteAddresses(EntityManager manager, String townName) {
        return manager.createQuery(DELETE_ADDRESSES)
                .setParameter("name", townName)
                .executeUpdate();
    }

    private static void updateEmployeeAddressByTownDeletion(EntityManager manager, String townName) {
        manager.createQuery(UPDATE_EMPLOYEE_ADDRESS)
                .setParameter("name", townName)
                .executeUpdate();
    }
}
