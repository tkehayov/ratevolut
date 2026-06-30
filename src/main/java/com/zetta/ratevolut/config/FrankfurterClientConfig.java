package com.zetta.ratevolut.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class FrankfurterClientConfig {

    @Bean
    public RestClient frankfurterRestClient(
            @Value("${app.frankfurter.base-url}") String baseUrl) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        requestFactory.setConnectTimeout((int) Duration.ofSeconds(5).toMillis());
        requestFactory.setReadTimeout((int) Duration.ofSeconds(3).toMillis());

        return RestClient.builder()
                .requestFactory(requestFactory)
                .baseUrl(baseUrl)
                .build();
    }
}