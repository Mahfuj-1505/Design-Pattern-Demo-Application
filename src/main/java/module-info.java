module com.example{
    requires javafx.controls;
    requires javafx.fxml;
    
    requires java.sql;
    requires jbcrypt;
    requires java.desktop;

    // Allow JavaFX to use reflection to access your controller class
    opens com.example to javafx.fxml;
    opens com.example.controller to javafx.fxml;
    opens com.example.model to javafx.fxml; // Open your model package for FXML reflection
    opens com.example.util to javafx.fxml;

    exports com.example;
    exports com.example.controller;
    exports com.example.model; // Export your model package
    exports com.example.util; // Export your utility package
}
