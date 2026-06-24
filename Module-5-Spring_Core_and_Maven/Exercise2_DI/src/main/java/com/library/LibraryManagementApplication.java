package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    
    public static void main(String[] args) {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        BookService bookService = context.getBean("bookService", BookService.class);
        
        System.out.println("=== Testing Dependency Injection ===");
        
        bookService.addBook("Clean Code");
        bookService.addBook("Design Patterns");
        
        String book = bookService.getBook("B002");
        System.out.println("Retrieved: " + book);
        
        System.out.println("Dependency Injection test completed successfully!");
    }
}