package com.zetta.ratevolut.core.conversions;

import com.zetta.ratevolut.BaseIntegrationTest;
import com.zetta.ratevolut.core.rates.Rate;
import com.zetta.ratevolut.core.rates.RateService;
import com.zetta.ratevolut.rest.conversions.ConversionGetResponse;
import com.zetta.ratevolut.rest.conversions.ConversionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.zetta.ratevolut.core.exceptions.BalanceNotFoundException;
import com.zetta.ratevolut.core.exceptions.InsufficientFundsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ConversionServiceTest extends BaseIntegrationTest {
    @Autowired
    private ConversionService conversionService;
    @MockitoBean
    private RateService rateService;
    private UUID idempotencyKey;

    @BeforeEach
    public void setup() {
        idempotencyKey = UUID.randomUUID();
    }

    @Test
    public void create() {
        Rate mockRate = new Rate("CAD", "USD", new BigDecimal("10.00"));
        UUID clientId = UUID.fromString("4b43cf01-fd6b-4fbd-b749-020ed1444552");
        Conversion conversion = new Conversion(clientId, "CAD", "USD", new BigDecimal("5"), idempotencyKey);

        when(rateService.getRate("CAD", "USD")).thenReturn(mockRate);
        ConversionResponse actual = conversionService.create(conversion);

        assertEquals(new BigDecimal("245.0000"), actual.updatedBalances().sourceBalance());
        assertEquals(new BigDecimal("1550.7500"), actual.updatedBalances().targetBalance());
        assertEquals(new BigDecimal("10.00"), actual.rate());
        assertEquals("USD", actual.targetCurrency());
        assertEquals(new BigDecimal("50.00"), actual.targetAmount());
        assertEquals("CAD", actual.sourceCurrency());
        assertEquals(new BigDecimal("5"), actual.sourceAmount());
    }

    @Test
    public void createBalanceNotFound() {
        UUID nonExistentClientId = UUID.randomUUID();
        Conversion conversion = new Conversion(nonExistentClientId, "CAD", "USD", new BigDecimal("5"), idempotencyKey);

        assertThrows(BalanceNotFoundException.class, () -> conversionService.create(conversion));
    }

    @Test
    public void createInsufficientFunds() {
        Rate mockRate = new Rate("CAD", "USD", new BigDecimal("10.00"));
        UUID clientId = UUID.fromString("4b43cf01-fd6b-4fbd-b749-020ed1444552");
        Conversion conversion = new Conversion(clientId, "CAD", "USD", new BigDecimal("10000"), idempotencyKey);

        when(rateService.getRate("CAD", "USD")).thenReturn(mockRate);

        assertThrows(InsufficientFundsException.class, () -> conversionService.create(conversion));
    }


    @Test
    public void createWithIdempotency() {
        Rate mockRate = new Rate("CAD", "USD", new BigDecimal("10.00"));
        UUID clientId = UUID.fromString("4b43cf01-fd6b-4fbd-b749-020ed1444552");
        Conversion conversion = new Conversion(clientId, "CAD", "USD", new BigDecimal("5"), idempotencyKey);

        when(rateService.getRate("CAD", "USD")).thenReturn(mockRate);

        ConversionResponse first = conversionService.create(conversion);
        ConversionResponse second = conversionService.create(conversion);

        assertEquals(first.transactionId(), second.transactionId());
        assertEquals(first.sourceAmount(), second.sourceAmount());
        assertEquals(first.targetAmount(), second.targetAmount());
    }

    @Test
    public void getConversions() {
        UUID clientId = UUID.fromString("4b43cf01-fd6b-4fbd-b749-020ed1444552");
        Pageable pageable = PageRequest.of(0, 3);
        Page<ConversionGetResponse> conversions = conversionService.getConversions(Optional.of(clientId), pageable);
        List<ConversionGetResponse> content = conversions.getContent();
        ConversionGetResponse actual = conversions.getContent().get(0);

        assertEquals(content.size(), 3);
        assertEquals(new BigDecimal("0.6100"), actual.rate());
        assertEquals("EUR", actual.targetCurrency());
        assertEquals(new BigDecimal("6.1000"), actual.targetAmount());
        assertEquals("AUD", actual.sourceCurrency());
        assertEquals(new BigDecimal("10.0000"), actual.sourceAmount());
    }

}