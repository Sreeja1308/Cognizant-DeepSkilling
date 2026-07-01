package com.example;

public interface FileWriter {
    void write(String content);
    void append(String content);
    void delete();
}