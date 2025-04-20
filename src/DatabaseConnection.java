import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static Connection connection;
    private static final String URL = "jdbc:sqlite:inventory.db";

    public static void initializeDatabase() throws SQLException, ClassNotFoundException {
        // Load SQLite JDBC driver
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(URL);

        // Create tables if they don't exist
        createTables();
    }

    public static Connection getConnection() {
        return connection;
    }

    private static void createTables() throws SQLException {
        String userTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL UNIQUE," +
                "password TEXT NOT NULL)";

        String itemTable = "CREATE TABLE IF NOT EXISTS items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "item_code TEXT NOT NULL UNIQUE," +
                "item_name TEXT NOT NULL," +
                "quantity INTEGER NOT NULL," +
                "price_per_unit REAL NOT NULL," +
                "supplier_details TEXT)";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(itemTable);
        }
    }
}