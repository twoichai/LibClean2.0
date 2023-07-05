package com.example.demo.repository.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){super(message);}

}
