package database.transactions;

import database.DBConnection;
import database.DBDataCenter;
import database.IControlInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin extends DBConnection {

    public boolean isLoginInfoTrue(String accountNumber, String password) {

        String query = "SELECT account_number, user_password FROM users WHERE " +
                "account_number='" + accountNumber + "' AND " +
                "user_password='" + password + "'";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
