package com.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercise 1 & 9: Calculator Service Tests")
class CalculatorServiceTest {
    
    private CalculatorService calculatorService;
    
    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }
    
    //  Exercise 1: Basic Unit Test 
    @Test
    @DisplayName("Exercise 1: Should add two numbers correctly")
    void testAdd() {
        // Arrange
        int a = 5;
        int b = 3;
        int expected = 8;
        
        // Act
        int result = calculatorService.add(a, b);
        
        // Assert
        assertEquals(expected, result, "5 + 3 should equal 8");
    }
    
    @Test
    @DisplayName("Should subtract two numbers correctly")
    void testSubtract() {
        assertEquals(2, calculatorService.subtract(5, 3));
        assertEquals(-2, calculatorService.subtract(3, 5));
    }
    
    @Test
    @DisplayName("Should multiply two numbers correctly")
    void testMultiply() {
        assertEquals(15, calculatorService.multiply(5, 3));
        assertEquals(0, calculatorService.multiply(5, 0));
        assertEquals(-15, calculatorService.multiply(5, -3));
    }
    
    @Test
    @DisplayName("Should divide two numbers correctly")
    void testDivide() {
        assertEquals(2, calculatorService.divide(10, 5));
        assertEquals(3, calculatorService.divide(10, 3));
    }
    
    @Test
    @DisplayName("Should throw exception when dividing by zero")
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, 
            () -> calculatorService.divide(10, 0),
            "Should throw IllegalArgumentException when dividing by zero"
        );
    }
    
    // Exercise 9: Parameterized Test
    @ParameterizedTest
    @DisplayName("Exercise 9: Parameterized test for addition")
    @CsvSource({
        "1, 2, 3",
        "5, 5, 10",
        "10, 20, 30",
        "100, 200, 300",
        "0, 0, 0",
        "-1, 1, 0",
        "-5, -3, -8"
    })
    void testAddParameterized(int a, int b, int expected) {
        int result = calculatorService.add(a, b);
        assertEquals(expected, result, a + " + " + b + " should equal " + expected);
    }
    
    @ParameterizedTest
    @DisplayName("Parameterized test for multiplication")
    @CsvSource({
        "1, 2, 2",
        "5, 5, 25",
        "10, 10, 100",
        "0, 5, 0",
        "-2, 3, -6"
    })
    void testMultiplyParameterized(int a, int b, int expected) {
        int result = calculatorService.multiply(a, b);
        assertEquals(expected, result, a + " * " + b + " should equal " + expected);
    }
    
    @ParameterizedTest
    @DisplayName("Parameterized test for subtraction")
    @CsvSource({
        "5, 3, 2",
        "10, 5, 5",
        "1, 1, 0",
        "0, 5, -5"
    })
    void testSubtractParameterized(int a, int b, int expected) {
        int result = calculatorService.subtract(a, b);
        assertEquals(expected, result, a + " - " + b + " should equal " + expected);
    }
}