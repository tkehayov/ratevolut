package com.zetta.ratevolut.rest.conversions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ConversionGetResponse(
        UUID transactionId,
        BigDecimal sourceAmount,
        String sourceCurrency,
        BigDecimal targetAmount,
        String targetCurrency,
        BigDecimal rate,
        LocalDateTime timestamp
) {}
