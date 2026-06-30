package com.zetta.ratevolut.core.exceptions;

public class RateProviderUnavailableException extends RuntimeException {

    public RateProviderUnavailableException(String message) {
        super("Exchange rate provider is unavailable %s".formatted(message));
    }
}