package com.example.observer;

public interface StockSubject {
    void addObserver(StockObserver observer);
    void removeObserver(StockObserver observer);
    void notifyObservers(String productName, int currentStock);
}
