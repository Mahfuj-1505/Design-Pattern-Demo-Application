package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class AdminDashboardController extends BaseDashboardController{


    public void handleAddProduct(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/add-product.fxml"));
            Node productList = loader.load();

            contentArea.getChildren().setAll(productList);

        } catch (IOException e) {
            System.out.println("❌ Failed to load add-product.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }





    public void handleSalesReport(ActionEvent actionEvent) {
    }

    public void handleManageEmployees(ActionEvent actionEvent) {

    }



}
