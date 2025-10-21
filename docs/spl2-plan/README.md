# SPL 2 Plan - Software Project Lab 2

## Project Overview
This document outlines the plan for Software Project Lab 2 (SPL 2) focusing on the Design Pattern Demo Application - a JavaFX-based POS (Point of Sale) system.

## Related Resources

### GitHub Copilot Chat Discussion
For detailed technical discussions and planning session, refer to the GitHub Copilot chat:
- **Chat Link**: https://github.com/copilot/share/480d4234-43c0-8023-a050-d80b844d2820

This chat contains important architectural decisions, design pattern implementations, and development guidance for the project.

## Project Goals
1. Implement various design patterns in a real-world application
2. Build a functional POS system using JavaFX
3. Demonstrate best practices in software architecture
4. Apply MVC architecture principles

## Design Patterns Implemented
- **Observer Pattern**: Stock alert notifications
- **Command Pattern**: Backup and restore operations
- **Strategy Pattern**: Report action strategies (Download, Email, Print)
- **Bridge Pattern**: Report format handling (CSV, PDF)
- **Memento Pattern**: Order state management
- **MVC Pattern**: Overall application architecture

## Technology Stack
- **Frontend**: JavaFX 13 with FXML
- **Backend**: Java 11
- **Database**: SQLite with JDBC
- **Build Tool**: Maven
- **Security**: BCrypt for password hashing
- **PDF Generation**: OpenPDF
- **CSV Processing**: Apache Commons CSV

## Key Features
1. Role-based access control (Admin, Employee)
2. Product management (CRUD operations)
3. Customer and order tracking
4. Sales reporting with multiple formats
5. Stock management with alerts
6. Database backup and restore
7. Secure authentication

## Development Roadmap
- [x] Initial project setup
- [x] Database schema and seeder
- [x] Authentication system
- [x] Product management
- [x] Order processing
- [x] Reporting system with design patterns
- [x] Observer pattern for stock alerts
- [ ] Additional enhancements and optimizations
- [ ] Documentation improvements
- [ ] Testing coverage

## Contributing
Please refer to the chat discussion linked above for architectural decisions and development guidelines.

## References
- GitHub Copilot Chat: https://github.com/copilot/share/480d4234-43c0-8023-a050-d80b844d2820
- Main Repository: https://github.com/Mahfuj-1505/Design-Pattern-Demo-Application
