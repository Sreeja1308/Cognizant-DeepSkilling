package com.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {
    
    private static StringBuilder executionOrder = new StringBuilder();
    
    @BeforeAll
    static void setup() {
        executionOrder = new StringBuilder();
        System.out.println("Test execution order will be tracked");
    }
    
    @Test
    @Order(1)
    @DisplayName("First test - should run first")
    void testFirst() {
        executionOrder.append("1");
        System.out.println("Running testFirst - Order: " + executionOrder);
        assertEquals("1", executionOrder.toString());
    }
    
    @Test
    @Order(2)
    @DisplayName("Second test - should run second")
    void testSecond() {
        executionOrder.append("2");
        System.out.println("Running testSecond - Order: " + executionOrder);
        assertEquals("12", executionOrder.toString());
    }
    
    @Test
    @Order(3)
    @DisplayName("Third test - should run third")
    void testThird() {
        executionOrder.append("3");
        System.out.println("Running testThird - Order: " + executionOrder);
        assertEquals("123", executionOrder.toString());
    }
    
    @Test
    @Order(5)
    @DisplayName("Fifth test - with higher order number")
    void testFifth() {
        executionOrder.append("5");
        System.out.println("Running testFifth - Order: " + executionOrder);
        assertTrue(executionOrder.toString().contains("123"));
    }
    
    @Test
    @Order(4)
    @DisplayName("Fourth test - should run before fifth")
    void testFourth() {
        executionOrder.append("4");
        System.out.println("Running testFourth - Order: " + executionOrder);
        assertTrue(executionOrder.toString().contains("123"));
    }
    
    @AfterAll
    static void tearDown() {
        System.out.println("Final execution order: " + executionOrder.toString());
        
    }
}