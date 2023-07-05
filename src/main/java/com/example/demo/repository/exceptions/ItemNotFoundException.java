package com.example.demo.repository.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message){
        super(message);
    }
}
