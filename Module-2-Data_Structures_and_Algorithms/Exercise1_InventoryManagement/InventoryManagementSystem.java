package Exercise1_InventoryManagement;
import java.util.*;

public class InventoryManagementSystem {
    private Map<Integer, Product> inventory;
    
    public InventoryManagementSystem() {
        this.inventory = new HashMap<>();
    }
    
    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }
    
    public void updateProduct(int productId, String productName, int quantity, double price) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setProductName(productName);
            product.setQuantity(quantity);
            product.setPrice(price);
        }
    }
    
    public void deleteProduct(int productId) {
        inventory.remove(productId);
    }
    
    public Product getProduct(int productId) {
        return inventory.get(productId);
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(inventory.values());
    }
    
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        ims.addProduct(new Product(1, "Laptop", 10, 999.99));
        ims.addProduct(new Product(2, "Mouse", 50, 29.99));
        ims.addProduct(new Product(3, "Keyboard", 30, 79.99));
        
        System.out.println("All Products:");
        for (Product p : ims.getAllProducts()) {
            System.out.println(p);
        }
        
        ims.updateProduct(2, "Wireless Mouse", 45, 34.99);
        System.out.println("\nUpdated Product:");
        System.out.println(ims.getProduct(2));
        
        ims.deleteProduct(3);
        System.out.println("\nAfter deleting product 3:");
        for (Product p : ims.getAllProducts()) {
            System.out.println(p);
        }
    }
}

class Product {
    private int productId;
    private String productName;
    private int quantity;
    private double price;
    
    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    
    public void setProductName(String productName) { this.productName = productName; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
    
    @Override
    public String toString() {
        return "Product{id=" + productId + ", name='" + productName + "', quantity=" + quantity + ", price=" + price + "}";
    }
}