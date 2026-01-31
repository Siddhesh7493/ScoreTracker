package com.cricket.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;	

/**
 * Purpose: Creates and manages JDBC connection.
 * Used everywhere in the DAO layer.
 */
public class DBConnection {
    
    
    private static final String URL = "jdbc:postgresql://localhost:5432/cricket_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0000";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
            
            System.out.println("Connection established successfully!");
            
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found. Add the PostgreSQL JAR to your build path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        return connection;
    }
}