package com.example.controller;

import com.example.util.Sceneloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


import java.io.IOException;

public class BaseDashboardController {
    @FXML
    protected StackPane contentArea;

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        System.out.println("Logout clicked");
        // Redirect to login page
        Sceneloader.openScene( "login-view",event, "Login Page");
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/restock-product.fxml"));
            Node productList = loader.load();

            contentArea.getChildren().setAll(productList);

        } catch (IOException e) {
            System.out.println("❌ Failed to load restock-product.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleSell(ActionEvent actionEvent) {
    }

    public void handleViewStock(ActionEvent actionEvent) {
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
