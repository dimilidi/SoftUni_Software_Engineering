import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// _05ChangeTownNamesCasing
// Write a program that changes all town names to uppercase for a given country.
// Print the number of towns that were changed in the format provided in the examples.
// On the next line print the names that were changed, separated by a coma and a space.

public class _05ChangeTownNamesCasing {
    private static final String UPDATE_TOWNS_UPPERCASE = "UPDATE towns SET name = UPPER(name) WHERE country=?";
    private static final String GET_TOWNS_BY_COUNTRY = "SELECT name FROM towns WHERE country=?";

    public static void main(String[] args) throws SQLException {
        Connection sqlConnection = DBTools.getDefaultConnection();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a country:");
        String countryName = scanner.nextLine();

        // Update towns and retrieve the number of affected towns
        int updatedCount = updateTownNamesToUppercase(sqlConnection, countryName);

        // Get the list of updated towns
        List<String> updatedTowns = getUpdatedTownsByCountry(sqlConnection, countryName);

        // Print the result
        printResult(updatedCount, updatedTowns);

        sqlConnection.close();
    }


    private static int updateTownNamesToUppercase(Connection connection, String countryName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOWNS_UPPERCASE);
        preparedStatement.setString(1, countryName);
        int updatedCount = preparedStatement.executeUpdate();
        preparedStatement.close();
        return updatedCount;
    }

    private static List<String> getUpdatedTownsByCountry(Connection connection, String countryName) throws SQLException {
        PreparedStatement getTowns = connection.prepareStatement(GET_TOWNS_BY_COUNTRY);
        getTowns.setString(1, countryName);
        ResultSet resultSet = getTowns.executeQuery();

        List<String> updatedTowns = new ArrayList<>();
        while (resultSet.next()) {
            updatedTowns.add(resultSet.getString(1));
        }

        getTowns.close();
        return updatedTowns;
    }

    private static void printResult(int updatedCount, List<String> updatedTowns) {
        if (updatedCount > 0) {
            System.out.printf("%d town names were affected.%n", updatedCount);
            System.out.println(updatedTowns);
        } else {
            System.out.println("No town names were affected.");
        }
    }
}