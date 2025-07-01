package com.example.controller;

import com.example.HelloApplication;
import com.example.util.DatabaseHelper;
import com.example.util.NavigationManager;
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
        String email = emailField.getText();
        String password = passwordField.getText();

        Integer userId = DatabaseHelper.verifyUser(email, password);
        System.out.println("User ID: " + userId); // Debug

        if (userId != null) {
            // ✅ Set logged-in user in session
            Session.setLoggedInUserId(userId);

            HelloApplication.setRoot("home-view");
            HelloApplication.setTitle("Home Page");
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    @FXML
    private void handleGoToHomePageButton(ActionEvent event) {
        try {
            HelloApplication.setRoot("home-view");
            HelloApplication.setTitle("Home Page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
