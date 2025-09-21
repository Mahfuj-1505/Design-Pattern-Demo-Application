package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

// ===== Strategy Pattern =====
interface ReportActionStrategy {
    String execute(String reportType, String format);
}

class DownloadStrategy implements ReportActionStrategy {
    @Override
    public String execute(String reportType, String format) {
        return reportType + " Report Downloaded in " + format + " format";
    }
}

class PrintStrategy implements ReportActionStrategy {
    @Override
    public String execute(String reportType, String format) {
        return reportType + " Report Printed in " + format + " format";
    }
}

class EmailStrategy implements ReportActionStrategy {
    @Override
    public String execute(String reportType, String format) {
        return reportType + " Report Emailed in " + format + " format";
    }
}

// ===== Controller =====
public class SalesReportController {

    @FXML
    private ChoiceBox<String> reportTypeChoice;

    @FXML
    private ChoiceBox<String> actionChoice;

    @FXML
    private ChoiceBox<String> formatChoice;

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

        formatChoice.getItems().addAll("CSV", "PDF");
        formatChoice.setValue("CSV");
    }

    @FXML
    public void handleGenerateReport() {
        String reportType = reportTypeChoice.getValue();
        String action = actionChoice.getValue();
        String format = formatChoice.getValue();

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
            String result = strategy.execute(reportType, format);
            outputLabel.setText(result);
        }
    }
}
