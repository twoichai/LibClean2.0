package com.example.demo.repository.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ItemNotAvailableException extends RuntimeException {
    public ItemNotAvailableException(String message){
        super(message);
    }
}
