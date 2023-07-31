package com.ecole221.client.paiement.service.exception.client;

public class ClientNotFoundException extends ClientException {
    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
