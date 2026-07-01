package com.example;

public interface RestClient {
    String get(String url);
    String post(String url, String body);
    int getStatus(String url);
    void put(String url, String body);
}