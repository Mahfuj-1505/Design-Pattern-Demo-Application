package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:pos.db";
    private static DatabaseHelper instance;
    private Connection connection;

    private DatabaseHelper() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(DB_URL);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to database: " + e.getMessage());
        }
    }

    public static DatabaseHelper getInstance() throws SQLException {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null) {
                    instance = new DatabaseHelper();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }


    public boolean insertUser(String email, String plainPassword, String name) {
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        String sql = "INSERT INTO users (name, email, type, password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, "admin");
            stmt.setString(4, hashedPassword);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }


    public String verifyUser(String email, String plainPassword) {
        String sql = "SELECT id, password, type FROM users WHERE email = ?";
        System.out.println("Verifying user: " + email);

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                String userType = rs.getString("type");

                if (BCrypt.checkpw(plainPassword, storedHash)) {
                    System.out.println("Password matched. User type: " + userType);
                    return userType;
                } else {
                    System.out.println("Password did not match.");
                }
            } else {
                System.out.println("No user found with email: " + email);
            }
        } catch (SQLException e) {
            System.err.println("Login error: " + e.getMessage());
        }
        return null;
    }
}
