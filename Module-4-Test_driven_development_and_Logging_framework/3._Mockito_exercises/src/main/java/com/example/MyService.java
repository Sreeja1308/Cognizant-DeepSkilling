package com.example;

public class MyService {
    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String getUserInfo(int userId) {
        return externalApi.getUserData(userId);
    }

    public void sendNotification(String message) {
        externalApi.sendNotification(message);
    }

    public void processPayment(double amount) throws Exception {
        externalApi.processPayment(amount);
    }

    public int getOrderCount() {
        return externalApi.getOrderCount();
    }

    public void saveUser(String username) {
        externalApi.saveUser(username);
    }

    public String fetchData(int id, String type) {
        return externalApi.fetchData(id, type);
    }
}