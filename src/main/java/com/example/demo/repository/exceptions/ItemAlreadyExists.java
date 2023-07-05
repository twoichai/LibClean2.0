package com.example.demo.repository.exceptions;

import jakarta.persistence.EntityExistsException;

public class ItemAlreadyExists extends RuntimeException {
    public ItemAlreadyExists(String message){super(message);}
}
