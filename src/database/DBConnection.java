package database;

import java.sql.*;

public class DBConnection {

    protected Connection connection = null;
    protected Statement statement = null;
    protected PreparedStatement preparedStatement = null;

    public DBConnection() {
        String host = "localhost";
        String port = "3306";
        String dbName = "lightoatm";
        String user = "root";
        String password = "12345678";

        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected!");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
