import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/*_02ChangeCasing
Persist all towns from the database. Detach those whose name length is more than 5 symbols.
Then transform the names of all attached towns to uppercase and save them to the database.*/

public class _02ChangeCasing {
    private static final String ALL_TOWNS = "FROM Town";
    private static final int NAME_LENGTH = 5;

    public static void main(String[] args) {

        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        // Persist all towns from the database.
        List<Town> allTowns = manager.createQuery(ALL_TOWNS, Town.class).getResultList();

        // Detach those whose name length is more than 5 symbols.
        detachTownsWithNameLengthGreaterThan(allTowns, NAME_LENGTH,  manager);

        // Transform the names of all attached towns to uppercase and save them to the database.
        updateNameToUpperCase(allTowns);

        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }

    private static void updateNameToUpperCase(List<Town> towns) {
        for (Town town : towns) {
            town.setName(town.getName().toUpperCase());
        }
    }

    private static void detachTownsWithNameLengthGreaterThan(List<Town> townsToDetach, int townNameLength, EntityManager manager) {
        for (Town town : townsToDetach) {
            if(town.getName().length() > townNameLength) {
                manager.detach(town);
            }
        }
    }
}
