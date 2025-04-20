import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class InventoryView extends JFrame {
    private ItemController itemController;
    private DefaultTableModel tableModel;
    private JTable itemTable;
    private JTextField itemCodeField, itemNameField, quantityField, priceField, supplierField, searchField;

    public InventoryView() {
        itemController = new ItemController();
        setTitle("Inventory Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600)); // Ensure minimum size for responsiveness

        // Main panel with modern layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(0xF5F7FA));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(mainPanel);

        // Header panel with branding and logout
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0x2196F3));
        headerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel headerLabel = new JLabel("Inventory Dashboard");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel, BorderLayout.WEST);

        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton, new Color(0x010215), new Color(0xF57C00));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginView().setVisible(true);
        });
        headerPanel.add(logoutButton, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Input panel for item details
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(0xB0BEC5)),
                        "Item Details",
                        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new Font("SansSerif", Font.BOLD, 14)),
                new EmptyBorder(10, 10, 10, 10)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {"Item Code:", "Item Name:", "Quantity:", "Price per Unit:", "Supplier Details:"};
        JTextField[] fields = {
                itemCodeField = new JTextField(15),
                itemNameField = new JTextField(15),
                quantityField = new JTextField(15),
                priceField = new JTextField(15),
                supplierField = new JTextField(15)
        };

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("SansSerif", Font.PLAIN, 14));
            label.setForeground(new Color(0x212121));
            gbc.gridx = 0;
            gbc.gridy = i;
            inputPanel.add(label, gbc);

            fields[i].setFont(new Font("SansSerif", Font.PLAIN, 14));
            fields[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0xB0BEC5), 1, true),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            fields[i].setToolTipText("Enter " + labels[i].replace(":", ""));
            gbc.gridx = 1;
            gbc.gridy = i;
            inputPanel.add(fields[i], gbc);
        }

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton addButton = new JButton("Add Item");
        styleButton(addButton, new Color(0x4CAF50), new Color(0x388E3C));
        addButton.addActionListener(e -> addItem());

        JButton updateButton = new JButton("Update Item");
        styleButton(updateButton, new Color(0x2196F3), new Color(0x1976D2));
        updateButton.addActionListener(e -> updateItem());

        JButton deleteButton = new JButton("Delete Item");
        styleButton(deleteButton, new Color(0xD32F2F), new Color(0xB71C1C));
        deleteButton.addActionListener(e -> deleteItem());

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        mainPanel.add(inputPanel, BorderLayout.WEST);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(new Color(0xF5F7FA));

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchPanel.add(searchLabel);

        searchField = new JTextField(20);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0xB0BEC5), 1, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        searchField.setToolTipText("Search by item name or code");
        searchPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        styleButton(searchButton, new Color(0x2196F3), new Color(0x1976D2));
        searchButton.addActionListener(e -> searchItems());
        searchPanel.add(searchButton);

        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Item Code", "Item Name", "Quantity", "Price", "Supplier"}, 0);
        itemTable = new JTable(tableModel);
        itemTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
        itemTable.setRowHeight(30);
        itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemTable.setGridColor(new Color(0xE0E0E0));
        itemTable.setShowGrid(true);

        // Customize table header
        JTableHeader header = itemTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(0x2196F3));
        header.setForeground(Color.BLACK);
        header.setBorder(BorderFactory.createLineBorder(new Color(0xB0BEC5)));

        // Center-align table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        itemTable.setDefaultRenderer(Object.class, centerRenderer);

        itemTable.getSelectionModel().addListSelectionListener(e -> populateFields());
        JScrollPane scrollPane = new JScrollPane(itemTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0xB0BEC5)));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Load initial data
        loadItems();

        // Add keyboard navigation
        itemCodeField.addActionListener(e -> itemNameField.requestFocus());
        itemNameField.addActionListener(e -> quantityField.requestFocus());
        quantityField.addActionListener(e -> priceField.requestFocus());
        priceField.addActionListener(e -> supplierField.requestFocus());
        supplierField.addActionListener(e -> addButton.doClick());
    }

    private void styleButton(JButton button, Color bgColor, Color hoverColor) {
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setForeground(Color.BLACK);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    private void loadItems() {
        try {
            tableModel.setRowCount(0);
            List<Item> items = itemController.getAllItems();
            for (Item item : items) {
                tableModel.addRow(new Object[]{item.getId(), item.getItemCode(), item.getItemName(),
                        item.getQuantity(), item.getPricePerUnit(), item.getSupplierDetails()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading items: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addItem() {
        if (!validateInputs()) return;
        try {
            Item item = new Item(0, itemCodeField.getText().trim(), itemNameField.getText().trim(),
                    Integer.parseInt(quantityField.getText().trim()), Double.parseDouble(priceField.getText().trim()),
                    supplierField.getText().trim());
            itemController.addItem(item);
            loadItems();
            clearFields();
            JOptionPane.showMessageDialog(this, "Item added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding item: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateItem() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to update", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!validateInputs()) return;
        try {
            Item item = new Item(
                    (int) tableModel.getValueAt(selectedRow, 0),
                    itemCodeField.getText().trim(),
                    itemNameField.getText().trim(),
                    Integer.parseInt(quantityField.getText().trim()),
                    Double.parseDouble(priceField.getText().trim()),
                    supplierField.getText().trim()
            );
            itemController.updateItem(item);
            loadItems();
            clearFields();
            JOptionPane.showMessageDialog(this, "Item updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating item: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteItem() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            itemController.deleteItem(id);
            loadItems();
            clearFields();
            JOptionPane.showMessageDialog(this, "Item deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting item: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchItems() {
        try {
            tableModel.setRowCount(0);
            List<Item> items = itemController.searchItems(searchField.getText().trim());
            for (Item item : items) {
                tableModel.addRow(new Object[]{item.getId(), item.getItemCode(), item.getItemName(),
                        item.getQuantity(), item.getPricePerUnit(), item.getSupplierDetails()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error searching items: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateFields() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow != -1) {
            itemCodeField.setText((String) tableModel.getValueAt(selectedRow, 1));
            itemNameField.setText((String) tableModel.getValueAt(selectedRow, 2));
            quantityField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 3)));
            priceField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 4)));
            supplierField.setText((String) tableModel.getValueAt(selectedRow, 5));
        }
    }

    private boolean validateInputs() {
        if (itemCodeField.getText().trim().isEmpty() || itemNameField.getText().trim().isEmpty() ||
                quantityField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int quantity = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());
            if (quantity < 0 || price < 0) {
                JOptionPane.showMessageDialog(this, "Quantity and price must be non-negative", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity and price must be valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearFields() {
        itemCodeField.setText("");
        itemNameField.setText("");
        quantityField.setText("");
        priceField.setText("");
        supplierField.setText("");
    }
}