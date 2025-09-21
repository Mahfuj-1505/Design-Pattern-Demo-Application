package com.example.observer;

public class StockAlert implements StockObserver {
    @Override
    public void update(String productName, int currentStock) {
        System.out.println("⚠ ALERT: Stock for " + productName + " is low (Current: " + currentStock + ")");
        // Later: integrate email / SMS / UI popup here
    }
}
