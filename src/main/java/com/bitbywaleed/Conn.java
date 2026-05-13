package com.bitbywaleed;

import java.sql.*;

/**
 * Database Connection Manager
 * Package: com.bitbywaleed
 */
public class Conn {
    private Connection c;
    private Statement s;
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "atm_simulator";
    private static final String USER = "root";
    private static final String PASSWORD = "@MysqlWaleed"; // Change this to your password

    public Conn() throws SQLException, ClassNotFoundException {
        connect();
    }

    private void connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection(URL + DB_NAME + "?useSSL=false&allowPublicKeyRetrieval=true", USER, PASSWORD);
        s = c.createStatement();
        System.out.println("Database connected successfully!");
    }

    public Connection getConnection() {

        return c;
    }

    public Statement getStatement() {
        return s;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return c.prepareStatement(sql);
    }

    public void close() {
        try {
            if (s != null) s.close();
            if (c != null) c.close();
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}