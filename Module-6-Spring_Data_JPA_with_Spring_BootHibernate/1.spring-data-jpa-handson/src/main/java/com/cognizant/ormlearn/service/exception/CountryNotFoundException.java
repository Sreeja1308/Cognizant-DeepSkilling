package com.cognizant.ormlearn.service.exception;

public class CountryNotFoundException extends Exception {

    public CountryNotFoundException(String message) {
        super(message);
    }

    public CountryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}