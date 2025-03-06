package com.medical.rrcat.service.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDBUtil {

    private Database db = new Database();
    private final String url = db.getPostURI();
    private final String user = db.getPostuser();
    private final String password = db.getPassword();

    public Connection connect() {
        Connection conn = null;
        try {
            // Explicitly load the PostgreSQL JDBC Driver for Java 1.6
            Class.forName("org.postgresql.Driver");

            // Connect to the PostgreSQL database
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found! " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Connection failed! " + e.getMessage());
        }

        return conn;
    }
}
