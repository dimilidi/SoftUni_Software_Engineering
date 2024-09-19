import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// _07PrintAllMinionNames:
// Write a program that prints all minion names from the minion’s table in order
// first record, last record, first + 1, last – 1, first + 2, last – 2… first + n, last – n.
public class _07PrintAllMinionNames {
    private static final String GET_ALL_MINIONS = "SELECT name FROM minions";
    private static final String COLUMN_LABEL_NAME = "name";

    public static void main(String[] args) throws SQLException {
        Connection sqlConnection = DBTools.getDefaultConnection();
        PreparedStatement preparedStatement = sqlConnection.prepareStatement(GET_ALL_MINIONS);
        ResultSet resultSet = preparedStatement.executeQuery();


        List<String> minionNames = new ArrayList<>();
        List<String> newMinionsList = new ArrayList<>();

        while (resultSet.next()) {
            minionNames.add(resultSet.getString(COLUMN_LABEL_NAME));
        }

        int left = 0;
        int right = minionNames.size() - 1;

        while (left < right) {
            newMinionsList.add(minionNames.get(left));
            newMinionsList.add(minionNames.get(right));
            left++;
            right--;
        }

        System.out.println(newMinionsList);
    }
}
