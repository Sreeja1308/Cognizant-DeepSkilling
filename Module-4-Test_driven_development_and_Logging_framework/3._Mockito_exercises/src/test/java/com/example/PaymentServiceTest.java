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
@DisplayName("Exercise 7: Void Methods with Exceptions Tests")
public class PaymentServiceTest {

    @Mock
    private ExternalApi mockExternalApi;

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService(mockExternalApi);
    }

    //Exercise 7: Handling Void Methods with Exceptions 
    @Test
    @DisplayName("Exercise 7: Void Method with Exception - Should throw exception")
    void testVoidMethodWithException() throws Exception {
        doThrow(new RuntimeException("Payment failed"))
            .when(mockExternalApi).processPayment(anyDouble());

        assertThrows(RuntimeException.class,
            () -> paymentService.processPayment(100.0),
            "Should throw RuntimeException when payment fails"
        );

        verify(mockExternalApi).processPayment(100.0);
    }

    @Test
    @DisplayName("Exercise 7: Void Method with Exception - Retry logic")
    void testVoidMethodWithRetry() throws Exception {
        doThrow(new RuntimeException("Payment failed"))
            .doNothing()
            .when(mockExternalApi).processPayment(anyDouble());

        paymentService.payWithRetry(50.0);

        verify(mockExternalApi, times(2)).processPayment(50.0);
    }

    @Test
    @DisplayName("Exercise 7: Void Method with Exception - Checked exception")
    void testVoidMethodWithCheckedException() throws Exception {
        // Throw a checked exception
        doThrow(new Exception("Payment service unavailable"))
            .when(mockExternalApi).processPayment(anyDouble());

        assertThrows(Exception.class,
            () -> paymentService.processPayment(100.0),
            "Should throw Exception when payment service fails"
        );

        verify(mockExternalApi).processPayment(100.0);
    }

    @Test
    @DisplayName("Exercise 7: Void Method with Exception - Multiple calls")
    void testVoidMethodWithMultipleExceptions() throws Exception {
        doThrow(new RuntimeException("Network error"))
            .doThrow(new RuntimeException("Timeout"))
            .doNothing()
            .when(mockExternalApi).processPayment(anyDouble());

        assertThrows(RuntimeException.class,
            () -> paymentService.processPayment(100.0)
        );

        assertThrows(RuntimeException.class,
            () -> paymentService.processPayment(100.0)
        );

        paymentService.processPayment(100.0);

        verify(mockExternalApi, times(3)).processPayment(100.0);
    }
}