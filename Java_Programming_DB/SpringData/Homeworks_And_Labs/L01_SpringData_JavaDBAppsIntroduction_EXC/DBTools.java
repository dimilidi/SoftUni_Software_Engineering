import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBTools {

    private Connection connection;

    public DBTools(String username, String password, String database) {
        Properties credentials = new Properties();
        credentials.setProperty(Constants.USER_KEY, username);
        credentials.setProperty(Constants.PASSWORD_KEY, password);

        // Create a dynamic JDBC URL by appending the database name
        String dynamicJdbcUrl = Constants.JDBC_URL + database;

        try {
            connection = DriverManager.getConnection(dynamicJdbcUrl, credentials);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create DB connection to database: " + database, e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    // This method still works with the default credentials, but also allows a database to be specified
    public static Connection getConnectionWithDatabase(String database) {
        return new DBTools(Constants.USER_VALUE, Constants.PASSWORD_VALUE, database).getConnection();
    }

    public static Connection getDefaultConnection() {
        return new DBTools(Constants.USER_VALUE, Constants.PASSWORD_VALUE, Constants.DB_NAME).getConnection();
    }
}
