package com.example.controller.bridge;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class CSVFormat implements ReportFormat {
    @Override
    public String export(String reportName, String[][] data) throws IOException {
        String filename = reportName + ".csv";
        FileWriter csvWriter = new FileWriter(filename);
        for (String[] row : data) {
            csvWriter.append(String.join(",", row));
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
        return filename;
    }
}