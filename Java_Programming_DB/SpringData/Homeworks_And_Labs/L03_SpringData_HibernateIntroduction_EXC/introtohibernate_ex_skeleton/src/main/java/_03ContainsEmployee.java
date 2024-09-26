import javax.persistence.EntityManager;
import java.util.Scanner;

/*_03ContainsEmployee
Write a program that checks if a given employee's name is contained in the database.*/

public class _03ContainsEmployee {
    private static final String SELECT_IF_NAME_EXISTS_IN_DB =
            "SELECT COUNT(e) FROM Employee AS e WHERE CONCAT(e.firstName, ' ', e.lastName) = ?1";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        final String fullName = getInput(scanner);
        final boolean exists = isInDataBase(manager, fullName);
        printResult(exists);

        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }

    private static void printResult(boolean exists) {
        System.out.println(exists ? "Yes" : "No");
    }

    private static String getInput(Scanner scanner) {
        System.out.print("Please, enter a name: ");
        final String fullName = scanner.nextLine();
        return fullName;
    }

    private static boolean isInDataBase(EntityManager manager, String fullName) {
        final long count = manager.createQuery(SELECT_IF_NAME_EXISTS_IN_DB, Long.class)
                .setParameter(1, fullName)
                .getSingleResult();
        return count > 0;
    }
}
