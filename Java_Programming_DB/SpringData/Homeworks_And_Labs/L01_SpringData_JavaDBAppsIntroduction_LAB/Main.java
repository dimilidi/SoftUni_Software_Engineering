import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", "root", "");
             Statement statement = connection.createStatement();
             Statement infoSchema = connection.createStatement();
        ) {

           /*
            Creating a server-side socket for network communication:
            ServerSocket socket = new ServerSocket(6004);   // creates a server socket that listens for incoming connections on port 6004.
            Socket s = socket.accept();   //  waits for a client to connect. When a connection is made, it returns a Socket object representing the connection.
            s.getInputStream();     //  retrieves the input stream of the connected socket to read data sent by the client.
            */


            // execute if mysql driver is not recognised
            // Class.forName(Driver.class.getName());


            // Print all distinct projects
            listProjects(connection);

            Scanner scanner = new Scanner(System.in);

            System.out.println("------------- STATEMENT ------------------------");
            System.out.println("Enter the salary you want to search for:");
            double salary = Double.parseDouble(scanner.nextLine());

            // executeQuery -> returns ResultSet (remember which row it has reached by retrieving data); DB gives us a cursor(pointer)
            // query employee data based on salary
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE salary > " + salary);
            printIdAndJobTitle(resultSet);


            System.out.println("------------- COLUMN NAMES & VALUES ------------------------");
            ResultSet resultSetCol = statement.executeQuery("SELECT * FROM employees WHERE salary > " + salary);

            // Iterate through the result set from the employee query
            while (resultSetCol.next()) {
                // Retrieves and prints the column information from the employees table for the current row.
                getColumns(infoSchema, "employees", resultSetCol);
                System.out.println();
            }


            System.out.println("------------- PREPARED STATEMENT ------------------------");
            System.out.println("------------- (parameterised queries against SQL Injection (string vulnerability)) ------------------------");
            System.out.println("Enter the salary:");
            // 65000 UNION SELECT fullname, description FROM projects
            String salaryString = scanner.nextLine();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE salary > ? OR department_id = ?");
            preparedStatement.setString(1, salaryString);  // set value salaryString for the first ? in the query
            preparedStatement.setInt(2, 3); // set value 3 for the second ? in the query
            ResultSet rSet = preparedStatement.executeQuery();
            printIdAndJobTitle(rSet);


            System.out.println("------------- UPDATE ------------------------");

            PreparedStatement updateStatement1 = connection.prepareStatement("UPDATE employees SET first_name = ? WHERE employee_id = ?");
            PreparedStatement updateStatement2 = connection.prepareStatement("UPDATE employees SET first_name = ? WHERE employee_id = ? OR employee_id = ?");

            updateStatement1.setString(1, "Changed");
            updateStatement1.setLong(2, 1);

            updateStatement2.setString(1, "Star");
            updateStatement2.setLong(2, 2);
            updateStatement2.setLong(3, 3);

            System.out.println("------------- UPDATE execute------------------------");
            // execute() -> returns boolean (true - when there is a result from the query, false - when no result or when it is update count), no cursor
            boolean updateResult1 = updateStatement1.execute();
            System.out.println(updateResult1);

            System.out.println("------------- UPDATE executeUpdate------------------------");
            // executeUpdate() -> returns int (how many rows updated); used by delete, update, insert queries
            int updateResult2 = updateStatement2.executeUpdate();
            System.out.println(updateResult2);

            System.out.println("------------- UPDATE RESULT-----------------------");
            PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM employees LIMIT 5");

            ResultSet empResult = prepStatement.executeQuery();
            while (empResult.next()) {
                System.out.println(empResult.getString("first_name"));
            }


            System.out.println("------------- CALLABLE STATEMENT ------------------------");
            System.out.println("------------- (used for stored procedures) ------------------------");


            System.out.println("------------- TRANSACTION PATTERN------------------------");
            // Isolation levels -> https://dev.mysql.com/doc/refman/8.4/en/innodb-transaction-isolation-levels.html
            try {
                connection.setAutoCommit(false);
                // statement, query ...
                // if no error
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }


            System.out.println("------------- ALTER DB ------------------------");
            Statement alterSTMT = connection.createStatement();
            alterSTMT.execute("ALTER TABLE projects change name fullname varchar(50) not null");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    // Iterate through the result set from the employee query
    // This loop processes each row returned from the employee table and prints selected columns.
    private static void printIdAndJobTitle(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String jobTitle = resultSet.getString(5);
            long id = resultSet.getLong(1);
            System.out.println(id + " | " + jobTitle);
        }
    }

    // Method to list all distinct project names from the 'projects' table
    public static void listProjects(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT fullname FROM projects");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("fullname"));
        }
    }

    // Method to retrieve and print column data for each row in a dynamic table
    public static void getColumns(Statement statement, String table, ResultSet resultSet) throws SQLException {
        String query = "SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = '" + table + "' AND TABLE_SCHEMA = 'soft_uni'";
        ResultSet rs = statement.executeQuery(query);

        // Loop through the columns and print column names with their corresponding values
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            // Retrieve and print the column data for the current row of the result set
            System.out.print(columnName + " : " + resultSet.getString(columnName) + " | ");
        }
    }
}
