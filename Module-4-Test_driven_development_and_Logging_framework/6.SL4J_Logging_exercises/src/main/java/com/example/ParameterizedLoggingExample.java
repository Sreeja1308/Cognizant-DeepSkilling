package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParameterizedLoggingExample {
    
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);
    
    public static void main(String[] args) {
        System.out.println("=== Exercise 2: Parameterized Logging ===\n");
        
        System.out.println("--- Simple Parameterized Logging ---");
        String userName = "Alice";
        int age = 30;
        String city = "New York";
        
        logger.info("User {} is {} years old and lives in {}", userName, age, city);
        logger.debug("User details - Name: {}, Age: {}, City: {}", userName, age, city);
        
        System.out.println("\n--- Multiple Parameters ---");
        logUserDetails("Bob", 25, "bob@example.com", "Los Angeles");
        logUserDetails("Charlie", null, "charlie@example.com", "Chicago");
        
        System.out.println("\n--- Logging with Exceptions ---");
        try {
            processOrder("ORDER-12345", 999.99);
        } catch (Exception e) {
            logger.error("Order processing failed for order: {}", "ORDER-12345", e);
        }
        
        System.out.println("\n--- Logging Collections ---");
        List<String> items = Arrays.asList("Laptop", "Mouse", "Keyboard", "Monitor");
        logger.info("Processing order with {} items: {}", items.size(), items);
        
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Laptop", 50);
        inventory.put("Mouse", 200);
        inventory.put("Keyboard", 100);
        logger.debug("Current inventory: {}", inventory);
        
        System.out.println("\n--- Advanced Parameterized Logging ---");
        advancedLoggingExample();
        
        System.out.println("\n--- Performance Comparison ---");
        performanceComparison();
        
        System.out.println("\n✅ Check the console output above for parameterized log messages.");
        System.out.println("💡 Notice how {} placeholders are replaced with actual values.");
    }
    
    private static void logUserDetails(String name, Integer age, String email, String city) {
        if (age == null) {
            logger.warn("User {} has no age specified - using default", name);
            age = 0;
        }
        logger.info("User Profile - Name: {}, Age: {}, Email: {}, City: {}", 
                   name, age, email, city);
        logger.debug("Detailed user info for {} - Age: {}, Location: {}", 
                    name, age, city);
    }
    
    private static void processOrder(String orderId, double amount) {
        logger.info("Processing order {} with amount: ${}", orderId, amount);
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Order amount must be positive: " + amount);
        }
        if (orderId == null || orderId.isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be empty");
        }
        
        logger.debug("Order {} - Amount: ${} - Processing...", orderId, amount);
        
        if (amount > 1000) {
            logger.warn("Order {} has high value: ${} - requiring additional approval", 
                       orderId, amount);
        }
        
        logger.info("Order {} processed successfully for amount: ${}", orderId, amount);
    }
    
    private static void advancedLoggingExample() {
        // Logging with conditional parameters
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ISO_DATE_TIME);
        
        logger.info("Application started at: {}", timestamp);
        
        int totalItems = 100;
        int processedItems = 75;
        int remainingItems = totalItems - processedItems;
        double percentage = (processedItems * 100.0) / totalItems;
        
        logger.info("Progress: {}/{} items processed ({}%)", 
                   processedItems, totalItems, String.format("%.2f", percentage));
        
        logger.debug("Order summary - ID: {}, Total: ${}, Items: {}, Status: {}", 
                    "ORD-789", 299.99, 3, "CONFIRMED");
        
        User user = new User("David", 28, "david@example.com");
        logger.info("New user registered: {}", user);
        logger.debug("User details - Name: {}, Age: {}, Email: {}", 
                    user.getName(), user.getAge(), user.getEmail());
    }
    
    private static void performanceComparison() {
        System.out.println("Comparing logging performance...");
        
        String name = "TestUser";
        int id = 12345;
        logger.debug("User: " + name + " with ID: " + id + " logged in");  // Not recommended
        
        logger.debug("User: {} with ID: {} logged in", name, id);  // Recommended
        
        System.out.println("💡 Parameterized logging is more efficient because:");
        System.out.println("   - It only constructs the message if the log level is enabled");
        System.out.println("   - It avoids unnecessary string concatenation");
        System.out.println("   - It's cleaner and more readable");
    }
   
    static class User {
        private String name;
        private int age;
        private String email;
        
        public User(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getEmail() { return email; }
        
        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + ", email='" + email + "'}";
        }
    }
}