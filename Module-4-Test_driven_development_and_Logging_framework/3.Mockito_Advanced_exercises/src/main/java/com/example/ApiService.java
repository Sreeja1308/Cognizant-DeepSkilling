package com.example;

import java.util.HashMap;
import java.util.Map;

public class ApiService {
    private final RestClient restClient;

    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String fetchUserData(int userId) {
        String url = "https://api.example.com/users/" + userId;
        String response = restClient.get(url);
        if (response == null || response.isEmpty()) {
            return "User not found";
        }
        return "User data: " + response;
    }

    public int getUserStatus(int userId) {
        String url = "https://api.example.com/users/" + userId + "/status";
        return restClient.getStatus(url);
    }

    public String createUser(String userData) {
        String url = "https://api.example.com/users";
        return restClient.post(url, userData);
    }

    public void updateUser(int userId, String userData) {
        String url = "https://api.example.com/users/" + userId;
        restClient.put(url, userData);
    }
}