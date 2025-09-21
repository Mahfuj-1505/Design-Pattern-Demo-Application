package com.example.controller.strategy;

public class PrintStrategy implements ReportActionStrategy {
    @Override
    public String execute(String reportFilePath) {
        return "Report printed: " + reportFilePath;
    }
}