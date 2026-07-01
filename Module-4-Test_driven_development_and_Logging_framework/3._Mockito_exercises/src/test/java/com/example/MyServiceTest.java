package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito Exercises - All Tests")
public class MyServiceTest {

    @Mock
    private ExternalApi mockExternalApi;

    private MyService myService;

    @BeforeEach
    void setUp() {
        myService = new MyService(mockExternalApi);
    }

    @Test
    @DisplayName("Exercise 1: Mocking and Stubbing - Should return mock data")
    void testMockingAndStubbing() {
        when(mockExternalApi.getData()).thenReturn("Mock Data");

        String result = myService.fetchData();
        assertEquals("Mock Data", result, "Should return stubbed value");
        
        verify(mockExternalApi).getData();
    }

    @Test
    @DisplayName("Exercise 1: Multiple stubbing scenarios")
    void testMultipleStubbing() {
        // Stub different methods
        when(mockExternalApi.getData()).thenReturn("API Data");
        when(mockExternalApi.getUserData(anyInt())).thenReturn("User Data");

        assertEquals("API Data", myService.fetchData());
        assertEquals("User Data", myService.getUserInfo(123));
        
        verify(mockExternalApi).getData();
        verify(mockExternalApi).getUserData(123);
    }

    @Test
    @DisplayName("Exercise 2: Verifying Interactions - Should verify method called")
    void testVerifyInteraction() {
        myService.fetchData();
        
        verify(mockExternalApi).getData();
    }

    @Test
    @DisplayName("Exercise 2: Verify interaction with specific argument")
    void testVerifyInteractionWithArgument() {
        myService.getUserInfo(456);
        
        verify(mockExternalApi).getUserData(456);
    }

    @Test
    @DisplayName("Exercise 3: Argument Matching - Using argument matchers")
    void testArgumentMatching() {
       
        myService.fetchData(100, "customer");
        
        verify(mockExternalApi).fetchData(eq(100), eq("customer"));
    }

    @Test
    @DisplayName("Exercise 3: Argument Matching with any() matcher")
    void testArgumentMatchingWithAny() {
        myService.fetchData(200, "order");
        
        // Use flexible argument matchers
        verify(mockExternalApi).fetchData(anyInt(), anyString());
    }

    @Test
    @DisplayName("Exercise 3: Argument Matching with multiple matchers")
    void testArgumentMatchingMultiple() {
        when(mockExternalApi.fetchData(anyInt(), anyString()))
            .thenReturn("Matched Data");
        
        String result = myService.fetchData(999, "test");
        assertEquals("Matched Data", result);
        
        verify(mockExternalApi).fetchData(eq(999), eq("test"));
    }

    @Test
    @DisplayName("Exercise 5: Multiple Returns - Consecutive calls return different values")
    void testMultipleReturns() {
        
        when(mockExternalApi.getOrderCount())
            .thenReturn(1, 2, 3, 4, 5); // Returns 1, then 2, then 3, etc.

        assertEquals(1, myService.getOrderCount());
        assertEquals(2, myService.getOrderCount());
        assertEquals(3, myService.getOrderCount());
        assertEquals(4, myService.getOrderCount());
        assertEquals(5, myService.getOrderCount());
        
        verify(mockExternalApi, times(5)).getOrderCount();
    }

    @Test
    @DisplayName("Exercise 5: Multiple returns with thenReturn and thenThrow")
    void testMultipleReturnsWithExceptions() {
        // Stub to return first, then throw exception
        when(mockExternalApi.getOrderCount())
            .thenReturn(1)
            .thenThrow(new RuntimeException("API Error"));

        assertEquals(1, myService.getOrderCount());
        
        assertThrows(RuntimeException.class, () -> 
            myService.getOrderCount(), 
            "Should throw RuntimeException on second call"
        );
    }

    @Test
    @DisplayName("Exercise 6: Interaction Order - Verify methods called in order")
    void testVerifyInteractionOrder() {
        myService.saveUser("John");
        myService.fetchData();
        myService.getUserInfo(123);
        
        InOrder inOrder = inOrder(mockExternalApi);
        inOrder.verify(mockExternalApi).saveUser("John");
        inOrder.verify(mockExternalApi).getData();
        inOrder.verify(mockExternalApi).getUserData(123);
    }

    @Test
    @DisplayName("Exercise 6: Interaction Order with complex sequence")
    void testComplexInteractionOrder() {
        // Arrange
        when(mockExternalApi.getOrderCount()).thenReturn(1);
        doNothing().when(mockExternalApi).saveUser(anyString());
        
        myService.saveUser("Alice");
        myService.getOrderCount();
        myService.fetchData();
        
        InOrder inOrder = inOrder(mockExternalApi);
        inOrder.verify(mockExternalApi).saveUser("Alice");
        inOrder.verify(mockExternalApi).getOrderCount();
        inOrder.verify(mockExternalApi).getData();
    }

    @Test
    @DisplayName("Verify interaction count with times()")
    void testVerifyCount() {
        myService.fetchData();
        myService.fetchData();
        myService.fetchData();
        
        verify(mockExternalApi, times(3)).getData();
        verify(mockExternalApi, atLeastOnce()).getData();
        verify(mockExternalApi, atMost(5)).getData();
    }

    @Test
    @DisplayName("Void method - saveUser with doNothing")
    void testVoidMethodSaveUser() {
        doNothing().when(mockExternalApi).saveUser(anyString());
        
        myService.saveUser("TestUser");
        
        verify(mockExternalApi).saveUser("TestUser");
    }
}