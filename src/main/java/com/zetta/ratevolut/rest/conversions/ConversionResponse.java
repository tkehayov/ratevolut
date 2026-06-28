package com.zetta.ratevolut.rest.conversions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ConversionResponse(
        UUID transactionId,
        BigDecimal sourceAmount,
        String sourceCurrency,
        BigDecimal targetAmount,
        String targetCurrency,
        BigDecimal rate,
        LocalDateTime timestamp,
        UpdatedBalances updatedBalances
) {
    public record UpdatedBalances(
            BigDecimal sourceBalance,
            BigDecimal targetBalance
    ) {
    }
}
