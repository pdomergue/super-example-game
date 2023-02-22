package com.example.demo.domain.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GameExceptionHandler {

    @ExceptionHandler({GameNotFoundException.class})
    public ResponseEntity handleGameNotFoundException(GameNotFoundException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({GameBadRequestException.class})
    public ResponseEntity handleGameBadRequestException(GameBadRequestException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }
}
