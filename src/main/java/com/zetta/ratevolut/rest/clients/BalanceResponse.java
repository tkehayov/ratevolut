package com.zetta.ratevolut.rest.clients;

import java.math.BigDecimal;

public record BalanceResponse(String currency, BigDecimal balance) {
}
