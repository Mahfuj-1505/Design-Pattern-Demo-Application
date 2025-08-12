// File: OrderMemento.java
package com.example.memento;

import com.example.controller.SellProductController.OrderItem;
import java.util.ArrayList;
import java.util.List;

public class OrderMemento {
    private final List<OrderItem> orderItems;
    private final String customerName;
    private final String customerPhone;

    public OrderMemento(List<OrderItem> items, String name, String phone) {
        // deep copy to avoid modifying the snapshot later
        this.orderItems = new ArrayList<>(items);
        this.customerName = name;
        this.customerPhone = phone;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }
}
