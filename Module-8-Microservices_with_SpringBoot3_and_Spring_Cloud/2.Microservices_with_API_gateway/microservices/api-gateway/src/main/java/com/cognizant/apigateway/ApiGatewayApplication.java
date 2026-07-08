package com.cognizant.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
        System.out.println("API Gateway started successfully!");
        System.out.println("Access at: http://localhost:9090");
        System.out.println("");
        System.out.println("Available Routes:");
        System.out.println("  http://localhost:9090/greet-service/greet");
        System.out.println("  http://localhost:9090/account-service/accounts/123");
        System.out.println("  http://localhost:9090/loan-service/loans/123");
    }
}