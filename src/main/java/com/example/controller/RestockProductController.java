package com.example.controller;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestockProductController {

    @FXML private ComboBox<String> productComboBox;
    @FXML private TextField restockQuantityField;
    @FXML private Label availableLabel, statusLabel;
    @FXML private Button restockButton;

    private Connection connection;

    @FXML
    public void initialize() {
        connectToDB();
        loadProductNames();

        productComboBox.setEditable(true);
        productComboBox.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            filterProductList(newVal);
        });

        productComboBox.setOnAction(e -> updateAvailableStock());

        restockButton.setOnAction(e -> performRestock());
    }

    private void connectToDB() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:pos.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProductNames() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM products");
            List<String> names = new ArrayList<>();
            while (rs.next()) names.add(rs.getString("name"));
            productComboBox.setItems(FXCollections.observableArrayList(names));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void filterProductList(String input) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT name FROM products WHERE name LIKE ?");
            pstmt.setString(1, input + "%");
            ResultSet rs = pstmt.executeQuery();
            ObservableList<String> filtered = FXCollections.observableArrayList();
            while (rs.next()) filtered.add(rs.getString("name"));
            productComboBox.setItems(filtered);
            productComboBox.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAvailableStock() {
        String productName = productComboBox.getValue();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT id FROM products WHERE name = ?");
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int pid = rs.getInt("id");
                PreparedStatement stockStmt = connection.prepareStatement(
                        "SELECT SUM(amount) AS total FROM stock WHERE product_id = ?");
                stockStmt.setInt(1, pid);
                ResultSet stockRs = stockStmt.executeQuery();
                int available = stockRs.getInt("total");
                availableLabel.setText("Available: " + available);
            } else {
                availableLabel.setText("Available: -");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void performRestock() {
        String productName = productComboBox.getValue();
        String quantityText = restockQuantityField.getText();

        if (productName == null || quantityText == null || quantityText.isBlank()) {
            statusLabel.setText("Please enter all fields.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                statusLabel.setText("Quantity must be positive.");
                return;
            }

            PreparedStatement pstmt = connection.prepareStatement("SELECT id FROM products WHERE name = ?");
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int pid = rs.getInt("id");

                PreparedStatement insert = connection.prepareStatement(
                        "INSERT INTO stock (product_id, product_name, amount, type) VALUES (?, ?, ?, 'restock')");
                insert.setInt(1, pid);
                insert.setString(2, productName);
                insert.setInt(3, quantity);
                insert.executeUpdate();

                statusLabel.setText("Restocked successfully!");
                restockQuantityField.clear();
                updateAvailableStock();
            } else {
                statusLabel.setText("Product not found.");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid quantity.");
        } catch (SQLException e) {
            e.printStackTrace();
            statusLabel.setText("Database error.");
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
