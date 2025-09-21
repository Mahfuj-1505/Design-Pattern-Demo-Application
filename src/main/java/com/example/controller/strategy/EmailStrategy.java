package com.example.controller.strategy;

public class EmailStrategy implements ReportActionStrategy {
    @Override
    public String execute(String reportFilePath) {
        return "Report emailed: " + reportFilePath;
    }
}