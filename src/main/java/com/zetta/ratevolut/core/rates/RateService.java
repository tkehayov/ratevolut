package com.zetta.ratevolut.core.rates;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateService {
    private final FxRateProvider primaryProvider;

    public Rate getRate(String from, String to) {
        String fromCode = from.toUpperCase();
        String toCode = to.toUpperCase();

        return primaryProvider.fetchRate(fromCode, toCode);
    }
}