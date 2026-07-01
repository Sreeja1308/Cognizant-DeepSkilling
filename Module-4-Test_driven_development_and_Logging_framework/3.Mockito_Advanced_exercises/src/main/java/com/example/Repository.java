package com.example;

public interface Repository {
    String getData();
    String findById(int id);
    void save(String data);
    int count();
}