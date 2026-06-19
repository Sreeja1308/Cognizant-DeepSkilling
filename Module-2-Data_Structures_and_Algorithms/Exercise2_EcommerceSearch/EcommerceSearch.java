package Exercise2_EcommerceSearch;

import java.util.*;

public class EcommerceSearch {
    private Product[] products;
    
    public EcommerceSearch(Product[] products) {
        this.products = products;
    }
    
    public Product linearSearch(int productId) {
        for (Product product : products) {
            if (product != null && product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }
    
    public Product binarySearch(int productId) {
        int left = 0;
        int right = products.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (products[mid] == null) {
                return null;
            }
            
            if (products[mid].getProductId() == productId) {
                return products[mid];
            }
            
            if (products[mid].getProductId() < productId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Mouse", "Electronics"),
            new Product(3, "Book", "Stationery"),
            new Product(4, "Chair", "Furniture"),
            new Product(5, "Table", "Furniture")
        };
        
        Arrays.sort(products, Comparator.comparingInt(Product::getProductId));
        
        EcommerceSearch search = new EcommerceSearch(products);
        
        System.out.println("Linear Search for product 3:");
        System.out.println(search.linearSearch(3));
        
        System.out.println("\nBinary Search for product 3:");
        System.out.println(search.binarySearch(3));
        
        System.out.println("\nLinear Search for product 10 (not found):");
        System.out.println(search.linearSearch(10));
        
        System.out.println("\nBinary Search for product 10 (not found):");
        System.out.println(search.binarySearch(10));
    }
}