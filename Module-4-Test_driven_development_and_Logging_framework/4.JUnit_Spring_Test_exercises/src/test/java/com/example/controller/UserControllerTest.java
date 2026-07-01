package com.example.controller;

import com.example.entity.User;
import com.example.exception.GlobalExceptionHandler;
import com.example.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {UserController.class, GlobalExceptionHandler.class})
@DisplayName("Exercise 3, 5, 8: User Controller Tests")
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private User testUser;
    
    @BeforeEach
    void setUp() {
        testUser = new User(1L, "John Doe", "john@example.com");
    }
    
    //Exercise 3: Testing GET endpoint 
    @Test
    @DisplayName("Exercise 3: Should return user when found")
    void testGetUser_UserExists() throws Exception {
      
        when(userService.getUserById(1L)).thenReturn(testUser);
        
        mockMvc.perform(get("/api/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
        
        verify(userService).getUserById(1L);
    }
    
    @Test
    @DisplayName("Exercise 3: Should return 404 when user not found")
    void testGetUser_UserNotFound() throws Exception {
       
        when(userService.getUserById(999L)).thenReturn(null);
        
        mockMvc.perform(get("/api/users/{id}", 999L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        
        verify(userService).getUserById(999L);
    }
    
    @Test
    @DisplayName("Exercise 3: Should return user with exception handling")
    void testGetUserV2_UserExists() throws Exception {
       
        when(userService.getUserByIdOrThrow(1L)).thenReturn(testUser);
        
        mockMvc.perform(get("/api/users/v2/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
        
        verify(userService).getUserByIdOrThrow(1L);
    }
    
    @Test
    @DisplayName("Exercise 8: Should handle exception with controller advice")
    void testGetUserV2_UserNotFound() throws Exception {
       
        when(userService.getUserByIdOrThrow(999L))
            .thenThrow(new NoSuchElementException("User not found with id: 999"));
        
        mockMvc.perform(get("/api/users/v2/{id}", 999L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("User not found"));
        
        verify(userService).getUserByIdOrThrow(999L);
    }
    
    //Exercise 5: Testing POST endpoint 
    @Test
    @DisplayName("Exercise 5: Should create user successfully")
    void testCreateUser_Success() throws Exception {
       
        User newUser = new User("Alice", "alice@example.com");
        User savedUser = new User(2L, "Alice", "alice@example.com");
        when(userService.saveUser(any(User.class))).thenReturn(savedUser);
        
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
        
        verify(userService).saveUser(any(User.class));
    }
    
    @Test
    @DisplayName("Exercise 5: Should return 400 when validation fails")
    void testCreateUser_ValidationFails() throws Exception {
     
        User invalidUser = new User(); 
        
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidUser)))
                .andExpect(status().isBadRequest());
        
        verify(userService, never()).saveUser(any(User.class));
    }
    
    @Test
    @DisplayName("Exercise 5: Should handle duplicate email")
    void testCreateUser_DuplicateEmail() throws Exception {
      
        User newUser = new User("John", "john@example.com");
        when(userService.saveUser(any(User.class)))
            .thenThrow(new IllegalArgumentException("Email already exists: john@example.com"));
        
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isBadRequest());
        
        verify(userService).saveUser(any(User.class));
    }
    
    @Test
    @DisplayName("Should get all users")
    void testGetAllUsers() throws Exception {
       
        List<User> users = Arrays.asList(
            new User(1L, "John", "john@example.com"),
            new User(2L, "Alice", "alice@example.com")
        );
        when(userService.getAllUsers()).thenReturn(users);
        
        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].name").value("Alice"));
        
        verify(userService).getAllUsers();
    }
    
    @Test
    @DisplayName("Should search users by name")
    void testSearchUsers() throws Exception {
      
        List<User> users = Arrays.asList(
            new User(1L, "John", "john@example.com")
        );
        when(userService.findUsersByNameContaining("John")).thenReturn(users);
        
        mockMvc.perform(get("/api/users/search")
                .param("name", "John")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("John"));
        
        verify(userService).findUsersByNameContaining("John");
    }
    
    @Test
    @DisplayName("Should update user")
    void testUpdateUser() throws Exception {
        
        User updatedUser = new User(1L, "John Updated", "john.updated@example.com");
        when(userService.getUserByIdOrThrow(1L)).thenReturn(testUser);
        when(userService.saveUser(any(User.class))).thenReturn(updatedUser);
        
        mockMvc.perform(put("/api/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Updated"))
                .andExpect(jsonPath("$.email").value("john.updated@example.com"));
        
        verify(userService).getUserByIdOrThrow(1L);
        verify(userService).saveUser(any(User.class));
    }
    
    @Test
    @DisplayName("Should delete user")
    void testDeleteUser() throws Exception {
       
        doNothing().when(userService).deleteUser(1L);
        
        mockMvc.perform(delete("/api/users/{id}", 1L))
                .andExpect(status().isNoContent());
        
        verify(userService).deleteUser(1L);
    }
}