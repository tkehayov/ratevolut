package com.zetta.ratevolut.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class FrankfurterClientConfig {

    @Bean
    public RestClient frankfurterRestClient(
            @Value("${app.frankfurter.base-url}") String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}