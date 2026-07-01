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
@DisplayName("Exercise 4: Void Methods Tests")
public class UserServiceTest {

    @Mock
    private ExternalApi mockExternalApi;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(mockExternalApi);
    }

    // Exercise 4: Handling Void Methods
    @Test
    @DisplayName("Exercise 4: Void Method - Should save user successfully")
    void testVoidMethod() {
        doNothing().when(mockExternalApi).saveUser(anyString());

        userService.saveUser("John Doe");
        verify(mockExternalApi).saveUser("John Doe");
    }

    @Test
    @DisplayName("Exercise 4: Void Method - Should handle null username")
    void testVoidMethodWithNull() {
      
        assertThrows(IllegalArgumentException.class, 
            () -> userService.saveUser(null),
            "Should throw IllegalArgumentException for null username"
        );

        verify(mockExternalApi, never()).saveUser(anyString());
    }

    @Test
    @DisplayName("Exercise 4: Void Method - Should handle empty username")
    void testVoidMethodWithEmptyString() {
       
        assertThrows(IllegalArgumentException.class,
            () -> userService.saveUser(""),
            "Should throw IllegalArgumentException for empty username"
        );

        verify(mockExternalApi, never()).saveUser(anyString());
    }

    @Test
    @DisplayName("Exercise 4: Void Method - Should handle whitespace username")
    void testVoidMethodWithWhitespace() {
       
        assertThrows(IllegalArgumentException.class,
            () -> userService.saveUser("   "),
            "Should throw IllegalArgumentException for whitespace username"
        );

        verify(mockExternalApi, never()).saveUser(anyString());
    }
}