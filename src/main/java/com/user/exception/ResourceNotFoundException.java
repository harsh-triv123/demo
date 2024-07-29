package com.user.exception;

public class ResourceNotFoundException extends RuntimeException{
    // above class becomes custom class
    public ResourceNotFoundException(String message) {
        super(message);
    }
    // now u can throw exception where-ever u want
}

