package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    
    public static void main(String[] args) {
       
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        System.out.println("=== Testing Spring IoC Container ===");
        
        BookService service1 = context.getBean("bookService", BookService.class);
        BookService service2 = context.getBean("bookService", BookService.class);
        
        System.out.println("service1 == service2: " + (service1 == service2));
        System.out.println("Different instances: " + (service1 != service2));
        
        service1.addBook("Spring Boot in Action");
        service2.addBook("Spring Cloud");
        
        System.out.println("IoC Container test completed!");
        
        ((ClassPathXmlApplicationContext) context).close();
    }
}