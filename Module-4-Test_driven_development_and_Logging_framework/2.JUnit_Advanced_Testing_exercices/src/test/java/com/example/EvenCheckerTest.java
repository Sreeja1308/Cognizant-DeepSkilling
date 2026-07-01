package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class EvenCheckerTest {
    
    private final EvenChecker evenChecker = new EvenChecker();
    
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 0, -2, -4, -6})
    @DisplayName("Should return true for even numbers")
    void testIsEvenForEvenNumbers(int number) {
        assertTrue(evenChecker.isEven(number), 
            number + " should be even");
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, -1, -3, -5})
    @DisplayName("Should return false for odd numbers")
    void testIsEvenForOddNumbers(int number) {
        assertFalse(evenChecker.isEven(number), 
            number + " should be odd");
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10})
    @DisplayName("Should identify even numbers correctly")
    void testIsEvenWithMultipleValues(int number) {
        
        assertEquals(number % 2 == 0, evenChecker.isEven(number));
    }
}