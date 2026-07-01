package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 5: Mocking Multiple Return Values")
public class MultiReturnServiceTest {

    @Mock
    private Repository mockRepository;

    private MultiReturnService multiReturnService;

    @BeforeEach
    void setUp() {
        multiReturnService = new MultiReturnService(mockRepository);
    }

    @Test
    @DisplayName("Should return different values on consecutive calls")
    void testServiceWithMultipleReturnValues() {
        
        when(mockRepository.getData())
            .thenReturn("First Mock Data")
            .thenReturn("Second Mock Data");

        String firstResult = multiReturnService.getData();
        String secondResult = multiReturnService.getData();

        assertEquals("First Mock Data", firstResult);
        assertEquals("Second Mock Data", secondResult);
        verify(mockRepository, times(2)).getData();
    }

    @Test
    @DisplayName("Should return multiple values in sequence with thenReturn array")
    void testMultipleReturnsWithArray() {
        when(mockRepository.getData())
            .thenReturn("Value 1", "Value 2", "Value 3", "Value 4", "Value 5");

        assertEquals("Value 1", multiReturnService.getData());
        assertEquals("Value 2", multiReturnService.getData());
        assertEquals("Value 3", multiReturnService.getData());
        assertEquals("Value 4", multiReturnService.getData());
        assertEquals("Value 5", multiReturnService.getData());

        assertEquals("Value 5", multiReturnService.getData());
        assertEquals("Value 5", multiReturnService.getData());

        verify(mockRepository, times(7)).getData();
    }

    @Test
    @DisplayName("Should return multiple values with a loop")
    void testMultipleReturnsWithLoop() {
        when(mockRepository.getData())
            .thenReturn("A", "B", "C");

        List<String> results = multiReturnService.getMultipleData(3);

        assertEquals("A", results.get(0));
        assertEquals("B", results.get(1));
        assertEquals("C", results.get(2));
        verify(mockRepository, times(3)).getData();
    }

    @Test
    @DisplayName("Should combine return values and exceptions")
    void testReturnWithException() {
        when(mockRepository.getData())
            .thenReturn("First")
            .thenThrow(new RuntimeException("Database error"))
            .thenReturn("Recovered");

        String result1 = multiReturnService.getData();
        assertEquals("First", result1);

        assertThrows(RuntimeException.class, () -> 
            multiReturnService.getData()
        );

        String result3 = multiReturnService.getData();
        assertEquals("Recovered", result3);

        verify(mockRepository, times(3)).getData();
    }

    @Test
    @DisplayName("Should handle fallback logic with multiple returns")
    void testFallbackLogicWithMultipleReturns() {
       
        when(mockRepository.getData())
            .thenThrow(new RuntimeException("API Down"))
            .thenReturn("Fallback Data");

        String result = multiReturnService.processWithFallback();
        assertEquals("Error processing data", result);

        result = multiReturnService.processWithFallback();
        assertEquals("Processed Fallback Data", result);

        verify(mockRepository, times(2)).getData();
    }

    @Test
    @DisplayName("Should verify interaction counts with multiple returns")
    void testInteractionCountsWithMultipleReturns() {
        when(mockRepository.getData())
            .thenReturn("X", "Y", "Z");

        multiReturnService.getData();
        multiReturnService.getData();
        multiReturnService.getData();

        verify(mockRepository, times(3)).getData();
        verify(mockRepository, atLeast(3)).getData();
        verify(mockRepository, atMost(3)).getData();
    }
}