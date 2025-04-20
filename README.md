Inventory Management System
A Java-based desktop application for managing inventory, built with Java Swing for the GUI and SQLite for persistent data storage. This project is developed as part of the IT 240 Advanced Programming with Java I course for the 2025 (I) semester.
Features

User authentication (login and registration)
CRUD operations for inventory items (Create, Read, Update, Delete)
Search functionality to filter items by name or code
Input validation for user inputs
Responsive and user-friendly UI with error messages and tooltips
MVC design pattern for maintainable code
SQLite database for persistent storage
Secure database interactions using prepared statements

Prerequisites

Java Development Kit (JDK) 17 or higher
SQLite JDBC Driver (sqlite-jdbc-3.42.0.jar)
A Java IDE (e.g., IntelliJ IDEA, Eclipse) or command-line tools
Git (for cloning the repository)

Setup Instructions

Clone the Repository:
git clone <repository-url>
cd inventory-management-system


Download SQLite JDBC Driver:

Get sqlite-jdbc-3.42.0.jar from Maven Repository.
Place it in the lib/ directory.


Add SQLite JDBC Driver to Project:

In IntelliJ IDEA: File > Project Structure > Libraries > + > Java > Select lib/sqlite-jdbc-3.42.0.jar.
In Eclipse: Right-click project > Build Path > Add External Archives > Select lib/sqlite-jdbc-3.42.0.jar.


Run the Application:

Open the project in your IDE and run src/InventoryManagementSystem.java.
Or, from the command line:javac -cp .:lib/sqlite-jdbc-3.42.0.jar src/*.java
java -cp .:lib/sqlite-jdbc-3.42.0.jar src/InventoryManagementSystem




Using the Application:

Register a new user or log in with existing credentials.
Add, update, delete, or search inventory items using the intuitive UI.



Project Structure
inventory-management-system/
├── src/                          # Java source files
│   ├── InventoryManagementSystem.java
│   ├── DatabaseConnection.java
│   ├── Item.java
│   ├── ItemController.java
│   ├── LoginView.java
│   └── InventoryView.java
├── lib/                          # External dependencies
│   └── sqlite-jdbc-3.42.0.jar
├── docs/                         # Documentation
│   └── Documentation.md
├── inventory.db                  # SQLite database (auto-generated)
├── README.md                     # Project overview
└── .gitignore                    # Git ignore file

Notes

The database (inventory.db) is created automatically on first run.
For production, enhance security by hashing passwords (e.g., using BCrypt).
Refer to docs/Documentation.md for detailed setup and feature descriptions.

Submission

GitHub Repository: <repository-url> (to be uploaded by the student)
Blackboard: Submit the repository link and presentation by 11:59 PM on 11/05/2025.

