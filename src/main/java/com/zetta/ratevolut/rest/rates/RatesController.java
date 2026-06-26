package com.zetta.ratevolut.rest.rates;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rates")
public class RatesController {
    @GetMapping(value = "/{id}")
    public ResponseEntity getProduct(@PathVariable Long id) {

        return null;
    }
}
