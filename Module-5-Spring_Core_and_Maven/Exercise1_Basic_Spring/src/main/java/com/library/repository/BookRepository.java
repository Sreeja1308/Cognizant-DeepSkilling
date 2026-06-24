package com.library.repository;

@Repository
public class BookRepository {
    
    public String findBookById(String id) {
        return "Book: " + id + " - 'Spring Framework Guide'";
    }
    
    public void saveBook(String bookName) {
        System.out.println("Book saved: " + bookName);
    }
}