package database.transactions;

import database.DBConnection;

import java.sql.SQLException;

public class Settings extends DBConnection {
    public boolean isPhoneNumberUpdated(String newPhone) {
        String query = "UPDATE users SET phone = '" + newPhone + "' WHERE user_id='" + AccountInfo.getInstance().getUserID() + "'";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            AccountInfo.getInstance().setPhone(newPhone);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean isPasswordUpdated(String newPassword) {
        String query = "UPDATE users SET user_password = '" + newPassword + "' WHERE user_id='" + AccountInfo.getInstance().getUserID() + "'";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
