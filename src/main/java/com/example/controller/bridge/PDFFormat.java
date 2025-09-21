package com.example.controller.bridge;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class PDFFormat implements ReportFormat {

    @Override
    public String export(String reportName, String[][] data) {
        String filePath = reportName + ".pdf";

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            // Load a Unicode font (supports all characters)
//            PDType0Font font = PDType0Font.load(document, new File("src/main/resources/fonts/arial.ttf"));

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
//                contentStream.setFont(font, 12);
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);

                for (String[] row : data) {
                    for (String cell : row) {
                        if (cell == null) cell = "";
                        // Remove tabs and other control characters except line breaks
                        cell = cell.replaceAll("[\\p{Cntrl}&&[^\r\n]]", " ");
                        contentStream.showText(cell + "  "); // double space between columns
                    }
                    contentStream.newLine();
                }

                contentStream.endText();
            }

            document.save(filePath);

        } catch (IOException e) {
            e.printStackTrace();
            return "Error generating PDF";
        }

        return filePath;
    }
}
