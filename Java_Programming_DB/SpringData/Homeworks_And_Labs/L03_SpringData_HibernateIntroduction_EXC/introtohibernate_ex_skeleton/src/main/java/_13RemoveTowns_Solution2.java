import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.NoSuchElementException;

public class _13RemoveTowns_Solution2 {

    public static final String FIND_TOWN = "FROM Town WHERE name = :name";
    public static final String GET_ADDRESSES = "SELECT a FROM Address a JOIN a.town t WHERE t.name = :town";
    public static final String DELETE_ADDRESSES_SUCCESS_MESSAGE_TEMPLATE = "%d addresses in %s deleted.%n";
    public static final String TOWN_INPUT_PROMPT = "Enter a town name: ";
    public static final String TOWN_NOT_FOUND = "No town found.";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        // Step 1: Get town name input from console
        String townName = getTownNameInput(reader);

        // Step 2: Find town in DB if exist
        Town town = findTown(manager, townName);

        // Step 2: Find addresses in the town
        List<Address> addresses = getAddresses(manager, town);

        // Step 3: Set the address field to null for employees in the addresses to be deleted
        addresses.forEach(a -> {
            a.getEmployees().forEach(e -> {
                e.setAddress(null);
                manager.persist(e);
            });
        // Step 4: Delete the addresses
            manager.remove(a);
        });

        // Step 5: Print the number of deleted addresses
        System.out.printf(DELETE_ADDRESSES_SUCCESS_MESSAGE_TEMPLATE, addresses.size(), town.getName());

        // Step 6: Delete the town
        manager.remove(town);

        // Commit the transaction
        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }

    private static String getTownNameInput(BufferedReader reader) throws IOException {
        System.out.print(TOWN_INPUT_PROMPT);
        return reader.readLine();
    }

    private static Town findTown(EntityManager manager, String townName) throws IOException {
        List<Town> resultList = manager.createQuery(FIND_TOWN, Town.class)
                .setParameter("name", townName)
                .getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            throw new NoSuchElementException(TOWN_NOT_FOUND);
        }
    }

    private static List<Address> getAddresses(EntityManager manager, Town town) {
        return manager.createQuery(GET_ADDRESSES, Address.class)
                .setParameter("town", town.getName())
                .getResultList();
    }
}

