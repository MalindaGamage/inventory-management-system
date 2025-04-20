# Inventory Management System

![Java](https://img.shields.io/badge/Java-17-blue)
![SQLite](https://img.shields.io/badge/SQLite-3.42-green)
![Swing](https://img.shields.io/badge/GUI-Swing-orange)

A comprehensive inventory management system built with Java Swing and SQLite, featuring user authentication, CRUD operations, and search functionality.

## Features

### User Authentication
- **Login System**: Secure login with username/password stored in SQLite
- **Registration System**: New user signup with unique username validation

### Inventory Management (CRUD)
- **Create**: Add new items with:
   - Item Code
   - Item Name
   - Quantity
   - Price per Unit
   - Supplier Details
- **Read**: View all items in sortable table
- **Update**: Modify existing item details
- **Delete**: Remove items from inventory

### Enhanced Functionality
- 🔍 **Search**: Filter items by name/code (case-insensitive)
- ✔️ **Input Validation**: Ensures valid, non-negative numeric inputs
- 📱 **Responsive UI**: Adapts to different screen sizes using Swing layouts
- ⚡ **Performance**: Optimized database queries with prepared statements
- 🔒 **Security**: SQL injection protection (passwords should be hashed in production)

## Technologies

- **Language**: Java (JDK 17+)
- **GUI**: Java Swing
- **Database**: SQLite
- **Libraries**:
   - SQLite JDBC Driver (sqlite-jdbc-3.42.0.jar)
   - Java Collections Framework

## Setup Instructions

### Prerequisites
- JDK 17 or higher
- SQLite JDBC Driver
- Java IDE (IntelliJ/Eclipse) or command-line tools

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/inventory-management-system.git
   cd inventory-management-system
   ```

2. Add SQLite JDBC Driver:
   - Download from [Maven Repository](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc)
   - Add to project libraries:
      - **IntelliJ**: File > Project Structure > Libraries > + > Java > Select JAR
      - **Eclipse**: Right-click project > Build Path > Add External Archives

### Running the Application
**Using IDE**:
1. Open project in your Java IDE
2. Ensure SQLite JDBC is in classpath
3. Run `InventoryManagementSystem.java`

**Command Line**:
```bash
javac -cp .:lib/sqlite-jdbc-3.42.0.jar src/*.java
java -cp .:lib/sqlite-jdbc-3.42.0.jar src/InventoryManagementSystem
```

### Database Setup
- The application automatically creates `inventory.db` on first run
- Tables for users and items are generated automatically

## Usage
1. **Register**: Create a new account
2. **Login**: Use your credentials
3. **Manage Inventory**:
   - Add items using input fields
   - Update/delete items by selecting from table
   - Search using the search bar

## Architecture
- **MVC Pattern**:
   - Model: `Item.java`
   - View: `LoginView.java`, `InventoryView.java`
   - Controller: `ItemController.java`

## Notes
- Database file (`inventory.db`) persists in project directory
- For production: Implement password hashing (e.g., BCrypt)
- UI includes tooltips and error messages for user guidance

## Contributing
Pull requests welcome! For major changes, please open an issue first.

## License
[MIT](https://choosealicense.com/licenses/mit/)