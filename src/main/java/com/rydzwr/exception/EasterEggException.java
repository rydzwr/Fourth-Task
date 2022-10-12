package com.rydzwr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
public class EasterEggException extends RuntimeException {
    public EasterEggException(String message) {
        super(message);
    }

    public EasterEggException(Throwable cause) {
        super(cause);
    }
}