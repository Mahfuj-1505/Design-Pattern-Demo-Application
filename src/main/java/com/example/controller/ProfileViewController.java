package com.example.controller;

import com.example.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfileViewController {

    @FXML private Label nameLabel;
    @FXML private Label emailLabel;
    @FXML private Label typeLabel;
    @FXML private Label messageLabel;

    private static final String DB_URL = "jdbc:sqlite:pos.db";

    @FXML
    private void initialize() {
        Integer userId = Session.getLoggedInUserId();

        if (userId == null) {
            showDefaultTemplate();
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT name, email, type FROM users WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nameLabel.setText("Name: " + rs.getString("name"));
                emailLabel.setText("Email: " + rs.getString("email"));
                typeLabel.setText("Type: " + rs.getString("type"));
                messageLabel.setText("");  // Clear message
            } else {
                showDefaultTemplate();
            }

        } catch (Exception e) {
            messageLabel.setText("Error loading profile: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showDefaultTemplate() {
        nameLabel.setText("Name: Guest");
        emailLabel.setText("Email: Not Available");
        typeLabel.setText("Type: Visitor");
        messageLabel.setText("No user is currently logged in.");
    }
}
