package Exercise6_LibraryManagement;

import java.util.*;

public class LibraryManagementSystem {
    private Book[] books;
    
    public LibraryManagementSystem(Book[] books) {
        this.books = books;
    }
    
    public Book linearSearchByTitle(String title) {
        for (Book book : books) {
            if (book != null && book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
    
    public Book binarySearchByTitle(String title) {
        int left = 0;
        int right = books.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (books[mid] == null) {
                return null;
            }
            
            int comparison = books[mid].getTitle().compareToIgnoreCase(title);
            if (comparison == 0) {
                return books[mid];
            }
            
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        Book[] books = {
            new Book(1, "Java Programming", "John Smith"),
            new Book(2, "Python Basics", "Jane Doe"),
            new Book(3, "Data Structures", "Bob Johnson"),
            new Book(4, "Algorithms", "Alice Brown"),
            new Book(5, "Web Development", "Charlie Wilson")
        };
        
        Arrays.sort(books, Comparator.comparing(Book::getTitle));
        
        LibraryManagementSystem lms = new LibraryManagementSystem(books);
        
        System.out.println("Linear Search for 'Data Structures':");
        System.out.println(lms.linearSearchByTitle("Data Structures"));
        
        System.out.println("\nBinary Search for 'Data Structures':");
        System.out.println(lms.binarySearchByTitle("Data Structures"));
        
        System.out.println("\nLinear Search for 'Nonexistent Book':");
        System.out.println(lms.linearSearchByTitle("Nonexistent Book"));
        
        System.out.println("\nBinary Search for 'Nonexistent Book':");
        System.out.println(lms.binarySearchByTitle("Nonexistent Book"));
    }
}