package com.zetta.ratevolut.core.conversions;

import com.zetta.ratevolut.core.exceptions.BalanceNotFoundException;
import com.zetta.ratevolut.core.exceptions.InsufficientFundsException;
import com.zetta.ratevolut.core.rates.Rate;
import com.zetta.ratevolut.core.rates.RateService;
import com.zetta.ratevolut.repositories.accounts.AccountEntity;
import com.zetta.ratevolut.repositories.balance.BalanceAmountView;
import com.zetta.ratevolut.repositories.balance.BalanceRepository;
import com.zetta.ratevolut.repositories.conversions.ConversionEntity;
import com.zetta.ratevolut.repositories.conversions.ConversionRepository;
import com.zetta.ratevolut.rest.conversions.ConversionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConversionService {
    private final RateService rateService;
    private final BalanceRepository balanceRepository;
    private final ConversionRepository conversionRepository;

    @Transactional
    public ConversionResponse execute(Conversion conversion) {
        BalanceAmountView balances = balanceRepository
                .findAmountsByClientIdAndCurrencies(conversion.clientId(), conversion.sourceCurrency(), conversion.targetCurrency())
                .orElseThrow(() ->  new BalanceNotFoundException(conversion.clientId().toString()));
        Rate rate = rateService.getRate(conversion.sourceCurrency(), conversion.targetCurrency());

        BigDecimal debitAmount = conversion.amount();
        BigDecimal fromCurrentAmount = balances.getSourceBalance();
        BigDecimal toCurrentAmount = balances.getTargetBalance();
        BigDecimal exchangeRate = rate.rate();

        if (fromCurrentAmount.compareTo(debitAmount) < 0) {
            throw new InsufficientFundsException(conversion.clientId().toString());
        }

        BigDecimal creditAmount = debitAmount.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal newSourceBalance = fromCurrentAmount.subtract(debitAmount);
        BigDecimal newTargetBalance = toCurrentAmount.add(creditAmount);

        balanceRepository.updateBalanceAmount(conversion.clientId(), conversion.sourceCurrency(), newSourceBalance);
        balanceRepository.updateBalanceAmount(conversion.clientId(), conversion.targetCurrency(), newTargetBalance);

        AccountEntity clientRef = AccountEntity.builder()
                .id(conversion.clientId())
                .build();

        ConversionEntity conversionEntity = ConversionEntity.builder()
                .client(clientRef)
                .sourceCurrency(conversion.sourceCurrency())
                .sourceAmount(debitAmount)
                .targetCurrency(conversion.targetCurrency())
                .targetAmount(creditAmount)
                .exchangeRate(exchangeRate)
                .createdAt(LocalDateTime.now())
                .build();

        ConversionEntity savedConversion = conversionRepository.save(conversionEntity);

        return new ConversionResponse(
                savedConversion.getId(),
                debitAmount,
                conversion.sourceCurrency(),
                creditAmount,
                conversion.targetCurrency(),
                exchangeRate,
                savedConversion.getCreatedAt(),
                new ConversionResponse.UpdatedBalances(newSourceBalance, newTargetBalance)
        );
    }
}