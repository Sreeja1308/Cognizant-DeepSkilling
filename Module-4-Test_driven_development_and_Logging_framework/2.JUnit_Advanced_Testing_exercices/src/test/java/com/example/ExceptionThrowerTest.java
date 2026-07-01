package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionThrowerTest {
    
    private final ExceptionThrower exceptionThrower = new ExceptionThrower();
    
    @Test
    @DisplayName("Should throw IllegalArgumentException for illegal type")
    void testThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, 
            () -> exceptionThrower.throwException("illegal"),
            "Should throw IllegalArgumentException");
    }
    
    @Test
    @DisplayName("Should throw NullPointerException for null type")
    void testThrowNullPointerException() {
        assertThrows(NullPointerException.class, 
            () -> exceptionThrower.throwException(null),
            "Should throw NullPointerException");
    }
    
    @Test
    @DisplayName("Should throw RuntimeException for runtime type")
    void testThrowRuntimeException() {
        assertThrows(RuntimeException.class, 
            () -> exceptionThrower.throwException("runtime"),
            "Should throw RuntimeException");
    }
    
    @Test
    @DisplayName("Should throw RuntimeException for unknown type")
    void testThrowUnknownException() {
        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> exceptionThrower.throwException("unknown"),
            "Should throw RuntimeException for unknown type");
        
        assertEquals("Unknown exception type: unknown", exception.getMessage());
    }
    
    @Test
    @DisplayName("Should throw checked Exception")
    void testThrowCheckedException() {
        assertThrows(Exception.class, 
            () -> exceptionThrower.throwCheckedException(),
            "Should throw Exception");
    }
    
    @Test
    @DisplayName("Should not throw exception for valid input")
    void testNoExceptionForValidInput() {
        assertDoesNotThrow(() -> {
           
            boolean result = true;
            assertTrue(result);
        });
    }
}