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
@DisplayName("Notification Service Tests")
public class NotificationServiceTest {

    @Mock
    private ExternalApi mockExternalApi;

    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationService(mockExternalApi);
    }

    @Test
    @DisplayName("Should send notification successfully")
    void testSendNotification() {
       
        doNothing().when(mockExternalApi).sendNotification(anyString());

        notificationService.sendNotification("Test message");

        verify(mockExternalApi).sendNotification("Test message");
    }

    @Test
    @DisplayName("Should send welcome email")
    void testSendWelcomeEmail() {
        doNothing().when(mockExternalApi).sendNotification(anyString());

        notificationService.sendWelcomeEmail("Alice");

        verify(mockExternalApi).sendNotification("Welcome Alice!");
    }

    @Test
    @DisplayName("Should handle exception in notification")
    void testSendNotificationWithException() {
        // Stub void method to throw exception
        doThrow(new RuntimeException("Notification service down"))
            .when(mockExternalApi).sendNotification(anyString());

        assertThrows(RuntimeException.class,
            () -> notificationService.sendNotification("Test")
        );

        verify(mockExternalApi).sendNotification("Test");
    }
}