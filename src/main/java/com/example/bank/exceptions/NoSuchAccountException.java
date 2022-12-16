package com.example.bank.exceptions;

public class NoSuchAccountException extends RuntimeException{
    public NoSuchAccountException(String message) {
        super(message);
    }

    public NoSuchAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
