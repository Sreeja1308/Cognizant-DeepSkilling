package com.library;

import com.library.service.BookService;

public class LibraryManagementApplication {
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        
        BookService bookService = context.getBean("bookService", BookService.class);
        
        bookService.addBook("Spring in Action");
        String book = bookService.getBook("B001");
        System.out.println(book);
        
        System.out.println("Library Management Application started successfully!");
    }
}