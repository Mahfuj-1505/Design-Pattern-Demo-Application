package com.example.controller;

import com.example.util.DatabaseHelper;
import com.example.memento.OrderCaretaker;
import com.example.memento.OrderMemento;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.*;

public class SellProductController {

    @FXML private ComboBox<String> productComboBox;
    @FXML private TextField quantityField, priceField, totalField;
    @FXML private ComboBox<String> phoneField;
    @FXML private TextField customerNameField;
    @FXML private Label availableQtyLabel;
    @FXML private Button addToOrderButton, sellButton;
    @FXML private TableView<OrderItem> orderTable;
    @FXML private TableColumn<OrderItem, String> colProduct;
    @FXML private TableColumn<OrderItem, Integer> colQuantity;
    @FXML private TableColumn<OrderItem, Double> colPrice;
    @FXML private TableColumn<OrderItem, Double> colTotal;

    private Connection connection;
    private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();

    private final OrderCaretaker caretaker = OrderCaretaker.getInstance();

    @FXML
    public void initialize() {
        connectToDB();
        loadProductNames();
        setupTable();

        restoreDraft();

        productComboBox.setEditable(true);
        productComboBox.getEditor().textProperty().addListener((obs, oldVal, newVal) -> filterProductList(newVal));
        productComboBox.setOnAction(e -> updateProductDetails(productComboBox.getValue()));

        phoneField.setEditable(true);
        phoneField.getEditor().textProperty().addListener((obs, oldVal, newVal) -> searchCustomerByPhone(newVal));
        phoneField.setOnAction(e -> updateCustomerName(phoneField.getValue()));

        quantityField.textProperty().addListener((obs, oldVal, newVal) -> updateTotalField());
        addToOrderButton.setOnAction(e -> {
            addItemToOrder();
            saveDraft();
        });
        sellButton.setOnAction(e -> processOrder());

        orderItems.addListener((ListChangeListener<OrderItem>) change -> saveDraft());
    }

    private void connectToDB() {
        try {
            connection = DatabaseHelper.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupTable() {
        colProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        orderTable.setItems(orderItems);
    }

    private void loadProductNames() {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT name FROM products");
            List<String> names = new ArrayList<>();
            while (rs.next()) names.add(rs.getString("name"));
            productComboBox.setItems(FXCollections.observableArrayList(names));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void filterProductList(String input) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT name FROM products WHERE name LIKE ?");
            pstmt.setString(1, input + "%");
            ResultSet rs = pstmt.executeQuery();
            ObservableList<String> filtered = FXCollections.observableArrayList();
            while (rs.next()) filtered.add(rs.getString("name"));
            productComboBox.setItems(filtered);
            productComboBox.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateProductDetails(String productName) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT id, price FROM products WHERE name = ?");
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                priceField.setText(String.valueOf(rs.getDouble("price")));
                int productId = rs.getInt("id");
                int available = getAvailableStock(productId);
                availableQtyLabel.setText("Available: " + available);
                updateTotalField();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getAvailableStock(int productId) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT SUM(amount) AS total FROM stock WHERE product_id = ?");
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void searchCustomerByPhone(String input) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT email FROM customers WHERE email LIKE ?");
            pstmt.setString(1, input + "%");
            ResultSet rs = pstmt.executeQuery();
            List<String> results = new ArrayList<>();
            while (rs.next()) results.add(rs.getString("email"));
            phoneField.setItems(FXCollections.observableArrayList(results));
            phoneField.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCustomerName(String phone) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT name FROM customers WHERE email = ?");
            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) customerNameField.setText(rs.getString("name"));
            else customerNameField.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTotalField() {
        try {
            int qty = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            totalField.setText(String.valueOf(qty * price));
        } catch (Exception ignored) {
            totalField.clear();
        }
    }

    private void addItemToOrder() {
        String product = productComboBox.getValue();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        double total = price * quantity;
        orderItems.add(new OrderItem(product, quantity, price, total));

        productComboBox.getEditor().clear();
        productComboBox.getSelectionModel().clearSelection();
        priceField.clear();
        quantityField.clear();
        totalField.clear();
        availableQtyLabel.setText("Available: -");
    }

    private void processOrder() {
        String phone = phoneField.getValue();
        String customerName = customerNameField.getText();

        if (orderItems.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Order is empty!");
            return;
        }

        try {
            int customerId = getOrCreateCustomer(customerName, phone);

            PreparedStatement orderStmt = connection.prepareStatement(
                    "INSERT INTO orders (customer_id, status) VALUES (?, 'pending')",
                    Statement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, customerId);
            orderStmt.executeUpdate();
            ResultSet keys = orderStmt.getGeneratedKeys();
            keys.next();
            int orderId = keys.getInt(1);

            for (OrderItem item : orderItems) {
                PreparedStatement prodStmt = connection.prepareStatement("SELECT id FROM products WHERE name = ?");
                prodStmt.setString(1, item.getProductName());
                ResultSet rs = prodStmt.executeQuery();
                if (!rs.next()) continue;
                int productId = rs.getInt("id");

                PreparedStatement itemStmt = connection.prepareStatement(
                        "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)");
                itemStmt.setInt(1, orderId);
                itemStmt.setInt(2, productId);
                itemStmt.setInt(3, item.getQuantity());
                itemStmt.executeUpdate();

                PreparedStatement stockStmt = connection.prepareStatement(
                        "INSERT INTO stock (product_id, product_name, amount, type) VALUES (?, ?, ?, 'consumed')");
                stockStmt.setInt(1, productId);
                stockStmt.setString(2, item.getProductName());
                stockStmt.setInt(3, -item.getQuantity());
                stockStmt.executeUpdate();
            }

            resetPage();
            showAlert(Alert.AlertType.INFORMATION, "Order processed successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getOrCreateCustomer(String name, String phone) throws SQLException {
        PreparedStatement checkStmt = connection.prepareStatement("SELECT id FROM customers WHERE email = ?");
        checkStmt.setString(1, phone);
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next()) return rs.getInt("id");

        PreparedStatement insertStmt = connection.prepareStatement(
                "INSERT INTO customers (name, email) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        insertStmt.setString(1, name);
        insertStmt.setString(2, phone);
        insertStmt.executeUpdate();
        ResultSet keys = insertStmt.getGeneratedKeys();
        if (keys.next()) return keys.getInt(1);
        else throw new SQLException("Customer insert failed");
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void resetPage() {
        orderItems.clear();
        productComboBox.getEditor().clear();
        productComboBox.getSelectionModel().clearSelection();
        quantityField.clear();
        priceField.clear();
        totalField.clear();
        availableQtyLabel.setText("Available: -");
        customerNameField.clear();
        phoneField.getEditor().clear();
        phoneField.getItems().clear();
    }

    private void saveDraft() {
        OrderMemento memento = new OrderMemento(
                new ArrayList<>(orderItems),
                customerNameField.getText(),
                phoneField.getValue()
        );
        caretaker.saveDraft(memento);
    }

    private void restoreDraft() {
        OrderMemento memento = caretaker.getLatestDraft();
        if (memento == null) return;
        resetPage();
        orderItems.addAll(memento.getOrderItems());
        customerNameField.setText(memento.getCustomerName());
        phoneField.setValue(memento.getCustomerPhone());
    }

    public static class OrderItem {
        private final String productName;
        private final int quantity;
        private final double price;
        private final double total;

        public OrderItem(String productName, int quantity, double price, double total) {
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
            this.total = total;
        }

        public String getProductName() { return productName; }
        public int getQuantity() { return quantity; }
        public double getPrice() { return price; }
        public double getTotal() { return total; }
    }
}
