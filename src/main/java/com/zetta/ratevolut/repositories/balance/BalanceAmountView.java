package com.zetta.ratevolut.repositories.balance;

import java.math.BigDecimal;

public interface BalanceAmountView {
    BigDecimal getSourceBalance();
    BigDecimal getTargetBalance();
}