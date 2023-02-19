package com.example.demo.domain.exception;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(final String message) {
        super(message);
    }
}
