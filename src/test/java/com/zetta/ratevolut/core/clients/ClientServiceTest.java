package com.zetta.ratevolut.core.clients;

import com.zetta.ratevolut.BaseIntegrationTest;
import com.zetta.ratevolut.rest.clients.BalanceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ClientServiceTest extends BaseIntegrationTest {
    @Autowired
    private ClientService clientService;

    @Test
    public void getBalances() {
        UUID clientId = UUID.fromString("1b8b13d9-76d3-4b54-9322-4bda96cbb2d4");
        List<BalanceResponse> actual = clientService.getBalances(clientId);

        assertEquals(2, actual.size());
        assertEquals("AUD", actual.get(0).currency());
        assertEquals("EUR", actual.get(1).currency());
        assertEquals(new BigDecimal("100.0000"), actual.get(0).balance());
        assertEquals(new BigDecimal("1200.5000"), actual.get(1).balance());
    }

    @Test
    public void getBalancesWithNotExistClientId() {
        UUID clientId = UUID.fromString("1b8b13d9-76d3-4b54-9322-666");
        List<BalanceResponse> actual = clientService.getBalances(clientId);

        assertEquals(0, actual.size());
    }
}