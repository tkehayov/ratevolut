package com.zetta.ratevolut.rest.conversions;

import com.zetta.ratevolut.core.conversions.Conversion;
import com.zetta.ratevolut.core.conversions.ConversionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conversions")
public class ConversionController {
    private final ConversionService conversionService;

    @PostMapping
    public ResponseEntity<ConversionResponse> addConversion(
            @RequestHeader("X-Client-Id") UUID clientId,
            @RequestHeader("Idempotency-Key") UUID idempotencyKey,
            @Valid @RequestBody ConversionCreateRequest request) {
        ConversionResponse response = conversionService.create(
                new Conversion(clientId, request.sourceCurrency(), request.targetCurrency(), request.amount(), idempotencyKey)
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ConversionGetResponse>> getConversion(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String clientId
    ) {
        Pageable paging = PageRequest.of(page, size);
        Optional<UUID> clientUuid = (clientId != null && !clientId.isBlank())
                ? Optional.of(UUID.fromString(clientId))
                : Optional.empty();

        Page<ConversionGetResponse> conversions = conversionService.getConversions(clientUuid, paging);

        return ResponseEntity.ok(conversions);
    }
}
