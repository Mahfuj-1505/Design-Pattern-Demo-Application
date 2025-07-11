package com.example.util;

import com.example.HelloApplication;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Sceneloader {
    public static void openScene(String fxmlFile, ActionEvent event, String title) {
        try {
            HelloApplication.setRoot("/" + fxmlFile);
            HelloApplication.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
