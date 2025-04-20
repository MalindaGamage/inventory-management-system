import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
    public void addItem(Item item) throws SQLException {
        String sql = "INSERT INTO items (item_code, item_name, quantity, price_per_unit, supplier_details) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, item.getItemCode());
            pstmt.setString(2, item.getItemName());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setDouble(4, item.getPricePerUnit());
            pstmt.setString(5, item.getSupplierDetails());
            pstmt.executeUpdate();
        }
    }

    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Statement stmt = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Item item = new Item(
                        rs.getInt("id"),
                        rs.getString("item_code"),
                        rs.getString("item_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price_per_unit"),
                        rs.getString("supplier_details")
                );
                items.add(item);
            }
        }
        return items;
    }

    public void updateItem(Item item) throws SQLException {
        String sql = "UPDATE items SET item_code = ?, item_name = ?, quantity = ?, price_per_unit = ?, supplier_details = ? WHERE id = ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, item.getItemCode());
            pstmt.setString(2, item.getItemName());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setDouble(4, item.getPricePerUnit());
            pstmt.setString(5, item.getSupplierDetails());
            pstmt.setInt(6, item.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteItem(int id) throws SQLException {
        String sql = "DELETE FROM items WHERE id = ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<Item> searchItems(String query) throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE item_name LIKE ? OR item_code LIKE ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, "%" + query + "%");
            pstmt.setString(2, "%" + query + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(
                            rs.getInt("id"),
                            rs.getString("item_code"),
                            rs.getString("item_name"),
                            rs.getInt("quantity"),
                            rs.getDouble("price_per_unit"),
                            rs.getString("supplier_details")
                    );
                    items.add(item);
                }
            }
        }
        return items;
    }
}