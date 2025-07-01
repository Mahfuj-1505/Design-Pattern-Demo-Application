package com.example.model;

import javafx.beans.property.*;

public class Product {

    private final IntegerProperty id;
    private final StringProperty name;
    private final DoubleProperty price;

    public Product(int id, String name, double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public Product(String name, String category, double price, int quantity) {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id.get() +
                ", name='" + name.get() + '\'' +
                ", price=" + price.get() +
                '}';
    }

    // Getters for properties (e.g., getId(), getName())
    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }

    // Property getters (e.g., idProperty(), nameProperty()) for TableView
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

}
