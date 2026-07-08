package com.cognizant.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping("/{number}")
    public Map<String, Object> getAccountDetails(@PathVariable String number) {
        Map<String, Object> account = new HashMap<>();
        account.put("accountNumber", number);
        account.put("accountType", "Savings");
        account.put("balance", 50000.00);
        account.put("holderName", "John Doe");
        account.put("branch", "Downtown Branch");
        account.put("ifscCode", "CBIN0123456");
        account.put("status", "Active");
        return account;
    }

    // Additional endpoint for testing
    @GetMapping("/test")
    public String test() {
        return "Account Service is working!";
    }
}