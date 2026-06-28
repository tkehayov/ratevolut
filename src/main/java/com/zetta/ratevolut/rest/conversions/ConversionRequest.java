package com.zetta.ratevolut.rest.conversions;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ConversionRequest(@NotBlank(message = "Source currency is required")
                                @Pattern(regexp = "[A-Za-z]+", message = "Currency code must contain only letters")
                                String sourceCurrency,

                                @NotBlank(message = "Target currency is required")
                                @Pattern(regexp = "[A-Za-z]+", message = "Currency code must contain only letters")
                                String targetCurrency,

                                @NotNull(message = "Amount is required")
                                @Positive(message = "Amount must be greater than zero")
                                BigDecimal amount) {
}
