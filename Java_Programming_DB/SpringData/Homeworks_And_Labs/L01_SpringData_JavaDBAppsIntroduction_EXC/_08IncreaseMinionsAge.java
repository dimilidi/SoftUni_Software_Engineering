

// _08IncreaseMinionsAge:
// Read from the console minion IDs, separated by space.
// Increment the age of those minions by 1 and make their names titles lower case.
// Finally, print the names and the ages of all minions that are in the database.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _08IncreaseMinionsAge {

    private static String CHANGE_MINION_AGE_AND_NAME = "UPDATE minions SET age = age + 1, name = LOWER(name) WHERE id =?";
    private static String GET_MINIONS = "SELECT name, age FROM minions";
    private static final String PRINT_MINIONS_FORMAT = "%s %d\n";
    private static final String COLUMN_LABEL_NAME = "name";
    private static final String COLUMN_LABEL_AGE = "age";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minion IDs:");
        String[] minionIDs = scanner.nextLine().split("\\s+");

        Connection sqlConnection =DBTools.getDefaultConnection();
        PreparedStatement updateMinionStatement = sqlConnection.prepareStatement(CHANGE_MINION_AGE_AND_NAME);

        for (String id : minionIDs) {
            int minionID = Integer.parseInt(id);
            updateMinionStatement.setInt(1, minionID);
            updateMinionStatement.executeUpdate();
        }

        PreparedStatement selectMinionStatement = sqlConnection.prepareStatement(GET_MINIONS);
        ResultSet resultSet = selectMinionStatement.executeQuery();

        while (resultSet.next()) {
            System.out.printf(
                    PRINT_MINIONS_FORMAT,
                    resultSet.getString(COLUMN_LABEL_NAME),
                    resultSet.getInt(COLUMN_LABEL_AGE));
        }
    }
}
