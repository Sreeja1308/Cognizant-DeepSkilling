package com.example;

public class ExceptionThrower {
    
    public void throwException(String type) {
        if (type == null) {
            throw new NullPointerException("Input cannot be null");
        }
        
        switch (type) {
            case "illegal":
                throw new IllegalArgumentException("Illegal argument provided");
            case "null":
                throw new NullPointerException("Null value encountered");
            case "runtime":
                throw new RuntimeException("Runtime exception occurred");
            default:
                throw new RuntimeException("Unknown exception type: " + type);
        }
    }
    
    public void throwCheckedException() throws Exception {
        throw new Exception("This is a checked exception");
    }
}