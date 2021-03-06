package com.revature.exception;

public class ClientAlreadyExistsException extends Exception{
    public ClientAlreadyExistsException() {
    }

    public ClientAlreadyExistsException(String message) {
        super(message);
    }

    public ClientAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ClientAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
