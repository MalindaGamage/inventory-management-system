// Model class for Inventory Item
public class Item {
    private int id;
    private String itemCode;
    private String itemName;
    private int quantity;
    private double pricePerUnit;
    private String supplierDetails;

    public Item(int id, String itemCode, String itemName, int quantity, double pricePerUnit, String supplierDetails) {
        this.id = id;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.supplierDetails = supplierDetails;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getItemCode() { return itemCode; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public double getPricePerUnit() { return pricePerUnit; }
    public String getSupplierDetails() { return supplierDetails; }

    public void setId(int id) { this.id = id; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPricePerUnit(double pricePerUnit) { this.pricePerUnit = pricePerUnit; }
    public void setSupplierDetails(String supplierDetails) { this.supplierDetails = supplierDetails; }
}