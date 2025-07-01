package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class HelloApplication extends Application {
    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("register-view.fxml"));
        stage.setTitle("User Registration");
        scene = new Scene(root, 480, 520);
        stage.setScene(scene);
        primaryStage = stage;
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();
        scene.setRoot(root);
    }

    public static void setTitle(String title) {
        primaryStage.setTitle(title);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
