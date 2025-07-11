package com.example.util;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:pos.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static boolean insertUser(String email, String hashedPassword, String name) {
        String sql = "INSERT INTO users (name, email, type, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name); // full name
            stmt.setString(2, email);
            stmt.setString(3, "admin"); // default type
            stmt.setString(4, hashedPassword);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifies login credentials.
     * @return user ID if login is successful, otherwise null.
     */
    public static String verifyUser(String email, String plainPassword) {
        String sql = "SELECT id, password, type FROM users WHERE email = ?";
        System.out.println("Verifying user: " + email); // Debug: Show email being checked

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            System.out.println("Executing SQL: " + sql + " with email: " + email); // Debug: Show query and parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                String userType = rs.getString("type"); // Get type here
                System.out.println("User found. Stored Hash: " + storedHash + ", Stored Type: " + userType); // Debug: Show retrieved data

                if (BCrypt.checkpw(plainPassword, storedHash)) {
                    System.out.println("Password matched! Returning user type: " + userType); // Debug: Success
                    return userType;
                } else {
                    System.out.println("Password did not match."); // Debug: Password mismatch
                }
            } else {
                System.out.println("No user found with email: " + email); // Debug: No user found
            }
        } catch (SQLException e) {
            System.err.println("Login error (SQLException): " + e.getMessage()); // Use err for errors
            e.printStackTrace(); // Print full stack trace for detailed error
        }
        System.out.println("Login failed. Returning null."); // Debug: Failed login
        return null;  // Login failed
    }
}
