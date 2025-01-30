package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    private ConnectionPool() {}

    public static final Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try { 
                String url = "jdbc:postgresql://autorack.proxy.rlwy.net:37498/railway";
                String user = "postgres";
                String password = "oCnBTMclvJvMUZCAmwwVTDAOJANeaiza";
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static Connection connection;
}