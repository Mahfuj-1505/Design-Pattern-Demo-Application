package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

public class AddProductController {

    @FXML private TextField nameField;
    @FXML private TextField priceField;
    @FXML private Label statusLabel;

    private static final String DB_URL = "jdbc:sqlite:pos.db";

    @FXML
    private void handleAddProduct() {
        String name = nameField.getText();
        String priceText = priceField.getText();

        if (name.isEmpty() || priceText.isEmpty()) {
            statusLabel.setText("Please fill all fields.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);

            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setDouble(2, price);
                stmt.executeUpdate();
                statusLabel.setText("Product added successfully!");
                nameField.clear();
                priceField.clear();
            }

        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid price format.");
        } catch (SQLException e) {
            statusLabel.setText("Database error.");
            e.printStackTrace();
        }
    }

    @FXML
    public void hoverGlow(javafx.scene.input.MouseEvent e) {
        Button btn = (Button) e.getSource();
        // Add drop shadow effect on hover
        btn.setStyle(btn.getStyle() + "; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.5, 0, 2);");
        // Scale up button
        btn.setScaleX(1.1);
        btn.setScaleY(1.1);
    }

    @FXML
    public void removeGlow(javafx.scene.input.MouseEvent e) {
        Button btn = (Button) e.getSource();
        // Remove drop shadow effect from style
        btn.setStyle(btn.getStyle().replaceAll("-fx-effect: .*?;", ""));
        // Reset scale
        btn.setScaleX(1);
        btn.setScaleY(1);
    }
}
