package com.example;

public class UserService {
    private final ExternalApi externalApi;

    public UserService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String getUserData(int userId) {
        String data = externalApi.getUserData(userId);
        if (data == null) {
            return "User not found";
        }
        return "User: " + data;
    }

    public void saveUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        externalApi.saveUser(username);
    }
}