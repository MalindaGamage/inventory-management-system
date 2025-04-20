import javax.swing.*;
import java.sql.SQLException;

// Main class to launch the Inventory Management System
public class InventoryManagementSystem {
    public static void main(String[] args) {
        // Ensure GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize database connection
                DatabaseConnection.initializeDatabase();

                // Set look and feel for better UI
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                // Start with login view
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
            } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                JOptionPane.showMessageDialog(null, "Error initializing application: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }
}