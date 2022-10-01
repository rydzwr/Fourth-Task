package com.rydzwr.exception;

public class MissingParameterException extends RuntimeException
{
    public MissingParameterException(String message)
    {
        super(message);
    }

    public MissingParameterException(Throwable cause)
    {
        super(cause);
    }
}