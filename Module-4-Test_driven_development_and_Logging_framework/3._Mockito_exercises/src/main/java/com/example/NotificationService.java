package com.example;

public class NotificationService {
    private final ExternalApi externalApi;

    public NotificationService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public void sendNotification(String message) {
        externalApi.sendNotification(message);
    }

    public void sendWelcomeEmail(String username) {
        externalApi.sendNotification("Welcome " + username + "!");
    }
}