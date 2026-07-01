package com.example;

public class OrderService {
    private final ExternalApi externalApi;

    public OrderService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public int getOrderCount() {
        return externalApi.getOrderCount();
    }

    public void processOrder(int orderId) {
        int count = externalApi.getOrderCount();
        if (count < 5) {
            externalApi.saveUser("Order-" + orderId);
        } else {
            throw new RuntimeException("Too many orders");
        }
    }
}