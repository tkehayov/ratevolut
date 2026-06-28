package com.zetta.ratevolut.rest.clients;

import com.zetta.ratevolut.core.clients.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientsController {
    private final ClientService clientService;

    @GetMapping(value = "/{clientId}/balances")
    public ResponseEntity<List<BalanceResponse>> getConversion(
            @PathVariable UUID clientId
    ) {
        List<BalanceResponse> balances = clientService.getBalances(clientId);

        return ResponseEntity.ok(balances);
    }
}
