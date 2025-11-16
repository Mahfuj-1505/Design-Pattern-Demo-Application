# JavaFX POS Desktop Application with Design Patterns

A desktop-based Point of Sale (POS) system built with **JavaFX**, **SQLite**, and **MVC architecture**, demonstrating the implementation of multiple **Design Patterns** including Singleton, Observer, Memento, and Strategy patterns.

## 📋 Table of Contents

- [Features](#-features)
- [Design Patterns Implemented](#-design-patterns-implemented)
- [Technologies Used](#-technologies-used)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation & Setup](#-installation--setup)
- [Usage](#-usage)
- [Database](#-database)
- [Contributing](#-contributing)

---

## ✨ Features

- 🔐 **Secure Authentication System**
  - Role-based access control (Admin/Employee)
  - Password hashing with BCrypt
  
- 📦 **Product Management**
  - Add new products
  - View product inventory
  - Restock products
  - Low stock alerts (Observer Pattern)

- 🛒 **Sales Management**
  - Create and process orders
  - Add multiple items to orders
  - Order draft saving (Memento Pattern)
  - Customer information tracking

- 📊 **Reporting System**
  - Generate daily sales reports
  - Multiple export formats (CSV, PDF)
  - Strategy Pattern for report actions (Download, Print, Email)

- 👥 **Customer Management**
  - Customer database with contact information
  - Order history tracking

- 💾 **Automated Backup System**
  - Scheduled automatic database backups
  - Manual backup restoration

---

## 🎨 Design Patterns Implemented

### 1. **Singleton Pattern**
- **DatabaseHelper**: Ensures single database connection instance
- **OrderCaretaker**: Manages order draft history
- **BackupManager**: Handles automated backup scheduling

```java
public static DatabaseHelper getInstance() throws SQLException {
    if (instance == null) {
        synchronized (DatabaseHelper.class) {
            if (instance == null) {
                instance = new DatabaseHelper();
            }
        }
    }
    return instance;
}
```

### 2. **Observer Pattern**
- **Stock Alert System**: Notifies when product stock falls below threshold
- Components:
  - `StockSubject`: Interface for stock monitoring
  - `StockObserver`: Interface for observers
  - `StockAlert`: Concrete observer for low stock notifications

```java
public interface StockObserver {
    void update(String productName, int currentStock);
}
```

### 3. **Memento Pattern**
- **Order Draft System**: Save and restore incomplete orders
- Components:
  - `OrderMemento`: Stores order state (items, customer info)
  - `OrderCaretaker`: Manages draft history (up to 10 drafts)

```java
public void saveDraft(OrderMemento draft) {
    if (drafts.size() == MAX_DRAFTS) {
        drafts.removeFirst();
    }
    drafts.addLast(draft);
}
```

### 4. **Strategy Pattern**
- **Report Action System**: Different strategies for handling generated reports
- Strategies:
  - `DownloadStrategy`: Downloads report file
  - `PrintStrategy`: Prints report
  - `EmailStrategy`: Emails report

```java
public interface ReportActionStrategy {
    String execute(String reportFilePath);
}
```

---

## 🛠 Technologies Used

- **Java 17+**
- **JavaFX** - UI framework
- **FXML** - UI layout definitions
- **SQLite** - Embedded database
- **JDBC** - Database connectivity
- **BCrypt** - Password hashing
- **Apache PDFBox** - PDF generation
- **Apache Commons CSV** - CSV file handling
- **Maven** - Build automation

---

## 📂 Project Structure

```
Design-Pattern-Demo-Application/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/
│       │       ├── HelloApplication.java        # Main application entry point
│       │       ├── RegisterController.java      # User registration
│       │       ├── controller/                  # JavaFX controllers
│       │       │   ├── AuthController.java      # Login authentication
│       │       │   ├── BaseDashboardController.java
│       │       │   ├── SellProductController.java
│       │       │   ├── AddProductController.java
│       │       │   ├── RestockProductController.java
│       │       │   └── strategy/                # Strategy Pattern
│       │       │       ├── ReportActionStrategy.java
│       │       │       ├── DownloadStrategy.java
│       │       │       ├── PrintStrategy.java
│       │       │       └── EmailStrategy.java
│       │       ├── model/                       # Data models
│       │       ├── util/                        # Utility classes
│       │       │   ├── DatabaseHelper.java      # Singleton DB manager
│       │       │   ├── DatabaseSeeder.java      # DB initialization
│       │       │   ├── Session.java             # User session
│       │       │   └── BackupManager.java       # Automated backups
│       │       ├── observer/                    # Observer Pattern
│       │       │   ├── StockObserver.java
│       │       │   ├── StockSubject.java
│       │       │   └── StockAlert.java
│       │       └── memento/                     # Memento Pattern
│       │           ├── OrderMemento.java
│       │           └── OrderCaretaker.java
│       └── resources/
│           ├── *.fxml                           # UI layouts
│           └── *.css                            # Stylesheets
├── backups/                                     # Automatic DB backups
├── pos.db                                       # SQLite database
├── pom.xml                                      # Maven configuration
└── README.md
```

---

## 💾 Prerequisites

- **Java Development Kit (JDK) 17+**
- **Maven 3.6+**
- **JavaFX SDK** (handled by Maven)

---

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Mahfuj-1505/Design-Pattern-Demo-Application.git
cd Design-Pattern-Demo-Application
```

### 2. Build with Maven

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn javafx:run
```

Or run the main class directly:
```bash
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -jar target/your-app.jar
```

---

## 📖 Usage

### Login Credentials

For testing purposes:
- **Admin**: 
  - Email: `adm`
  - Password: `12`
  
- **Employee**: 
  - Email: `emp`
  - Password: `12`

### Features by Role

#### Admin Dashboard
- View/Add products
- Restock inventory
- Process sales
- View customers
- Generate reports
- Manage orders

#### Employee Dashboard
- Process sales
- View products
- View stock levels
- View customers

---

## 🗄 Database

The application uses **SQLite** with the following main tables:

- **users** - User accounts with hashed passwords
- **products** - Product inventory
- **customers** - Customer information
- **orders** - Order records
- **order_items** - Order line items

### Automatic Backups

The application automatically creates database backups in the `backups/` directory on startup.

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is created for educational purposes to demonstrate Design Pattern implementation in a real-world application.

---

## 👤 Author

**Mahfuj-1505**

- GitHub: [@Mahfuj-1505](https://github.com/Mahfuj-1505)

---

## 🙏 Acknowledgments

- Design Patterns: Gang of Four (GoF)
- JavaFX Documentation
- SQLite Community

---

## 📌 Future Enhancements

- [ ] Implement Factory Pattern for UI component creation
- [ ] Add Decorator Pattern for enhanced product features
- [ ] Integrate Command Pattern for undo/redo operations
- [ ] Add real email/SMS notifications for stock alerts
- [ ] Implement user role management UI
- [ ] Add data visualization charts
- [ ] Export reports to Excel format
