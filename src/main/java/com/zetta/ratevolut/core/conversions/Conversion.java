package com.zetta.ratevolut.core.conversions;

import java.math.BigDecimal;
import java.util.UUID;

public record Conversion(UUID clientId,
                         String sourceCurrency,
                         String targetCurrency,
                         BigDecimal amount) {
}
