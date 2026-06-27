package com.zetta.ratevolut.core.rates;

public interface FxRateProvider {
    Rate fetchRate(String from, String to);
}
