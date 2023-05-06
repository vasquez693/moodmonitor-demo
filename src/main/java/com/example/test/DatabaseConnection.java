package com.example.test;

import java.sql.*;

public class DatabaseConnection {
    private static Connection connection;
    private static final String dbURL = "jdbc:mysql://localhost:3306/moodmonitor";
    private static final String dbUser = "root";
    private static final String dbPass = "xxxx";
    public static Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
        }
        return connection;
    }
    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}
