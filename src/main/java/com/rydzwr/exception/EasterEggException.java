package com.rydzwr.exception;

public class EasterEggException extends RuntimeException
{
    public EasterEggException(String message)
    {
        super(message);
    }

    public EasterEggException(Throwable cause)
    {
        super(cause);
    }
}