package com.example.demo.repository.exceptions;

public class UnsuccessfulOperation extends RuntimeException{
    public UnsuccessfulOperation(String message){
        super(message);
    }
}
