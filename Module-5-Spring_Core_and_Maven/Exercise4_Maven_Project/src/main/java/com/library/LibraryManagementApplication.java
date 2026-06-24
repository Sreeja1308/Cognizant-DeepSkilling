package com.library;

import com.library.service.BookService;

public class LibraryManagementApplication {
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        
        BookService bookService = context.getBean("bookService", BookService.class);
        
        System.out.println("=== Testing AOP Logging ===");
        System.out.println("Check console for logging output...");
        
        bookService.addBook("Effective Java");
        bookService.addBook("Java Concurrency in Practice");
        
        String book = bookService.getBook("B003");
        System.out.println("Retrieved: " + book);
        
        System.out.println("AOP Logging test completed!");
    }
}