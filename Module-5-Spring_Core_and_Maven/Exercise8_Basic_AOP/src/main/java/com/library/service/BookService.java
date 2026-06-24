package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    private BookRepository bookRepository;
    private String serviceName;
    private int maxBooks;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookRepository injected via Constructor");
    }
    
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookRepository injected via Setter");
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
        System.out.println("Service name set: " + serviceName);
    }
    
    public void setMaxBooks(int maxBooks) {
        this.maxBooks = maxBooks;
        System.out.println("Max books set: " + maxBooks);
    }
    
    public void addBook(String bookName) {
        System.out.println("Adding book: " + bookName + " (Service: " + serviceName + ")");
        bookRepository.saveBook(bookName);
    }
    
    public String getBook(String id) {
        return bookRepository.findBookById(id);
    }
}