package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
        System.out.println("=== Library Management System ===");
        System.out.println("Application started successfully!");
        System.out.println("H2 Console: http://localhost:8080/api/h2-console");
        System.out.println("API Base URL: http://localhost:8080/api/books");
    }
}