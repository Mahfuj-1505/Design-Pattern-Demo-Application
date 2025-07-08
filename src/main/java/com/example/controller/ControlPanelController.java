package com.example.controller;

//package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.event.MouseEvent;
import java.io.IOException;

import com.example.HelloApplication;
import javafx.scene.control.Button;

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

//    @FXML
//    public void hoverGlow(MouseEvent e) {
//        ((Button) e.getSource()).setStyle(((Button) e.getSource()).getStyle() +
//                "; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.5, 0, 4);");
//    }
//
//    @FXML
//    private void removeGlow(MouseEvent e) {
//        ((Button) e.getSource()).setStyle(((Button) e.getSource()).getStyle().replaceAll("-fx-effect: .*?;", ""));
//    }

    @FXML
    public void hoverGlow(javafx.scene.input.MouseEvent e) {
        ((Button) e.getSource()).setStyle(((Button) e.getSource()).getStyle() +
                "; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.5, 0, 4);");
    }

    public void removeGlow(javafx.scene.input.MouseEvent e) {
        ((Button) e.getSource()).setStyle(((Button) e.getSource()).getStyle().replaceAll("-fx-effect: .*?;", ""));
    }
}
