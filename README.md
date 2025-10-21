# JavaFX POS Desktop Application

A desktop-based Point of Sale (POS) system built with **JavaFX**, **SQLite**, and **MVC architecture**. The application supports:

- Role-based access (admin, employee)
- Product listing and management
- Customer and order tracking

---

## ✨ Features

- 🔐 **Secure Login** using `BCrypt` hashing
- 📦 **Product Management** (add, view products)
- 📋 **Order Tracking** with itemized details
- 👥 **Customer Database** with email storage
- 🧭 **Navigation Stack** to support back-button across views
- 🗃️ **SQLite-backed storage** using JDBC
- 🎨 Built with **JavaFX (FXML-based UI)**

---

## 📂 Project Structure
src/
├── com.example
│ ├── HelloApplication.java # Main launcher
│ ├── controller/ # All JavaFX controllers
│ ├── model/ # Java data models (Product, Order, etc.)
│ ├── util/
│ │ ├── DatabaseHelper.java # SQLite + JDBC logic
│ │ ├── DatabaseSeeder.java # Creates and seeds the DB
│ │ └── NavigationManager.java# Handles view transitions
├── resources/
│ ├── *.fxml # JavaFX view definitions
│ └── *.css (if any)


---

## 💾 Requirements

- Java 17+ (Java 21 or OpenJDK 24 recommended)
- JavaFX SDK (use version **matching your runtime**)
- SQLite JDBC driver (`org.xerial:sqlite-jdbc`)
- JavaFX dependencies (`javafx-controls`, `javafx-fxml`, etc.)
- Maven or IntelliJ configured build system

---

## 📚 Documentation

For detailed project planning, design patterns, and architectural decisions, see:
- [SPL 2 Plan Documentation](./docs/spl2-plan/README.md)
- [GitHub Copilot Chat Reference](./docs/spl2-plan/copilot-chat-reference.md)

---


