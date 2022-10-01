package com.rydzwr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {EasterEggException.class})
    public ResponseEntity<Object> handleException(EasterEggException e)
    {
        HttpStatus badRequest = HttpStatus.I_AM_A_TEAPOT;

        Exception exception = new Exception(
                e.getMessage(),
                e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exception, badRequest);
    }

    @ExceptionHandler(value = {MissingParameterException.class})
    public ResponseEntity<Object> handleException(MissingParameterException e)
    {
        HttpStatus badRequest = HttpStatus.NOT_FOUND;

        Exception exception = new Exception(
                e.getMessage(),
                e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exception, badRequest);
    }
}
