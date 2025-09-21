package com.example.observer;

public interface StockObserver {
    void update(String productName, int currentStock);
}
