package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import com.example.backup.BackupManager; // ✅ added

public class HelloApplication extends Application {
    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        //  start auto backup
        BackupManager.getInstance().scheduleAutomaticBackup();

//        to restore
//        BackupManager.getInstance().restoreFrom("backups/backup_1692182400000.db");

        Parent root = FXMLLoader.load(getClass().getResource("/login-view.fxml"));
        stage.setTitle("User Registration");
        scene = new Scene(root, 1920, 1080);
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
