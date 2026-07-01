package com.example;

public interface ExternalApi {
    String getData();
    String getUserData(int userId);
    void sendNotification(String message);
    void processPayment(double amount) throws Exception;
    int getOrderCount();
    void saveUser(String username);
    String fetchData(int id, String type);
}