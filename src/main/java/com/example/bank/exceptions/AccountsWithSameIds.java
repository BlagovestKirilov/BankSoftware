package com.example.bank.exceptions;

public class AccountsWithSameIds extends RuntimeException{
    public AccountsWithSameIds(String message) {
        super(message);
    }
    public AccountsWithSameIds(String message, Throwable cause) {
        super(message, cause);
    }
}
