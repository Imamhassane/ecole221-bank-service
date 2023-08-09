package com.ecole221.compte.transaction.service.exception.compte;

public class CompteException extends RuntimeException {
    public CompteException(String message) {
        super(message);
    }

    public CompteException(String message, Throwable cause) {
        super(message, cause);
    }
}