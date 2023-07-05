package com.example.demo.repository.exceptions;


public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String message){ super((message));}
}
