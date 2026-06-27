package com.zetta.ratevolut.rest.rates;

import com.zetta.ratevolut.core.rates.Rate;
import com.zetta.ratevolut.core.mappers.RateMapper;
import com.zetta.ratevolut.core.rates.RateService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/rates")
public class FxRateController {

    private final RateService rateService;
    private final RateMapper rateMapper;

    @GetMapping
    public ResponseEntity<RateResponse> getRate(@Valid RateRequest request) {
        Rate rate = rateService.getRate(request.from(), request.to());

        return ResponseEntity.ok(rateMapper.toResponse(rate));
    }
}