package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 1: Mocking Databases and Repositories")
public class ServiceTest {

    @Mock
    private Repository mockRepository;

    private Service service;

    @BeforeEach
    void setUp() {
        service = new Service(mockRepository);
    }

    @Test
    @DisplayName("Should process data from repository")
    void testServiceWithMockRepository() {
        
        when(mockRepository.getData()).thenReturn("Mock Data");

        String result = service.processData();

        assertEquals("Processed Mock Data", result);
        verify(mockRepository).getData();
    }

    @Test
    @DisplayName("Should handle null data from repository")
    void testServiceWithNullData() {
        when(mockRepository.getData()).thenReturn(null);

        String result = service.processData();

        assertEquals("No data available", result);
        verify(mockRepository).getData();
    }

    @Test
    @DisplayName("Should find by id with mocked repository")
    void testFindById() {
        when(mockRepository.findById(123)).thenReturn("User Data");

        String result = service.findById(123);

        assertEquals("Found: User Data", result);
        verify(mockRepository).findById(123);
    }

    @Test
    @DisplayName("Should handle not found by id")
    void testFindByIdNotFound() {
        when(mockRepository.findById(456)).thenReturn(null);

        String result = service.findById(456);

        assertEquals("Not found", result);
        verify(mockRepository).findById(456);
    }

    @Test
    @DisplayName("Should save data with mock repository")
    void testSaveData() {
        doNothing().when(mockRepository).save(anyString());

        service.saveData("Test Data");

        verify(mockRepository).save("Test Data");
    }

    @Test
    @DisplayName("Should get count from mock repository")
    void testGetCount() {
        when(mockRepository.count()).thenReturn(10);

        int count = service.getCount();

        assertEquals(10, count);
        verify(mockRepository).count();
    }
}