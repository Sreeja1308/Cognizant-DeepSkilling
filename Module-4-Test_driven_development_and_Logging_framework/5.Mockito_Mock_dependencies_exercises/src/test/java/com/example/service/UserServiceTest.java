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
@DisplayName("Exercise 2: Mocking Repository in Service Test")
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
    
    @Test
    @DisplayName("Should return user when found")
    void testGetUserById_UserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        
        User result = userService.getUserById(1L);
        
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        verify(userRepository).findById(1L);
    }
    
    @Test
    @DisplayName("Should return null when user not found")
    void testGetUserById_UserNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());
        
        User result = userService.getUserById(999L);
        
        assertNull(result);
        verify(userRepository).findById(999L);
    }
    
    @Test
    @DisplayName("Should throw exception when user not found with orElseThrow")
    void testGetUserByIdOrThrow_UserNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());
        
        assertThrows(NoSuchElementException.class,
            () -> userService.getUserByIdOrThrow(999L),
            "Should throw NoSuchElementException when user not found"
        );
        verify(userRepository).findById(999L);
    }
    
    @Test
    @DisplayName("Should return user when found with orElseThrow")
    void testGetUserByIdOrThrow_UserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        
        User result = userService.getUserByIdOrThrow(1L);
        
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository).findById(1L);
    }
    
    @Test
    @DisplayName("Should save user successfully")
    void testSaveUser_Success() {
        User newUser = new User("Alice", "alice@example.com");
        User savedUser = new User(2L, "Alice", "alice@example.com");
        when(userRepository.existsByEmail("alice@example.com")).thenReturn(false);
        when(userRepository.save(newUser)).thenReturn(savedUser);
        
        User result = userService.saveUser(newUser);
        
        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("Alice", result.getName());
        verify(userRepository).existsByEmail("alice@example.com");
        verify(userRepository).save(newUser);
    }
    
    @Test
    @DisplayName("Should throw exception when email already exists")
    void testSaveUser_DuplicateEmail() {
        User newUser = new User("Alice", "john@example.com");
        when(userRepository.existsByEmail("john@example.com")).thenReturn(true);
        
        assertThrows(IllegalArgumentException.class,
            () -> userService.saveUser(newUser),
            "Should throw IllegalArgumentException for duplicate email"
        );
        verify(userRepository).existsByEmail("john@example.com");
        verify(userRepository, never()).save(any(User.class));
    }
    
    @Test
    @DisplayName("Should get all users")
    void testGetAllUsers() {
        List<User> users = Arrays.asList(
            new User(1L, "John", "john@example.com"),
            new User(2L, "Alice", "alice@example.com")
        );
        when(userRepository.findAll()).thenReturn(users);
        
        List<User> result = userService.getAllUsers();
        
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }
    
    @Test
    @DisplayName("Should delete user")
    void testDeleteUser_Success() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);
        
        userService.deleteUser(1L);
        
        verify(userRepository).existsById(1L);
        verify(userRepository).deleteById(1L);
    }
    
    @Test
    @DisplayName("Should throw exception when deleting non-existent user")
    void testDeleteUser_NotFound() {
        when(userRepository.existsById(999L)).thenReturn(false);
        
        assertThrows(NoSuchElementException.class,
            () -> userService.deleteUser(999L)
        );
        verify(userRepository).existsById(999L);
        verify(userRepository, never()).deleteById(anyLong());
    }
}