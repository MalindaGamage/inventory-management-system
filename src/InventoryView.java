import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        setTitle("Inventory Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Labels and Fields
        String[] labels = {"Item Code:", "Item Name:", "Quantity:", "Price per Unit:", "Supplier Details:"};
        JTextField[] fields = {itemCodeField = new JTextField(15), itemNameField = new JTextField(15),
                quantityField = new JTextField(15), priceField = new JTextField(15), supplierField = new JTextField(15)};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            inputPanel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            inputPanel.add(fields[i], gbc);
        }

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(e -> addItem());
        JButton updateButton = new JButton("Update Item");
        updateButton.addActionListener(e -> updateItem());
        JButton deleteButton = new JButton("Delete Item");
        deleteButton.addActionListener(e -> deleteItem());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchItems());
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Item Code", "Item Name", "Quantity", "Price", "Supplier"}, 0);
        itemTable = new JTable(tableModel);
        itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemTable.getSelectionModel().addListSelectionListener(e -> populateFields());
        JScrollPane scrollPane = new JScrollPane(itemTable);

        // Layout
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load initial data
        loadItems();
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
            Item item = new Item(0, itemCodeField.getText(), itemNameField.getText(),
                    Integer.parseInt(quantityField.getText()), Double.parseDouble(priceField.getText()),
                    supplierField.getText());
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
                    itemCodeField.getText(),
                    itemNameField.getText(),
                    Integer.parseInt(quantityField.getText()),
                    Double.parseDouble(priceField.getText()),
                    supplierField.getText()
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
            List<Item> items = itemController.searchItems(searchField.getText());
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
        if (itemCodeField.getText().isEmpty() || itemNameField.getText().isEmpty() ||
                quantityField.getText().isEmpty() || priceField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
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