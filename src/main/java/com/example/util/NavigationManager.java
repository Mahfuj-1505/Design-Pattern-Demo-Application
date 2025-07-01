package com.example.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class NavigationManager {
    private static Stage stage;
    private static final Stack<String> history = new Stack<>();

    public static void setStage(Stage s) {
        stage = s;
    }

    public static void goTo(String fxml) {
        try {
            if (stage.getScene() != null && stage.getScene().getRoot().getId() != null) {
                history.push(stage.getScene().getRoot().getId());
            }

            FXMLLoader loader = new FXMLLoader(NavigationManager.class.getResource("/com/example/" + fxml + ".fxml"));
            Parent root = loader.load();
            root.setId(fxml); // remember id for back navigation
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void goBack() {
        if (!history.isEmpty()) {
            String prev = history.pop();
            goTo(prev);
        }
    }

    public static void resetHistory() {
        history.clear();
    }
}
