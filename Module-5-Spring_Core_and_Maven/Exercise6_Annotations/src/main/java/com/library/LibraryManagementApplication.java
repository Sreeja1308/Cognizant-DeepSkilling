package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        System.out.println("=== Testing Annotation-based Configuration ===");
        
        BookService bookService = context.getBean("bookService", BookService.class);
        
        bookService.addBook("Spring Annotations Guide");
        bookService.addBook("Java Best Practices");
        
        String book = bookService.getBook("B004");
        System.out.println("Retrieved: " + book);
        
        System.out.println("Annotation configuration test completed!");
    }
}