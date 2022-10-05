package com.rydzwr.exception;

public class ServiceFactoryException extends RuntimeException {
    public ServiceFactoryException(String message) {
        super(message);
    }

    public ServiceFactoryException(Throwable cause) {
        super(cause);
    }
}