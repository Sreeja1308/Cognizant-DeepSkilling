package com.example;

public class PaymentService {
    private final ExternalApi externalApi;

    public PaymentService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public void processPayment(double amount) throws Exception {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > 10000) {
            throw new RuntimeException("Amount exceeds limit");
        }
        externalApi.processPayment(amount);
    }

    public void payWithRetry(double amount) {
        try {
            externalApi.processPayment(amount);
        } catch (Exception e) {
            
            try {
                externalApi.processPayment(amount);
            } catch (Exception ex) {
                throw new RuntimeException("Payment failed after retry", ex);
            }
        }
    }
}