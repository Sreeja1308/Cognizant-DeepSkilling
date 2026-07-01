package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class AppenderExample {
    
    private static final Logger logger = LoggerFactory.getLogger(AppenderExample.class);
    
    public static void main(String[] args) {
        System.out.println("=== Exercise 3: Using Different Appenders ===\n");
        System.out.println("📝 Log messages are being sent to multiple destinations:");
        System.out.println("   - Console (with colors)");
        System.out.println("   - logs/app.log (all logs)");
        System.out.println("   - logs/error.log (ERROR level only)");
        System.out.println("   - logs/application.log (rolling file with date)\n");
        
        System.out.println("--- Logging Messages at Different Levels ---");
        
        logger.trace("This is a TRACE message - Very detailed tracing");
        logger.debug("This is a DEBUG message - Useful for debugging");
        logger.info("This is an INFO message - General information");
        logger.warn("This is a WARN message - Warning condition");
        logger.error("This is an ERROR message - Error condition");
        
        System.out.println("\n--- Generating Multiple Log Messages ---");
        generateLogMessages(10);
        
        System.out.println("\n--- Simulating Application Lifecycle ---");
        simulateApplicationLifecycle();
        
        System.out.println("\n--- Logging with Context Information ---");
        logWithContext();
        
        System.out.println("\n--- Log File Information ---");
        displayLogFileInfo();
        
        System.out.println("\n✅ Check the following locations for log files:");
        System.out.println("   📄 logs/app.log - All log messages");
        System.out.println("   📄 logs/error.log - Only ERROR level messages");
        System.out.println("   📄 logs/application.log - Rolling log files");
    }
    
    private static void generateLogMessages(int count) {
        for (int i = 1; i <= count; i++) {
            if (i % 2 == 0) {
                logger.info("Processing item {} of {} - Operation completed", i, count);
            } else {
                logger.debug("Processing item {} of {} - Starting operation", i, count);
            }
            
            if (i % 3 == 0) {
                logger.warn("Item {} processing is slower than expected", i);
            }
            
            if (i % 5 == 0) {
                logger.error("Error processing item {} - Retrying...", i);
            }
        }
        logger.info("Completed processing {} items", count);
    }
    
    private static void simulateApplicationLifecycle() {
        logger.info("Application starting up...");
        
        logger.debug("Loading configuration from application.properties");
        logger.debug("Initializing database connection pool");
        logger.info("Database connection pool initialized with 10 connections");
        
        for (int i = 1; i <= 5; i++) {
            logger.info("Executing batch job {} of {}", i, 5);
            logger.debug("Batch job {} - Processing {} records", i, i * 100);
            if (i == 3) {
                logger.warn("Batch job {} encountered duplicate records - skipping", i);
            }
            if (i == 5) {
                logger.error("Batch job {} failed - Check logs for details", i);
            }
        }
        
        logger.info("Application shutting down...");
        logger.debug("Closing database connections");
        logger.debug("Cleaning up resources");
        logger.info("Application shutdown complete");
    }
    
    private static void logWithContext() {
        String userId = "user-123";
        String action = "LOGIN";
        String ipAddress = "192.168.1.100";
        
        logger.info("User {} performed {} from IP {}", userId, action, ipAddress);
        logger.debug("User {} - Login successful at {}", userId, System.currentTimeMillis());
        
        String orderId = "ORD-789";
        double amount = 299.99;
        String status = "CONFIRMED";
        
        logger.info("Order {} created - Amount: ${}, Status: {}", orderId, amount, status);
        logger.debug("Order {} - Items: 3, Shipping: Standard, Tracking: TRK-{}", 
                    orderId, System.currentTimeMillis() % 10000);
        
        String paymentId = "PAY-456";
        String method = "CREDIT_CARD";
        
        logger.info("Payment {} processed - Method: {}, Amount: ${}", paymentId, method, amount);
        logger.debug("Payment {} - Transaction ID: {}, Status: {}", 
                    paymentId, "TXN-" + System.currentTimeMillis(), "COMPLETED");
    }
    
    private static void displayLogFileInfo() {
        Path logDir = Paths.get("logs");
        
        if (Files.exists(logDir)) {
            try (Stream<Path> paths = Files.list(logDir)) {
                System.out.println("📂 Log files found:");
                paths.filter(Files::isRegularFile)
                     .forEach(path -> {
                         try {
                             long size = Files.size(path);
                             String sizeStr = formatFileSize(size);
                             System.out.printf("   📄 %s (%s)%n", 
                                               path.getFileName(), sizeStr);
                         } catch (IOException e) {
                             System.out.printf("   📄 %s (unknown size)%n", 
                                               path.getFileName());
                         }
                     });
            } catch (IOException e) {
                System.err.println("Error listing log files: " + e.getMessage());
            }
        } else {
            System.out.println("📁 Log directory 'logs' does not exist yet.");
            System.out.println("   It will be created when the application runs.");
        }
    }
    
    /**
     * Format file size for display
     */
    private static String formatFileSize(long size) {
        if (size < 1024) {
            return size + " B";
        } else if (size < 1024 * 1024) {
            return String.format("%.2f KB", size / 1024.0);
        } else if (size < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", size / (1024.0 * 1024));
        } else {
            return String.format("%.2f GB", size / (1024.0 * 1024 * 1024));
        }
    }
}