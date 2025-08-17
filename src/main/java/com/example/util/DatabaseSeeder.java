package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class DatabaseSeeder {
    public static void main(String[] args) {

        try (
                Connection connection = DatabaseHelper.getInstance().getConnection();
                Statement statement = connection.createStatement();) {

            // Drop existing tables
            statement.executeUpdate("DROP TABLE IF EXISTS order_items;");
            statement.executeUpdate("DROP TABLE IF EXISTS orders;");
            statement.executeUpdate("DROP TABLE IF EXISTS products;");
            statement.executeUpdate("DROP TABLE IF EXISTS users;");
            statement.executeUpdate("DROP TABLE IF EXISTS customers;");
            statement.executeUpdate("DROP TABLE IF EXISTS stock;");

            // Create customers table
            statement.executeUpdate(
                    "CREATE TABLE customers (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL);");

            // Create users table
            statement.executeUpdate(
                    "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL, type VARCHAR(100) NOT NULL, password VARCHAR(255) NOT NULL);");

            // Create products table
            statement.executeUpdate(
                    "CREATE TABLE products (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NOT NULL, price REAL NOT NULL);");

            // Create orders table with foreign key to customers
            statement.executeUpdate(
                    "CREATE TABLE orders (id INTEGER PRIMARY KEY AUTOINCREMENT, customer_id INT NOT NULL, date DATETIME DEFAULT CURRENT_TIMESTAMP, status VARCHAR(50) NOT NULL, FOREIGN KEY (customer_id) REFERENCES customers (id));");

            // Create order_items table with foreign keys to orders and products
            statement.executeUpdate(
                    "CREATE TABLE order_items (id INTEGER PRIMARY KEY AUTOINCREMENT, order_id INT NOT NULL, product_id INT NOT NULL, quantity INT NOT NULL, FOREIGN KEY (order_id) REFERENCES orders (id), FOREIGN KEY (product_id) REFERENCES products (id));");

            // Create stock table to track inventory changes
            statement.executeUpdate(
                    "CREATE TABLE stock (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "product_id INTEGER NOT NULL, " +
                            "product_name VARCHAR(100) NOT NULL, " +
                            "amount INTEGER NOT NULL, " +
                            "type VARCHAR(20) CHECK(type IN ('restock', 'consumed')) NOT NULL, " +
                            "date DATETIME DEFAULT (datetime('now', '+6 hours')), " +
                            "FOREIGN KEY (product_id) REFERENCES products(id)" +
                            ");"
            );


            // Initial stock entries (restock)
            statement.executeUpdate(
                    "INSERT INTO stock (product_id, product_name, amount, type) VALUES " +
                            "(1, 'Product A', 10, 'restock'), " +
                            "(2, 'Product B', 20, 'restock'), " +
                            "(3, 'Product C', 15, 'restock'), " +
                            "(4, 'Product D', 30, 'restock'), " +
                            "(5, 'Product E', 5, 'restock');"
            );


            // Insert data into users (if still needed separately)
            String samplePass = BCrypt.hashpw("12", BCrypt.gensalt());
            statement.executeUpdate(String.format(
                    "INSERT INTO users (id, name, email, type, password) VALUES (1, 'Admin One', 'admin1', 'admin', '12'), " +
                            "(2, 'Employee One', 'employee1', 'employee', '12');",
                    samplePass, samplePass));

            // Insert data into customers
            statement.executeUpdate(
                    "INSERT INTO customers (id, name, email) VALUES (1, 'John Doe', '012345'), (2, 'Jane Smith', '0123123');");

            // Insert data into products table
            statement.executeUpdate(
                    "INSERT INTO products (name, price) VALUES ('Product A', 19.99), ('Product B', 9.99), ('Product C', 29.99), ('Product D', 15.49), ('Product E', 42.00);");

            // Insert data into orders
            statement.executeUpdate(
                    "INSERT INTO orders (customer_id, date, status) VALUES (1, '2023-10-01 10:00:00', 'shipped'), (2, '2023-10-02 11:00:00', 'pending'), (1, '2023-10-03 15:30:00', 'delivered');");

            // Insert data into order_items
            statement.executeUpdate(
                    "INSERT INTO order_items (order_id, product_id, quantity) VALUES (1, 1, 2), (1, 2, 1), (2, 3, 3), (3, 4, 2), (3, 5, 1);");

            System.out.println("Database setup completed successfully.");

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }
    }
}