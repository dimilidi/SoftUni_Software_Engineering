import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// 03. GetMinionsNames:
// Write a program that prints on the console all minion names and their age for a given villain id.
// For the output, use the formats given in the examples.

public class _03GetMinionNames {

    private static final String GET_MINION_NAMES =
            "SELECT DISTINCT m.name, m.age, v.name AS villain_name " +
                    "FROM minions m " +
                    "JOIN minions_villains mv ON m.id = mv.minion_id " +
                    "JOIN villains v ON v.id = mv.villain_id " +
                    "WHERE mv.villain_id = ?;";

    private static final String COLUMN_LABEL_MINION_NAME = "name";
    private static final String COLUMN_LABEL_AGE = "age";
    private static final String COLUMN_LABEL_VILLAIN_NAME = "villain_name";
    private static final String PRINT_FORMAT_MINION = "%d. %s %d\n";
    private static final String PRINT_FORMAT_VILLAIN = "Villain: %s\n";
    private static final String VILLAIN_DOES_NOT_EXISTS = "No villain with ID %s exists in the database.";

    public static void main(String[] args) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        final Connection connection = DBTools.getDefaultConnection();
        final PreparedStatement stmt = connection.prepareStatement(GET_MINION_NAMES);

        System.out.print("Enter villain ID: ");
        int villainID = Integer.parseInt(reader.readLine());
        stmt.setInt(1, villainID);
        ResultSet rs = stmt.executeQuery();

        // Check if there are any results before proceeding
        if (!rs.isBeforeFirst()) {
            System.out.printf(VILLAIN_DOES_NOT_EXISTS, villainID);
            return;
        }

        // Move to the first row
        int counter = 0;

        while (rs.next()) {
            // Print villain name from the first rowUtils.getSQLConnection();
            if (counter == 0) {
                String villainName = rs.getString(COLUMN_LABEL_VILLAIN_NAME);
                System.out.printf(PRINT_FORMAT_VILLAIN, villainName);
            }

            String minionName = rs.getString(COLUMN_LABEL_MINION_NAME);
            int minionAge = rs.getInt(COLUMN_LABEL_AGE);
            System.out.printf(PRINT_FORMAT_MINION, ++counter, minionName, minionAge);
        }

        connection.close();
    }

}
