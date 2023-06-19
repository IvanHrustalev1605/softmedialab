package com.example.softmedialab.exceptions;

public class StudentNotExistException extends RuntimeException{
    public StudentNotExistException(String message) {
        super(message);
    }
}
