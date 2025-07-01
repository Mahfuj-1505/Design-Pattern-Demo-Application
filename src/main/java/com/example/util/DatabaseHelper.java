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
            stmt.setString(3, "employee"); // default type
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
    public static Integer verifyUser(String email, String plainPassword) {
        String sql = "SELECT id, password FROM users WHERE email = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (BCrypt.checkpw(plainPassword, storedHash)) {
                    return rs.getInt("id");  // ✅ return user ID
                }
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;  // ❌ Login failed
    }
}
