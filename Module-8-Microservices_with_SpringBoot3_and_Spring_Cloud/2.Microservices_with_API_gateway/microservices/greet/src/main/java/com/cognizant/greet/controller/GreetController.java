package com.cognizant.greet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/greet")
public class GreetController {

    @GetMapping
    public String greet() {
        return "Hello World!!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Greet Service!";
    }

    @GetMapping("/info")
    public Map<String, String> info() {
        Map<String, String> info = new HashMap<>();
        info.put("service", "Greet Service");
        info.put("status", "Running");
        info.put("port", "8082");
        info.put("message", "Hello World!!");
        return info;
    }

    @GetMapping("/welcome")
    public Map<String, Object> welcome() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Greet Service");
        response.put("timestamp", System.currentTimeMillis());
        response.put("version", "1.0.0");
        return response;
    }
}