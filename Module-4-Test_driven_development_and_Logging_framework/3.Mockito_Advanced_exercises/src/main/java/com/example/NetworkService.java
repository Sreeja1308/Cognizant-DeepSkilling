package com.example;

public class NetworkService {
    private final NetworkClient networkClient;

    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public String connectToServer() {
        String connection = networkClient.connect();
        if (connection == null) {
            return "Connection failed";
        }
        return "Connected to " + connection;
    }

    public String sendData(String data) {
        if (!networkClient.isConnected()) {
            return "Not connected";
        }
        return networkClient.send(data);
    }

    public String receiveData() {
        if (!networkClient.isConnected()) {
            return "Not connected";
        }
        return networkClient.receive();
    }

    public void disconnect() {
        networkClient.disconnect();
    }

    public boolean isConnected() {
        return networkClient.isConnected();
    }
}