package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class BaseDashboardController {
    @FXML
    protected StackPane contentArea;

    public void handleLogout(ActionEvent actionEvent) {
    }


    @FXML
    public void handleViewProducts(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/product-list.fxml"));
            Node productList = loader.load();

            contentArea.getChildren().setAll(productList);

        } catch (IOException e) {
            System.out.println("❌ Failed to load product-list.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleViewOrders(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/order-list.fxml"));
            Node productList = loader.load();


            contentArea.getChildren().setAll(productList);

        } catch (IOException e) {
            System.out.println("❌ Failed to load order-list.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleViewCustomers(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/customer-list.fxml"));
            Node productList = loader.load();

            contentArea.getChildren().setAll(productList);

        } catch (IOException e) {
            System.out.println("❌ Failed to load customer-list.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleRestock(ActionEvent actionEvent) {
    }

    public void handleSell(ActionEvent actionEvent) {
    }

    public void handleViewStock(ActionEvent actionEvent) {
    }


}
