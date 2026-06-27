package com.zetta.ratevolut.core.exceptions;

public class RateNotFoundException extends RuntimeException {

    public RateNotFoundException(String from, String to) {
        super("No rate found for %s → %s".formatted(from, to));
    }
}