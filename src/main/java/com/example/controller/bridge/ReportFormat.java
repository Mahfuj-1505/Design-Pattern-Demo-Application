package com.example.controller.bridge;

public interface ReportFormat {
    String export(String reportName, String[][] data) throws Exception;
}