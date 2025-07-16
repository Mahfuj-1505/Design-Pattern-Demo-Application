package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class StockListController {

    @FXML
    private TableView<StockItem> stockTable;

    @FXML
    private TableColumn<StockItem, Integer> productIdColumn;

    @FXML
    private TableColumn<StockItem, String> productNameColumn;

    @FXML
    private TableColumn<StockItem, Integer> stockCountColumn;

    @FXML
    private TableColumn<StockItem, String> typeColumn;

    @FXML
    private TableColumn<StockItem, String> timestampColumn;

    private final ObservableList<StockItem> stockData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        stockCountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadStockData();
    }

    private void loadStockData() {
        String url = "jdbc:sqlite:pos.db";
        String query = "SELECT product_id, product_name, amount, type, date FROM stock ORDER BY date DESC";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stockData.clear();
            while (rs.next()) {
                stockData.add(new StockItem(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("amount"),
                        rs.getString("type"),
                        rs.getString("date")
                ));
            }
            stockTable.setItems(stockData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Inner class representing a row in the stock table
    public static class StockItem {
        private final int productId;
        private final String productName;
        private final int amount;
        private final String type;
        private final String date;

        public StockItem(int productId, String productName, int amount, String type, String date) {
            this.productId = productId;
            this.productName = productName;
            this.amount = amount;
            this.type = type;
            this.date = date;
        }

        public int getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getAmount() {
            return amount;
        }

        public String getType() {
            return type;
        }

        public String getDate() {
            return date;
        }
    }
}
