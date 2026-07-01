package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;  

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@DisplayName("Exercise 1: Mocking Service Dependency in Controller Test")
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
    
    @Test
    @DisplayName("Should return user when found")
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
    @DisplayName("Should return 404 when user not found")
    void testGetUser_UserNotFound() throws Exception {
      
        when(userService.getUserById(999L)).thenReturn(null);
        
        mockMvc.perform(get("/api/users/{id}", 999L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        
        verify(userService).getUserById(999L);
    }
    
    @Test
    @DisplayName("Should create user successfully")
    void testCreateUser() throws Exception {
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
    @DisplayName("Should get all users")
    void testGetAllUsers() throws Exception {
        List<User> users = List.of(  // ← Now List is imported
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
    @DisplayName("Should delete user")
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);
        
        mockMvc.perform(delete("/api/users/{id}", 1L))
                .andExpect(status().isNoContent());
        
        verify(userService).deleteUser(1L);
    }
}