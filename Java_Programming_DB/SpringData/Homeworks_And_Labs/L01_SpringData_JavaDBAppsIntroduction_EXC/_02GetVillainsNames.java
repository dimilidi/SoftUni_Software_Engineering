import java.sql.*;

// 02. GetVillainsNames:
// Write a program that prints on the console all villains’ names and their number of minions.
// Get only the villains who have more than 15 minions. Order them by a number of minions in descending order.

public class _02GetVillainsNames {

    private static final String GET_VILLAINS_NAMES_AND_MINIONS_COUNT =
            "SELECT v.name, COUNT(DISTINCT mv.minion_id) AS count " +
                    "FROM villains AS v " +
                    "JOIN minions_villains AS mv ON v.id = mv.villain_id " +
                    "GROUP BY v.id " +
                    "HAVING count > ? " +
                    "ORDER BY count DESC;";

    private static final String COLUMN_LABEL_NAME = "name";
    private static final String COLUMN_LABEL_COUNT = "count";
    private static final String PRINT_FORMAT = "%s %d\n";

    public static void main(String[] args) throws SQLException {

        final Connection connection = DBTools.getDefaultConnection();

        final PreparedStatement stmt = connection.prepareStatement(GET_VILLAINS_NAMES_AND_MINIONS_COUNT);

        stmt.setInt(1, 15);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            final String villainName = rs.getString(COLUMN_LABEL_NAME);
            final int minionsCount = rs.getInt(COLUMN_LABEL_COUNT);

            System.out.printf(PRINT_FORMAT, villainName, minionsCount);
        }

        connection.close();
    }
}
