package com.example;

public interface FileReader {
    String read();
    String readLine(int lineNumber);
    boolean exists();
    long size();
}