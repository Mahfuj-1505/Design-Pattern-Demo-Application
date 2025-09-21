package com.example.controller.bridge;

import com.example.controller.strategy.ReportActionStrategy;

public abstract class Report {
    protected ReportFormat format;

    public Report(ReportFormat format) {
        this.format = format;
    }

    // Generate the report (returns file path or info)
    public abstract String generate(String reportName, ReportActionStrategy action);
}