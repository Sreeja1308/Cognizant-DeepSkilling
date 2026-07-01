package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 2, 6, 7: User Service Tests")
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    private User testUser;
    
    @BeforeEach
    void setUp() {
        testUser = new User(1L, "John Doe", "john@example.com");
    }
    
    //  Exercise 2: Mocking Repository 
    @Test
    @DisplayName("Exercise 2: Should return user when found")
    void testGetUserById_UserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        
        User result = userService.getUserById(1L);
        
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        verify(userRepository).findById(1L);
    }
    
    @Test
    @DisplayName("Exercise 2: Should return null when user not found")
    void testGetUserById_UserNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());
        
        User result = userService.getUserById(999L);
        
        assertNull(result);
        verify(userRepository).findById(999L);
    }
    
    //  Exercise 6: Exception Handling 
    @Test
    @DisplayName("Exercise 6: Should throw exception when user not found")
    void testGetUserByIdOrThrow_UserNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());
        
        assertThrows(NoSuchElementException.class,
            () -> userService.getUserByIdOrThrow(999L),
            "Should throw NoSuchElementException when user not found"
        );
        verify(userRepository).findById(999L);
    }
    
    @Test
    @DisplayName("Exercise 6: Should return user when found with exception version")
    void testGetUserByIdOrThrow_UserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        
        User result = userService.getUserByIdOrThrow(1L);
        
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository).findById(1L);
    }
    
    @Test
    @DisplayName("Exercise 6: Should handle duplicate email for new user")
    void testSaveUser_DuplicateEmail() {
        User newUser = new User("John Doe", "john@example.com");
        when(userRepository.existsByEmail("john@example.com")).thenReturn(true);
        
        assertThrows(IllegalArgumentException.class,
            () -> userService.saveUser(newUser),
            "Should throw IllegalArgumentException for duplicate email"
        );
        verify(userRepository).existsByEmail("john@example.com");
        verify(userRepository, never()).save(any(User.class));
    }
    
    @Test
    @DisplayName("Exercise 6: Should handle duplicate email during update")
    void testSaveUser_DuplicateEmailDuringUpdate() {
        User updatedUser = new User(1L, "John Updated", "john.updated@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(userRepository.existsByEmail("john.updated@example.com")).thenReturn(true);
        
        assertThrows(IllegalArgumentException.class,
            () -> userService.saveUser(updatedUser),
            "Should throw IllegalArgumentException for duplicate email during update"
        );
        verify(userRepository).findById(1L);
        verify(userRepository).existsByEmail("john.updated@example.com");
        verify(userRepository, never()).save(any(User.class));
    }
    
    //  Exercise 7: Custom Repository Query 
    @Test
    @DisplayName("Exercise 7: Should find users by name")
    void testFindUsersByName() {
        List<User> users = Arrays.asList(
            new User(1L, "John", "john@example.com"),
            new User(2L, "Johnny", "johnny@example.com")
        );
        when(userRepository.findByName("John")).thenReturn(users);
        
        List<User> result = userService.findUsersByName("John");
        
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        verify(userRepository).findByName("John");
    }
    
    @Test
    @DisplayName("Exercise 7: Should find users by name containing")
    void testFindUsersByNameContaining() {
        List<User> users = Arrays.asList(
            new User(1L, "Alice", "alice@example.com"),
            new User(2L, "Bob", "bob@example.com")
        );
        when(userRepository.findByNameContaining("Al")).thenReturn(users);
        
        List<User> result = userService.findUsersByNameContaining("Al");
        
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepository).findByNameContaining("Al");
    }
    
    @Test
    @DisplayName("Exercise 7: Should find user by email")
    void testFindUserByEmail() {
        when(userRepository.findByEmail("john@example.com"))
            .thenReturn(Optional.of(testUser));
        
        User result = userService.findUserByEmail("john@example.com");
        
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository).findByEmail("john@example.com");
    }
    
    @Test
    @DisplayName("Exercise 7: Should handle email not found")
    void testFindUserByEmail_NotFound() {
        when(userRepository.findByEmail("unknown@example.com"))
            .thenReturn(Optional.empty());
        
        assertThrows(NoSuchElementException.class,
            () -> userService.findUserByEmail("unknown@example.com")
        );
        verify(userRepository).findByEmail("unknown@example.com");
    }
}