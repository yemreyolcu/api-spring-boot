package com.example.springbootapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SQLExceptionH extends RuntimeException{
    public SQLExceptionH(String message) {
        super(message);
    }
}
