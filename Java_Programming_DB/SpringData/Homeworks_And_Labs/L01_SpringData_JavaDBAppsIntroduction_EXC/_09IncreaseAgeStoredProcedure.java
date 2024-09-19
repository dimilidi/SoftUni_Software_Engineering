import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _09IncreaseAgeStoredProcedure {
    private static final String CREATE_GET_OLDER_PROCEDURE =
            """
                  CREATE PROCEDURE IF NOT EXISTS usp_get_older(minion_id INT)
                  BEGIN
                  UPDATE minions m SET m.age = m.age + 1 WHERE id = minion_id;
                  END;
                  """;
    private static final String CALL_OLDER_PROCEDURE = "CALL usp_get_older(?);";
    private static final String SELECT_MINION_NAME_AGE =
            "SELECT name, " +
                    "age " +
                    "FROM minions " +
                    "WHERE id = ?;";
    private static final String PRINT_MINION_FORMAT = "%s %d\n";
    private static final String COLUMN_LABEL_NAME = "name";
    private static final String COLUMN_LABEL_AGE = "age";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        final Connection connection = DBTools.getDefaultConnection();

        System.out.print("Enter minion ID: ");
        int minionID = Integer.parseInt(scanner.nextLine());

        // Display minion age before
        displayMinionInfo(connection, minionID, "Minion age before: ");

        // Create and call stored procedure to increase minion's age
        createAndCallOlderProcedure(connection, minionID);

        // Display minion age after
        displayMinionInfo(connection, minionID, "Minion age after: ");
    }

    private static void createAndCallOlderProcedure(Connection connection, int minionID) throws SQLException {
        try (PreparedStatement createGetOlderProcedure = connection.prepareStatement(CREATE_GET_OLDER_PROCEDURE);
             PreparedStatement callGetOlderStatement = connection.prepareStatement(CALL_OLDER_PROCEDURE)) {

            // Create the procedure
            createGetOlderProcedure.execute();

            // Call the procedure to increase minion's age
            callGetOlderStatement.setInt(1, minionID);
            callGetOlderStatement.executeUpdate();
        }
    }

    private static void displayMinionInfo(Connection connection, int minionID, String message) throws SQLException {
        try (PreparedStatement selectMinionStatement = getPreparedStatement(connection, minionID, SELECT_MINION_NAME_AGE);
             ResultSet rs = selectMinionStatement.executeQuery()) {

            if (rs.next()) {
                String name = rs.getString(COLUMN_LABEL_NAME);
                int age = rs.getInt(COLUMN_LABEL_AGE);

                System.out.print(message);
                System.out.printf(PRINT_MINION_FORMAT, name, age);
            }
        }
    }

    private static PreparedStatement getPreparedStatement(Connection connection, int minionID, String query) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, minionID);
        return stmt;
    }
}



