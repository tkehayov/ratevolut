package com.zetta.ratevolut.core.clients;

import com.zetta.ratevolut.repositories.balance.BalanceRepository;
import com.zetta.ratevolut.rest.clients.BalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final BalanceRepository balanceRepository;

    public List<BalanceResponse> getBalances(UUID clientId) {
        return balanceRepository
                .findByClientId(clientId)
                .stream()
                .map(e ->
                        new BalanceResponse(
                                e.getCurrency(),
                                e.getAmount()))
                .toList();
    }
}
