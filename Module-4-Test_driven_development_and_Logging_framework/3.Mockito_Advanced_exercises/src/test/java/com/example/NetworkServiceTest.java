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
@DisplayName("Exercise 4: Mocking Network Interactions")
public class NetworkServiceTest {

    @Mock
    private NetworkClient mockNetworkClient;

    private NetworkService networkService;

    @BeforeEach
    void setUp() {
        networkService = new NetworkService(mockNetworkClient);
    }

    @Test
    @DisplayName("Should connect to server")
    void testServiceWithMockNetworkClient() {
        when(mockNetworkClient.connect()).thenReturn("Mock Connection");

        String result = networkService.connectToServer();

        assertEquals("Connected to Mock Connection", result);
        verify(mockNetworkClient).connect();
    }

    @Test
    @DisplayName("Should handle connection failure")
    void testConnectionFailure() {
        when(mockNetworkClient.connect()).thenReturn(null);

        String result = networkService.connectToServer();

        assertEquals("Connection failed", result);
        verify(mockNetworkClient).connect();
    }

    @Test
    @DisplayName("Should send data when connected")
    void testSendDataWhenConnected() {
        when(mockNetworkClient.isConnected()).thenReturn(true);
        when(mockNetworkClient.send("Hello")).thenReturn("Acknowledged");

        String result = networkService.sendData("Hello");

        assertEquals("Acknowledged", result);
        verify(mockNetworkClient).isConnected();
        verify(mockNetworkClient).send("Hello");
    }

    @Test
    @DisplayName("Should not send data when disconnected")
    void testSendDataWhenDisconnected() {
        when(mockNetworkClient.isConnected()).thenReturn(false);

        String result = networkService.sendData("Hello");

        assertEquals("Not connected", result);
        verify(mockNetworkClient).isConnected();
        verify(mockNetworkClient, never()).send(anyString());
    }

    @Test
    @DisplayName("Should receive data when connected")
    void testReceiveDataWhenConnected() {
        when(mockNetworkClient.isConnected()).thenReturn(true);
        when(mockNetworkClient.receive()).thenReturn("Received Data");

        String result = networkService.receiveData();

        assertEquals("Received Data", result);
        verify(mockNetworkClient).isConnected();
        verify(mockNetworkClient).receive();
    }

    @Test
    @DisplayName("Should not receive data when disconnected")
    void testReceiveDataWhenDisconnected() {
        when(mockNetworkClient.isConnected()).thenReturn(false);

        String result = networkService.receiveData();

        assertEquals("Not connected", result);
        verify(mockNetworkClient).isConnected();
        verify(mockNetworkClient, never()).receive();
    }

    @Test
    @DisplayName("Should disconnect from network")
    void testDisconnect() {
        doNothing().when(mockNetworkClient).disconnect();

        networkService.disconnect();

        verify(mockNetworkClient).disconnect();
    }

    @Test
    @DisplayName("Should check connection status")
    void testIsConnected() {
        when(mockNetworkClient.isConnected()).thenReturn(true);

        boolean connected = networkService.isConnected();

        assertTrue(connected);
        verify(mockNetworkClient).isConnected();
    }
}