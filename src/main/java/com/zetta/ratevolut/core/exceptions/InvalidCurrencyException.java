package com.zetta.ratevolut.core.exceptions;

public class InvalidCurrencyException extends RuntimeException {

    public InvalidCurrencyException(String currency) {
        super("Invalid currency: %s".formatted(currency));
    }
}