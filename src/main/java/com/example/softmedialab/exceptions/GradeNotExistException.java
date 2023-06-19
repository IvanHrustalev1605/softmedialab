package com.example.softmedialab.exceptions;

public class GradeNotExistException extends RuntimeException{
    public GradeNotExistException() {
    }
    public GradeNotExistException(String message) {
        super(message);
    }
}
