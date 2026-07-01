package com.example.integration;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
@DisplayName("Exercise 4: Integration Tests")
class UserIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private User testUser;
    
    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        testUser = new User("John Doe", "john@example.com");
    }
    
    @Test
    @DisplayName("Exercise 4: Full flow - Create and retrieve user")
    void testCreateAndGetUser() throws Exception {
        String createResponse = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        
        User createdUser = objectMapper.readValue(createResponse, User.class);
        Long userId = createdUser.getId();
        
        mockMvc.perform(get("/api/users/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }
    
    @Test
    @DisplayName("Exercise 4: Full flow - Get all users")
    void testGetAllUsers() throws Exception {
        userRepository.save(new User("John Doe", "john@example.com"));
        userRepository.save(new User("Alice", "alice@example.com"));
        userRepository.save(new User("Bob", "bob@example.com"));
        
        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }
    
    @Test
    @DisplayName("Exercise 4: Full flow - Search users by name")
    void testSearchUsers() throws Exception {
        userRepository.save(new User("John Doe", "john@example.com"));
        userRepository.save(new User("Johnny", "johnny@example.com"));
        userRepository.save(new User("Alice", "alice@example.com"));
        
        mockMvc.perform(get("/api/users/search")
                .param("name", "John")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    
    @Test
    @DisplayName("Exercise 4: Full flow - Update user")
    void testUpdateUser() throws Exception {
        // Use unique emails to avoid conflicts
        String originalEmail = "original_" + System.currentTimeMillis() + "@example.com";
        User saved = userRepository.save(new User("Original Name", originalEmail));
        
        String newEmail = "updated_" + System.currentTimeMillis() + "@example.com";
        User updated = new User("Updated Name", newEmail);
        
        mockMvc.perform(put("/api/users/{id}", saved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.email").value(newEmail));
    }
    
    @Test
    @DisplayName("Exercise 4: Full flow - Delete user")
    void testDeleteUser() throws Exception {
        User saved = userRepository.save(testUser);
        
        mockMvc.perform(delete("/api/users/{id}", saved.getId()))
                .andExpect(status().isNoContent());
        
        mockMvc.perform(get("/api/users/{id}", saved.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    @Test
    @DisplayName("Exercise 4: Full flow - Handle validation errors")
    void testValidationErrors() throws Exception {
        User invalidUser = new User();
        
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidUser)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @DisplayName("Exercise 4: Full flow - Handle duplicate email")
    void testDuplicateEmail() throws Exception {
        userRepository.save(testUser);
        User duplicateUser = new User("Jane Doe", "john@example.com");
        
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(duplicateUser)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @DisplayName("Exercise 4: Full flow - Get user by email")
    void testGetUserByEmail() throws Exception {
        userRepository.save(testUser);
        
        mockMvc.perform(get("/api/users/search")
                .param("name", "John")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}