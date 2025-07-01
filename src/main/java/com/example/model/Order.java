package com.example.model;

public class Order {
    private int id;
    private int customerId;
    private String date;
    private String status;

    public Order(int id, int customerId, String date, String status) {
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.status = status;
    }

    // Getters
    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
}
