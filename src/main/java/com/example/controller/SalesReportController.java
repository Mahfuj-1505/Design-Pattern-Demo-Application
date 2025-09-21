package com.example.controller;
import com.example.controller.strategy.ReportActionStrategy;

import com.example.controller.strategy.ReportActionStrategy;
import com.example.controller.strategy.DownloadStrategy;
import com.example.controller.strategy.PrintStrategy;
import com.example.controller.strategy.EmailStrategy;


import com.example.controller.bridge.*;
import com.example.controller.strategy.*;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class SalesReportController {

    @FXML private ChoiceBox<String> reportTypeChoice;
    @FXML private ChoiceBox<String> formatChoice;
    @FXML private ChoiceBox<String> actionChoice;
    @FXML private Label outputLabel;

    private ReportActionStrategy actionStrategy;

    @FXML
    public void initialize() {
        reportTypeChoice.getItems().addAll("Daily", "Weekly", "Monthly");
        reportTypeChoice.setValue("Daily");

        formatChoice.getItems().addAll("CSV", "PDF");
        formatChoice.setValue("CSV");

        actionChoice.getItems().addAll("Download", "Print", "Email");
        actionChoice.setValue("Download");
    }

    @FXML
    public void handleGenerateReport() {
        String reportType = reportTypeChoice.getValue();
        String formatType = formatChoice.getValue();
        String actionType = actionChoice.getValue();

        // Select action strategy
        switch (actionType) {
            case "Download": actionStrategy = new DownloadStrategy(); break;
            case "Print": actionStrategy = new PrintStrategy(); break;
            case "Email": actionStrategy = new EmailStrategy(); break;
        }

        // Select format
        ReportFormat format = formatType.equals("PDF") ? new PDFFormat() : new CSVFormat();

        // Select report type
        Report report;
        switch (reportType) {
            case "Weekly": report = new WeeklyReport(format); break;
            case "Monthly": report = new MonthlyReport(format); break;
            default: report = new DailyReport(format);
        }

        // Generate report
        String result = report.generate(reportType + "_Report", actionStrategy);
        outputLabel.setText(result);
    }
}
