package com.proyecto.grupo_3.error_handling.exception;

public class NotFoundException extends RuntimeException
{
    public NotFoundException(String message)
    {
        super(message);
    }

    public NotFoundException(Throwable cause)
    {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
