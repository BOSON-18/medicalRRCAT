package com.medical.rrcat.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PostgreSQLUtil {
    // Hardcoded Database Credentials
    private static final String URL = "jdbc:postgresql://your-db-host:5432/your-database";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    // Method to get a PostgreSQL connection
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver"); // Load PostgreSQL Driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to PostgreSQL!");
        }
    }

    // Method to fetch all records from 'employees' table
    public static void getAllEmployees() {
        String query = "SELECT * FROM employees"; // Change table name accordingly

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Employee ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Department: " + rs.getString("department"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to close connection (optional, since using try-with-resources)
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection closed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
