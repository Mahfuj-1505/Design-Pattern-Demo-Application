package com.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampDecoder {
    public static void main(String[] args) {
        long timestamp = 1692182400000L;
        Date date = new Date(timestamp);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(date);

        System.out.println("Backup created at: " + formattedDate);
    }
}
