package com.example.util;

import javafx.event.ActionEvent;

import java.io.IOException;

import com.example.HelloApplication;

public class DashboardController {
    public void handle(ActionEvent actionEvent) throws IOException {
        HelloApplication.setRoot("login-view");
        HelloApplication.setTitle("Login Page");
    }

    //

}
