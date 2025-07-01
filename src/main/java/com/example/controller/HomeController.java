package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import com.example.HelloApplication;

public class HomeController {

    private void openScene(String fxmlFile, ActionEvent event, String title) {
        try {
            HelloApplication.setRoot(fxmlFile);
            HelloApplication.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleViewProfile(ActionEvent event) {
        System.out.println("View Profile clicked");
        // TODO: Load the profile page
        // Example:
        // loadScene(event, "profile-view", "Profile");
        openScene("profile-view", event, "Profile View");
    }

    @FXML
    private void handleViewProducts(ActionEvent event) {
        openScene("product-list", event, "Product List");
    }

    @FXML
    private void handleOrders(ActionEvent event) {
        System.out.println("My Orders clicked");
        // TODO: Load the orders page
        // loadScene(event, "order-list", "Orders");
    }

    @FXML
    private void handleSettings(ActionEvent event) {
        System.out.println("Settings clicked");
        // TODO: Load the settings page
        // loadScene(event, "settings-view", "Settings");
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        System.out.println("Logout clicked");
        // Redirect to login page
        openScene( "login-view",event, "Login Page");
    }

//    FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view"));
//    Parent root = loader.load();
//    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(new Scene(root));
//                stage.setTitle("Login Page");
//                stage.show();

    @FXML
    private void handleControlPanel(ActionEvent event) throws IOException {
        HelloApplication.setRoot("control-panel");
        HelloApplication.setTitle("Control Panel");
    }


    // Utility method to load a new scene
    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
