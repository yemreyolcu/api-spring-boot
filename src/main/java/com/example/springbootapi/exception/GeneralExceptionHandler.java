package com.example.springbootapi.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);


    @ExceptionHandler(value = { com.example.springbootapi.exception.SQLExceptionH.class })
    public ResponseEntity<Object> handleSQLException(com.example.springbootapi.exception.SQLExceptionH ex) {

        logger.error("Exception has found!",ex.getMessage());

        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);

    }



}
