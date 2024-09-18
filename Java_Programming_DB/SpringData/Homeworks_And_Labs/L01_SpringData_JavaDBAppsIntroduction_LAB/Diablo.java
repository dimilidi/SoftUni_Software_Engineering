import java.sql.*;
import java.util.Scanner;

public class Diablo {
    public static void main(String[] args) throws SQLException {
        // Connect to DB
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", "root", "");


        // Execute query
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username:");
        String username = scanner.nextLine();

        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT u.first_name, u.last_name, COUNT(ug.game_id) " +
                        " FROM users u " +
                        " JOIN users_games ug ON ug.user_id = u.id " +
                        " WHERE u.user_name = ?" +
                        " GROUP BY u.first_name, u.last_name");
        preparedStatement.setString(1, username);
        ResultSet result = preparedStatement.executeQuery();

        // Print result

        if(result.next()) {
            System.out.printf("User: %s%n%s %s has played %d games",
                    username,
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4));
        } else {
            System.out.println("No such user exists.");
        }
    }
}
