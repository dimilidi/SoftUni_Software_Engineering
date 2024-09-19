import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// _06RemoveVillain:
// Write a program that receives an ID of a villain,
// deletes him from the database and releases his minions from serving him.
// As an output print the name of the villain and the number of minions released.
// Make sure all operations go as planned, otherwise do not make any changes to the database.
// For the output use the format given in the examples.

public class _06RemoveVillain {
    private static final String GET_VILLAIN_NAME_BY_ID = "SELECT name FROM villains WHERE id = ?;";
    private static final String RELEASE_MINIONS = "DELETE FROM minions_villains WHERE villain_id = ?;";
    private static final String DELETE_VILLAIN = "DELETE FROM villains WHERE id = ?;";
    private static final String COUNT_MINIONS_RELEASED = "SELECT COUNT(*) AS count FROM minions_villains WHERE villain_id = ?;";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection connection = DBTools.getDefaultConnection();
        connection.setAutoCommit(false); // Start the transaction manually

        System.out.print("Enter villain ID: ");
        int villainId = Integer.parseInt(scanner.nextLine());

        try {
            // Get the villain's name
            String villainName = getVillainName(connection, villainId);

            if (villainName == null) {
                System.out.println("No such villain was found.");
                return;
            }

            // Release the minions & Count how many minions are released
            int minionsReleased =  releaseMinions(connection, villainId);

            // Delete the villain
            deleteVillain(connection, villainId);

            // If everything is successful, commit the transaction
            connection.commit();

            // Output the result
            System.out.printf("%s was deleted.%n", villainName);
            System.out.printf("%d minions were released.%n", minionsReleased);

        } catch (SQLException e) {
            // If something goes wrong, roll back the transaction
            connection.rollback();
            System.out.println("Transaction failed. No changes were made.");
        } finally {
            connection.setAutoCommit(true); // Reset auto-commit to its default behavior
            connection.close(); // Close the connection
        }
    }

    private static String getVillainName(Connection connection, int villainId) throws SQLException {
        PreparedStatement getVillainStatement = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        getVillainStatement.setInt(1, villainId);

        ResultSet resultSet = getVillainStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("name");
        }

        return null;
    }

    private static int countMinionsReleased(Connection connection, int villainId) throws SQLException {
        PreparedStatement countMinionsStatement = connection.prepareStatement(COUNT_MINIONS_RELEASED);
        countMinionsStatement.setInt(1, villainId);

        ResultSet resultSet = countMinionsStatement.executeQuery();
        resultSet.next(); // There will always be a result, even if count is 0
        return resultSet.getInt("count");
    }

    private static int releaseMinions(Connection connection, int villainId) throws SQLException {
        PreparedStatement releaseMinionsStatement = connection.prepareStatement(RELEASE_MINIONS);
        releaseMinionsStatement.setInt(1, villainId);
        int affectedRows = releaseMinionsStatement.executeUpdate();

        return affectedRows;
    }

    private static void deleteVillain(Connection connection, int villainId) throws SQLException {
        PreparedStatement deleteVillainStatement = connection.prepareStatement(DELETE_VILLAIN);
        deleteVillainStatement.setInt(1, villainId);
        deleteVillainStatement.executeUpdate();
    }
}