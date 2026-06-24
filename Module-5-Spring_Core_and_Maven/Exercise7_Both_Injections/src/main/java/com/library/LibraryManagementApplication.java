package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        System.out.println("=== Testing Constructor and Setter Injection ===");
        
        BookService bookService = context.getBean("bookService", BookService.class);
        
        bookService.addBook("Spring Boot Mastery");
        bookService.addBook("Microservices with Spring");
        
        String book = bookService.getBook("B005");
        System.out.println("Retrieved: " + book);
        
        System.out.println("Constructor and Setter Injection test completed!");
    }
}