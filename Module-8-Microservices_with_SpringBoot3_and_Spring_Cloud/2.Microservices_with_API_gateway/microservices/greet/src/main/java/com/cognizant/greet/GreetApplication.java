package com.cognizant.greet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.cognizant.greet"})  // Explicit component scan
public class GreetApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreetApplication.class, args);
        System.out.println("Greet Service started successfully!");
        System.out.println("Access at: http://localhost:8082/greet");
        System.out.println("Access at: http://localhost:8082/greet/hello");
    }
}