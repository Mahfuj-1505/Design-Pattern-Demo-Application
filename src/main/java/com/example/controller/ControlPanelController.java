package com.example.controller;

//package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import com.example.HelloApplication;

public class ControlPanelController {

    private void openScene(String fxmlFile, ActionEvent event, String title) {
        try {
            HelloApplication.setRoot(fxmlFile);
            HelloApplication.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOrderList(ActionEvent event) {
        openScene("order-list", event, "Order List");
    }

    @FXML
    private void handleProductList(ActionEvent event) {
        openScene("product-list", event, "Product List");
    }

    @FXML
    private void handleCustomerList(ActionEvent event) {
        openScene("customer-list", event, "Customer List");
    }

    @FXML
    private void handleAddProduct(ActionEvent event) {
        openScene("add-product", event, "Add Product");
    }
}
