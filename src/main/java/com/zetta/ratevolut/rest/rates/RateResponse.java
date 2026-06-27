package com.zetta.ratevolut.rest.rates;

import java.math.BigDecimal;

public record RateResponse(String from, String to, BigDecimal rate) {}