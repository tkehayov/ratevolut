package com.zetta.ratevolut.core.rates;

import java.math.BigDecimal;

public record Rate(String from, String to, BigDecimal rate) {}