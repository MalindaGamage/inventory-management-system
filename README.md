# Inventory Management System

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![SQLite](https://img.shields.io/badge/SQLite-3.42-green)
![Swing](https://img.shields.io/badge/Java_Swing-GUI-orange)

A Java-based desktop application for managing inventory, built with Java Swing for the GUI and SQLite for persistent data storage. Developed as part of the IT 240 Advanced Programming with Java I course.

## Features

- 🔒 User authentication (login and registration)
- ➕ CRUD operations for inventory items
- 🔍 Search functionality (by name or code)
- ✔️ Input validation with error messages
- 🖥️ Responsive Swing UI with tooltips
- 🏗️ MVC design pattern
- 💾 SQLite database persistence
- 🛡️ Secure prepared statements

## Screenshots

*(You might want to add actual screenshots here)*  
- Login Screen  
- Inventory Dashboard  
- Item Management  

## Prerequisites

- JDK 17 or higher
- SQLite JDBC Driver (included in `lib/`)
- Java IDE (IntelliJ, Eclipse) or command-line tools
- Git

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/MalindaGamage/inventory-management-system.git
   cd inventory-management-system
   ```

2. Ensure the SQLite JDBC driver is in place:
    - Download [sqlite-jdbc-3.42.0.jar](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.42.0.0)
    - Place it in the `lib/` directory

## IDE Setup

### IntelliJ IDEA
1. File > Project Structure > Libraries > "+" > Java
2. Select `lib/sqlite-jdbc-3.42.0.jar`

### Eclipse
1. Right-click project > Build Path > Configure Build Path
2. Add External JARs > Select `lib/sqlite-jdbc-3.42.0.jar`

## Running the Application

### From IDE
Run `src/InventoryManagementSystem.java`

### Command Line
```bash
# Compile
javac -cp .:lib/sqlite-jdbc-3.42.0.jar src/*.java

# Run
java -cp .:lib/sqlite-jdbc-3.42.0.jar src/InventoryManagementSystem
```

## Project Structure

```
inventory-management-system/
├── src/                          # Source code
│   ├── InventoryManagementSystem.java  # Main class
│   ├── DatabaseConnection.java   # DB logic
│   ├── Item.java                 # Model
│   ├── ItemController.java       # Controller
│   ├── LoginView.java           # View
│   └── InventoryView.java        # View
├── lib/                          # Dependencies
│   └── sqlite-jdbc-3.42.0.jar
├── docs/                         # Documentation
│   └── Documentation.md
├── inventory.db                  # Database (auto-generated)
└── README.md                     # This file
```

## Usage

1. Launch the application
2. Register a new user or login
3. Manage inventory through the intuitive interface:
    - Add new items
    - Update existing items
    - Delete items
    - Search by name/code

## Notes

- The database (`inventory.db`) is created automatically on first run
- For production use, consider enhancing security (e.g., password hashing)
- Detailed documentation available in `docs/`

## Submission

- **GitHub Repository**: [inventory-management-system](https://github.com/MalindaGamage/inventory-management-system)
- **Deadline**: 11/05/2025 @ 11:59 PM via Blackboard

## License

*(You might want to add a license here)*  
This project is licensed under the MIT License.
```

Key improvements:
1. Added badges for visual appeal
2. Better organized sections
3. More concise instructions
4. Added placeholder for screenshots
5. Improved formatting
6. Added license placeholder
7. Better file structure visualization