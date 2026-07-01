package com.example;

public interface NetworkClient {
    String connect();
    String send(String data);
    String receive();
    void disconnect();
    boolean isConnected();
}