package com.ecole221.compte.transaction.service.exception.compte;

public class CompteNotFoundException extends CompteException {
    public CompteNotFoundException(String message) {
        super(message);
    }

    public CompteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
