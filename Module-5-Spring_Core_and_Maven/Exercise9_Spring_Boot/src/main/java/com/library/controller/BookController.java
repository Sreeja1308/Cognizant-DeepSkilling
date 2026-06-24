package com.library.controller;

import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id)
            .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    
    @GetMapping("/search/author")
    public ResponseEntity<List<Book>> searchByAuthor(@RequestParam String author) {
        List<Book> books = bookService.searchByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    @GetMapping("/search/title")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {
        List<Book> books = bookService.searchByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    @GetMapping("/search/year")
    public ResponseEntity<List<Book>> searchByYear(@RequestParam Integer year) {
        List<Book> books = bookService.searchByYear(year);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/search/price")
    public ResponseEntity<List<Book>> searchByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<Book> books = bookService.searchByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}