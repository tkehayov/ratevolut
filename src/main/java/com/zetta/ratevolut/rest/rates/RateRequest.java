package com.zetta.ratevolut.rest.rates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RateRequest(
        @NotBlank
        @Size(min = 3, max = 3, message = "Currency code must be exactly 3 characters")
        @Pattern(regexp = "[A-Za-z]+", message = "Currency code must contain only letters")
        String from,

        @NotBlank
        @Size(min = 3, max = 3, message = "Currency code must be exactly 3 characters")
        @Pattern(regexp = "[A-Za-z]+", message = "Currency code must contain only letters")
        String to
) {
}