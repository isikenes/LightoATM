package database.transactions;

import database.DBDataCenter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountInfo extends DBDataCenter {
    private static AccountInfo accountInfo;
    private String thisAccountNumber;

    //Singleton
    public static AccountInfo getInstance() {
        if (accountInfo == null) {
            accountInfo = new AccountInfo();
        }
        return accountInfo;
    }

    public void login(String accountNumber) {
        thisAccountNumber = accountNumber;
        getUserInfo();
        getUserBalance();
    }

    public void logout() {
        userID = 0;
        name = null;
        phone = null;
        ssn = null;
        accountNumber = null;
        balance = 0;
    }

    public void getUserInfo() {
        String query = "SELECT * FROM users WHERE account_number='" + thisAccountNumber + "'";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                userID = rs.getInt("user_id");
                name = rs.getString("name");
                ssn = rs.getString("ssn");
                phone = rs.getString("phone");
                securityQuestion = rs.getString("security_question");
                securityAnswer = rs.getString("security_answer");
                password = rs.getString("user_password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void getUserBalance() {
        String query = "SELECT * FROM user_balance WHERE user_id='" + getUserID() + "'";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                balance = rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
