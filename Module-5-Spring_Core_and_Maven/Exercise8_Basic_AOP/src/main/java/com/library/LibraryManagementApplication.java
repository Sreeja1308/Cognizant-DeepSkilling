package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        System.out.println("=== Testing Basic AOP Functionality ===");
        System.out.println("Check console for comprehensive logging output...");
        System.out.println();
        
        BookService bookService = context.getBean("bookService", BookService.class);
        
        bookService.addBook("Spring AOP Deep Dive");
        bookService.addBook("Aspect-Oriented Programming");
        
        String book = bookService.getBook("B006");
        System.out.println("Retrieved: " + book);
        
        System.out.println("\nAOP test completed!");
        
        ((ClassPathXmlApplicationContext) context).close();
    }
}