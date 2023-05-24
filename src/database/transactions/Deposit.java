package database.transactions;

import database.DBConnection;

import java.sql.SQLException;

public class Deposit extends DBConnection {
    public boolean isDepositCompleted(int amount) {
        String query = "UPDATE user_balance SET balance = balance + '" + amount +
                "' WHERE user_id = '" + AccountInfo.getInstance().getUserID() + "'";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            AccountInfo.getInstance().setBalance(AccountInfo.getInstance().getBalance() + amount);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
