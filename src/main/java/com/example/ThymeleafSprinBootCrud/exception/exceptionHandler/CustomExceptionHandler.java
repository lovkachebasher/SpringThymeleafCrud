package com.example.ThymeleafSprinBootCrud.exception.exceptionHandler;

import com.example.ThymeleafSprinBootCrud.exception.GlobalException;
import com.example.ThymeleafSprinBootCrud.exception.NotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<String> handle(GlobalException e) {

        logger.error(e.getMessage(), e);
        return switch (e) {
            case NotExistException ne -> getResponseWithStatusCode(ne, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>("something wrong", HttpStatus.I_AM_A_TEAPOT);
        };
    }


    private ResponseEntity<String> getResponseWithStatusCode(GlobalException globalException, HttpStatus httpStatus) {
        return new ResponseEntity<>(globalException.getMessage(), httpStatus);
    }
}
