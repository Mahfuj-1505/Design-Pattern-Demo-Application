package com.example.controller.strategy;

public class DownloadStrategy implements ReportActionStrategy {
    @Override
    public String execute(String reportFilePath) {
        return "Report downloaded: " + reportFilePath;
    }
}