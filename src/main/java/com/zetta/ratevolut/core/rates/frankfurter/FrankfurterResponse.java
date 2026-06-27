package com.zetta.ratevolut.core.rates.frankfurter;

import java.math.BigDecimal;

record FrankfurterResponse(
        String quote,
        BigDecimal rate
) {}