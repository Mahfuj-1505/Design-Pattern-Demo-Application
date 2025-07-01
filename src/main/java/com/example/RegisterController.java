package com.example;

import com.example.util.DatabaseHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField; // email = username
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField ConfirmPasswordField;

    @FXML
    void handleRegisterButton(ActionEvent event) {
        String firstName = firstnameTextField.getText();
        String lastName = lastnameTextField.getText();
        String email = usernameTextField.getText();
        String password = setPasswordField.getText();
        String confirmPassword = ConfirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match!");
            return;
        }

        String fullName = firstName + " " + lastName;
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        boolean success = DatabaseHelper.insertUser(email, hashedPassword, fullName);

        if (success) {
            System.out.println("User registered!");

            try {
                HelloApplication.setRoot("login-view");
                HelloApplication.setTitle("Login Page");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Registration failed. Email may already exist.");
        }
    }

    @FXML
    void handleCloseButton(ActionEvent event) {
        System.exit(0);
    }

    public void handleLoginButton() {
        try {
            HelloApplication.setRoot("login-view");
                HelloApplication.setTitle("Login Page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
