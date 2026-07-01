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
@DisplayName("Exercise 2: Mocking External Services (RESTful APIs)")
public class ApiServiceTest {

    @Mock
    private RestClient mockRestClient;

    private ApiService apiService;

    @BeforeEach
    void setUp() {
        apiService = new ApiService(mockRestClient);
    }

    @Test
    @DisplayName("Should fetch user data from REST API")
    void testFetchUserData() {
        // 1. Create a mock REST client using Mockito (already done with @Mock)
        // 2. Stub the REST client methods to return predefined responses
        String expectedResponse = "{\"id\":1,\"name\":\"John Doe\"}";
        when(mockRestClient.get("https://api.example.com/users/1"))
            .thenReturn(expectedResponse);

        // 3. Write a test to verify the service logic using the mocked REST client
        String result = apiService.fetchUserData(1);

        assertEquals("User data: " + expectedResponse, result);
        verify(mockRestClient).get("https://api.example.com/users/1");
    }

    @Test
    @DisplayName("Should handle empty response from REST API")
    void testFetchUserDataEmptyResponse() {
        when(mockRestClient.get(anyString())).thenReturn("");

        String result = apiService.fetchUserData(999);

        assertEquals("User not found", result);
        verify(mockRestClient).get("https://api.example.com/users/999");
    }

    @Test
    @DisplayName("Should handle null response from REST API")
    void testFetchUserDataNullResponse() {
        when(mockRestClient.get(anyString())).thenReturn(null);

        String result = apiService.fetchUserData(999);

        assertEquals("User not found", result);
        verify(mockRestClient).get("https://api.example.com/users/999");
    }

    @Test
    @DisplayName("Should get user status from REST API")
    void testGetUserStatus() {
        when(mockRestClient.getStatus(anyString())).thenReturn(200);

        int status = apiService.getUserStatus(1);

        assertEquals(200, status);
        verify(mockRestClient).getStatus("https://api.example.com/users/1/status");
    }

    @Test
    @DisplayName("Should create user via REST API")
    void testCreateUser() {
        String userData = "{\"name\":\"Alice\"}";
        String expectedResponse = "{\"id\":2,\"name\":\"Alice\"}";
        when(mockRestClient.post("https://api.example.com/users", userData))
            .thenReturn(expectedResponse);

        String result = apiService.createUser(userData);

        assertEquals(expectedResponse, result);
        verify(mockRestClient).post("https://api.example.com/users", userData);
    }

    @Test
    @DisplayName("Should update user via REST API")
    void testUpdateUser() {
        doNothing().when(mockRestClient).put(anyString(), anyString());

        apiService.updateUser(1, "{\"name\":\"Updated User\"}");

        verify(mockRestClient).put("https://api.example.com/users/1", "{\"name\":\"Updated User\"}");
    }
}