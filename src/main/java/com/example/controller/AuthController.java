package com.example.controller;

import com.example.HelloApplication;
import com.example.util.DatabaseHelper;
import com.example.util.NavigationManager;
import com.example.util.Sceneloader;
import com.example.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.*;

import java.io.IOException;

public class AuthController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleBack(ActionEvent event) {
        NavigationManager.goBack();
    }


    @FXML
    private void handleLoginButton(ActionEvent event) throws IOException {
        String email = emailField.getText().trim();     // Add .trim()
        String password = passwordField.getText().trim();

        String userType = DatabaseHelper.verifyUser(email, password);
        System.out.println("User type: " + userType); // Debug

        if (userType != null) {
            // ✅ Set logged-in user in session
//            Session.setLoggedInUserId(userId);
            String fxmlFileName = userType + "-dashboard";
            Sceneloader.openScene(fxmlFileName, event, "Admin Dashboard");

        } else {
            System.out.println("Invalid email or password.");
        }
    }

    @FXML
    private void handleGoToDashboardButton(ActionEvent event) {
        Sceneloader.openScene("employee-dashboard", event, "employee Dashboard");
    }
}
