Inventory Management System Documentation
1. List of Features and Descriptions

User Authentication:

Login System: Users can log in with a username and password stored in the SQLite database.
Registration System: New users can register with a unique username and password.


CRUD Operations:

Create: Add new inventory items with attributes: Item Code, Item Name, Quantity, Price per Unit, and Supplier Details.
Read: View all inventory items in a table view, with search functionality to filter by item name or code.
Update: Modify existing item details.
Delete: Remove items from the inventory.


Search Functionality:

Filter inventory items based on item name or code using a search bar.


Input Validation:

Ensures all required fields are filled.
Validates numeric inputs (quantity and price) and ensures they are non-negative.


Responsive UI:

Designed with Java Swing using GridBagLayout and BorderLayout for adaptability to different screen resolutions.
Includes tooltips and meaningful error messages for user guidance.


Performance Optimization:

Uses prepared statements to prevent SQL injection and optimize database queries.
Efficient data retrieval with minimal database calls.


Security:

Uses prepared statements for all database interactions to prevent SQL injection.
Stores user credentials in the database (Note: In a production environment, passwords should be hashed).



2. Technologies Used

Programming Language: Java (JDK 17)
GUI Framework: Java Swing
Database: SQLite
Libraries:
SQLite JDBC Driver (sqlite-jdbc-3.42.0.jar) for database connectivity
Java Collections Framework for managing in-memory data


Development Environment: IntelliJ IDEA (or any Java IDE)
Version Control: Git (repository hosted on GitHub)

3. Setup and Run Instructions
   Prerequisites

Java Development Kit (JDK) 17 or higher
SQLite JDBC Driver (sqlite-jdbc-3.42.0.jar)
A Java IDE (e.g., IntelliJ IDEA, Eclipse) or command-line tools

Steps to Set Up and Run

Clone the Repository:
git clone <repository-url>
cd inventory-management-system


Add SQLite JDBC Driver:

Download the SQLite JDBC driver from Maven Repository.
Add the sqlite-jdbc-3.42.0.jar to your project's library path.
In IntelliJ IDEA: File > Project Structure > Libraries > + > Java > Select the JAR file.
In Eclipse: Right-click project > Build Path > Add External Archives.




Compile and Run:

Open the project in your IDE.

Ensure the SQLite JDBC driver is included in the classpath.

Run the InventoryManagementSystem.java class as the main entry point.

Alternatively, from the command line:
javac -cp .:lib/sqlite-jdbc-3.42.0.jar src/*.java
java -cp .:lib/sqlite-jdbc-3.42.0.jar src/InventoryManagementSystem




Database Setup:

The application automatically creates an SQLite database file (inventory.db) in the project directory upon first run.
Tables for users and items are created automatically.


Using the Application:

Register: Create a new user account using the registration button.
Login: Log in with your credentials.
Manage Inventory:
Add new items using the input fields.
Update or delete items by selecting them from the table.
Search for items using the search bar.





Notes

The database file (inventory.db) is created in the project directory and persists data between sessions.
Ensure the SQLite JDBC driver is correctly included to avoid ClassNotFoundException.
For production, implement password hashing (e.g., using BCrypt) for enhanced security.

4. Additional Notes

The application uses the MVC pattern:
Model: Item.java represents the data structure.
View: LoginView.java and InventoryView.java handle the UI.
Controller: ItemController.java manages business logic and database interactions.


The UI is designed to be intuitive, with tooltips and error messages to guide users.
The search functionality is case-insensitive and supports partial matches.
The application is optimized for performance with minimal database queries and efficient data handling using the Java Collections Framework.

