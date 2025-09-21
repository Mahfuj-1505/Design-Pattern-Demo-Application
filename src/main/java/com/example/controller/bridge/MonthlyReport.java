package com.example.controller.bridge;

import com.example.controller.strategy.ReportActionStrategy;
import com.example.util.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonthlyReport extends Report {

    public MonthlyReport(ReportFormat format) {
        super(format);
    }

    @Override
    public String generate(String reportName, ReportActionStrategy action) {
        try (Connection conn = DatabaseHelper.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM orders WHERE strftime('%Y-%m', date) = strftime('%Y-%m', 'now')")) {

            String[][] data = extractData(rs);
            String file = format.export(reportName, data);
            return action.execute(file);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating Monthly Report";
        }
    }

    private static String[][] extractData(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        List<String[]> rows = new ArrayList<>();

        while (rs.next()) {
            String[] row = new String[cols];
            for (int c = 0; c < cols; c++) {
                row[c] = rs.getString(c + 1);
            }
            rows.add(row);
        }

        return rows.toArray(new String[0][0]);
    }

}
