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
@DisplayName("Order Service - Comprehensive Tests")
public class OrderServiceTest {

    @Mock
    private ExternalApi mockExternalApi;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(mockExternalApi);
    }

    @Test
    @DisplayName("Exercise 5: Multiple returns - Order count with varying values")
    void testOrderCountMultipleReturns() {
        when(mockExternalApi.getOrderCount())
            .thenReturn(10, 20, 30);

        assertEquals(10, orderService.getOrderCount());
        assertEquals(20, orderService.getOrderCount());
        assertEquals(30, orderService.getOrderCount());

        verify(mockExternalApi, times(3)).getOrderCount();
    }

    @Test
    @DisplayName("Exercise 6: Verify interaction order in order processing")
    void testProcessOrderInteractionOrder() {
        when(mockExternalApi.getOrderCount()).thenReturn(2);
        doNothing().when(mockExternalApi).saveUser(anyString());

        orderService.processOrder(123);
        InOrder inOrder = inOrder(mockExternalApi);
        inOrder.verify(mockExternalApi).getOrderCount();
        inOrder.verify(mockExternalApi).saveUser("Order-123");
    }

    @Test
    @DisplayName("Exercise 7: Void method with exception in order processing")
    void testProcessOrderWithException() {
        when(mockExternalApi.getOrderCount()).thenReturn(10); // Too many orders

        assertThrows(RuntimeException.class,
            () -> orderService.processOrder(456),
            "Should throw exception when too many orders"
        );

        verify(mockExternalApi).getOrderCount();
        verify(mockExternalApi, never()).saveUser(anyString());
    }

    @Test
    @DisplayName("Exercise 1,3: Stubbing and argument matchers combined")
    void testCombinedStubbingAndMatchers() {
        // Stub with matchers
        when(mockExternalApi.fetchData(anyInt(), anyString()))
            .thenReturn("Order Data");

        when(mockExternalApi.getOrderCount()).thenReturn(5);

        String data = mockExternalApi.fetchData(100, "test");
        int count = mockExternalApi.getOrderCount();

        assertEquals("Order Data", data);
        assertEquals(5, count);

        verify(mockExternalApi).fetchData(anyInt(), anyString());
        verify(mockExternalApi).getOrderCount();
    }
}