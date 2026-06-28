package com.zetta.ratevolut.core.exceptions;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String m) {
        super("Insufficient funds for client %s".formatted(m));
    }
}