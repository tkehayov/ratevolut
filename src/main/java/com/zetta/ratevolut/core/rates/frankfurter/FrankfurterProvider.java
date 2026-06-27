package com.zetta.ratevolut.core.rates.frankfurter;

import com.zetta.ratevolut.core.exceptions.RateNotFoundException;
import com.zetta.ratevolut.core.rates.FxRateProvider;
import com.zetta.ratevolut.core.rates.Rate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.math.RoundingMode;
import java.util.List;

@Component
@RequiredArgsConstructor
@Primary
public class FrankfurterProvider implements FxRateProvider {
    private static final int RATE_SCALE = 2;
    private static final RoundingMode ROUNDING = RoundingMode.HALF_UP;

    private final RestClient frankfurterRestClient;

    @Override
    public Rate fetchRate(String from, String to) {
        List<FrankfurterResponse> response;
        try {
            response = frankfurterRestClient.get()
                    .uri("/rates?base={from}", from)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
        } catch (HttpClientErrorException.UnprocessableContent e) {
            throw new RateNotFoundException(from, to);
        }

        return response.stream()
                .filter(r -> to.equals(r.quote()))
                .findFirst()
                .map(r -> new Rate(from, to, r.rate().setScale(RATE_SCALE, ROUNDING)))
                .orElseThrow(() -> new RateNotFoundException(from, to));
    }
}
