package com.zetta.ratevolut.core.exceptions;

public class BalanceNotFoundException extends RuntimeException {
    public BalanceNotFoundException(String s) {
        super("Balance not found for client %s".formatted(s));
    }
}
