package com.revature.exception;

public class AccountDoesNotBelongToClient extends Exception{
    public AccountDoesNotBelongToClient() {
    }

    public AccountDoesNotBelongToClient(String message) {
        super(message);
    }

    public AccountDoesNotBelongToClient(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDoesNotBelongToClient(Throwable cause) {
        super(cause);
    }

    public AccountDoesNotBelongToClient(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
