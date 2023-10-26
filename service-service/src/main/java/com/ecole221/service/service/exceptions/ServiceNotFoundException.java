package com.ecole221.service.service.exceptions;

public class ServiceNotFoundException extends ServiceException {
    public ServiceNotFoundException(String message) {
        super(message);
    }

    public ServiceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
