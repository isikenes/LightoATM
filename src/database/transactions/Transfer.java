package database.transactions;

import database.DBConnection;

import java.sql.SQLException;

public class Transfer extends DBConnection {

    public boolean isTransferCompleted(int amount, String accountNumber) {
        String query = "UPDATE user_balance SET balance = balance - '" + amount +
                "' WHERE user_id = '" + AccountInfo.getInstance().getUserID() + "'";

        String query2 = "UPDATE user_balance SET balance = balance + '" + amount +
                "' WHERE account_number = '" + accountNumber + "'";

        try {
            statement = connection.createStatement();
            if (statement.executeUpdate(query2) == 1) {
                statement.executeUpdate(query);
                AccountInfo.getInstance().setBalance(AccountInfo.getInstance().getBalance() - amount);
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            return false;
        }
    }

}
