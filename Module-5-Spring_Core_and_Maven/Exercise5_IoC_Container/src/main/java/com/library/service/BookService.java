package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    
    private BookRepository bookRepository;
    
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookRepository injected via setter");
    }
    
    public void addBook(String bookName) {
        System.out.println("Adding book: " + bookName);
        bookRepository.saveBook(bookName);
    }
    
    public String getBook(String id) {
        return bookRepository.findBookById(id);
    }
}