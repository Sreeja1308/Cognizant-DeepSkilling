package com.example;

import java.util.concurrent.TimeUnit;

public class PerformanceTester {
    
    public void performTask(long durationMs) {
        try {
            Thread.sleep(durationMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task interrupted", e);
        }
    }
    
   
    public long computeHeavyTask(int iterations) {
        long sum = 0;
        for (int i = 0; i < iterations; i++) {
            sum += i * i;
            Math.sqrt(i);
        }
        return sum;
    }
    
    public void processData(int size) {
        for (int i = 0; i < size * 10; i++) {
            double result = Math.pow(i, 2) / Math.sqrt(i + 1);
        }
    }
}