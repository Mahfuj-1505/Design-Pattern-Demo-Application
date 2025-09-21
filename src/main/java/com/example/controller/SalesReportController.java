package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

// ===== Strategy Pattern =====
interface ReportActionStrategy {
    String execute(String reportType);
}

class DownloadStrategy implements ReportActionStrategy {
    @Override
    public String execute(String reportType) {
        return reportType + " Report Downloaded";
    }
}

class PrintStrategy implements ReportActionStrategy {
    @Override
    public String execute(String reportType) {
        return reportType + " Report Printed";
    }
}

class EmailStrategy implements ReportActionStrategy {
    @Override
    public String execute(String reportType) {
        return reportType + " Report Emailed";
    }
}

// ===== Controller =====
public class SalesReportController {

    @FXML
    private ChoiceBox<String> reportTypeChoice;

    @FXML
    private ChoiceBox<String> actionChoice;

    @FXML
    private Label outputLabel;

    private ReportActionStrategy strategy;

    @FXML
    public void initialize() {
        // Populate choices
        reportTypeChoice.getItems().addAll("Daily", "Weekly", "Monthly");
        reportTypeChoice.setValue("Daily");

        actionChoice.getItems().addAll("Download", "Print", "Email");
        actionChoice.setValue("Download");
    }

    @FXML
    public void handleGenerateReport() {
        String reportType = reportTypeChoice.getValue();
        String action = actionChoice.getValue();

        // Select strategy
        switch (action) {
            case "Download":
                strategy = new DownloadStrategy();
                break;
            case "Print":
                strategy = new PrintStrategy();
                break;
            case "Email":
                strategy = new EmailStrategy();
                break;
        }

        // Execute strategy
        if (strategy != null) {
            String result = strategy.execute(reportType);
            outputLabel.setText(result);
        }
    }
}
