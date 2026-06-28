package com.zetta.ratevolut.rest.conversions;

import com.zetta.ratevolut.core.conversions.Conversion;
import com.zetta.ratevolut.core.conversions.ConversionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conversions")
public class ConversionController {
    private final ConversionService conversionService;

    @PostMapping
    public ResponseEntity<ConversionResponse> addConversion(
            @RequestHeader("X-Client-Id") UUID clientId,
            @Valid @RequestBody ConversionRequest request) {
        ConversionResponse response = conversionService.execute(new Conversion(clientId,
                request.sourceCurrency(),
                request.targetCurrency(),
                request.amount()));

        return ResponseEntity.ok(response);
    }
}
