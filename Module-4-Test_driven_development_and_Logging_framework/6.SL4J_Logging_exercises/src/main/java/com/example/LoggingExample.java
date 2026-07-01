package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);
    
    public static void main(String[] args) {
        System.out.println("=== Exercise 1: Logging Error Messages and Warning Levels ===\n");
        
        logger.error("This is an ERROR message - Critical issue needs immediate attention");
        logger.warn("This is a WARNING message - Potential issue to be aware of");
        logger.info("This is an INFO message - General application information");
        logger.debug("This is a DEBUG message - Detailed information for debugging");
        logger.trace("This is a TRACE message - Very detailed tracing information");
        
        System.out.println("\n--- Simulating Error Scenarios ---");
        
        try {
            int result = divide(10, 0);
        } catch (ArithmeticException e) {
            logger.error("Division by zero error occurred: {}", e.getMessage());
        }
        
        System.out.println("\n--- Simulating Warning Scenarios ---");
        
        String userInput = null;
        processUserInput(userInput);
        
        String emptyInput = "";
        processUserInput(emptyInput);
        
        String validInput = "John Doe";
        processUserInput(validInput);
        
        System.out.println("\n--- Logging with Context ---");
        
        simulateDatabaseOperation("users", "SELECT * FROM users");
        simulateApiCall("https://api.example.com/users", 200);
        simulateApiCall("https://api.example.com/invalid", 404);
        
        System.out.println("\n✅ Check the console output above for log messages.");
        System.out.println("📝 Notice the different colors/formatting for each log level.");
    }
    
    private static int divide(int a, int b) {
        logger.debug("Dividing {} by {}", a, b);
        try {
            return a / b;
        } catch (ArithmeticException e) {
            logger.error("Division error: Cannot divide by zero", e);
            throw e;
        }
    }
    
    private static void processUserInput(String input) {
        if (input == null) {
            logger.warn("User input is null - using default value");
            return;
        }
        if (input.trim().isEmpty()) {
            logger.warn("User input is empty - ignoring");
            return;
        }
        logger.info("Processing user input: {}", input);
    }
    
    private static void simulateDatabaseOperation(String table, String query) {
        logger.debug("Executing query on table '{}': {}", table, query);
       
        logger.info("Successfully executed query on table: {}", table);
    }
    
    private static void simulateApiCall(String url, int statusCode) {
        logger.debug("Making API call to: {}", url);
        if (statusCode >= 200 && statusCode < 300) {
            logger.info("API call successful: {} - Status: {}", url, statusCode);
        } else if (statusCode >= 400 && statusCode < 500) {
            logger.warn("API call failed with client error: {} - Status: {}", url, statusCode);
        } else if (statusCode >= 500) {
            logger.error("API call failed with server error: {} - Status: {}", url, statusCode);
        } else {
            logger.info("API call completed with status: {} - {}", statusCode, url);
        }
    }
}