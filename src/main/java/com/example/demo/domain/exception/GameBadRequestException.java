package com.example.demo.domain.exception;

public class GameBadRequestException extends RuntimeException{

    public GameBadRequestException(final String message) {
        super(message);
    }
}
