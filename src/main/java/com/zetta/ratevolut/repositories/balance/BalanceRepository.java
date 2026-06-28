package com.zetta.ratevolut.repositories.balance;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            SELECT 
                balanceFrom.amount AS sourceBalance,
                balanceTo.amount   AS targetBalance
            FROM BalanceEntity balanceFrom
            LEFT JOIN BalanceEntity balanceTo 
                ON balanceFrom.client.id = balanceTo.client.id 
               AND balanceTo.currency = :toCurrency
            WHERE balanceFrom.client.id = :clientId 
              AND balanceFrom.currency = :fromCurrency
            """)
    Optional<BalanceAmountView> findAmountsByClientIdAndCurrencies(
            @Param("clientId") UUID clientId,
            @Param("fromCurrency") String fromCurrency,
            @Param("toCurrency") String toCurrency
    );

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        UPDATE BalanceEntity b 
        SET b.amount = :newAmount 
        WHERE b.client.id = :clientId AND b.currency = :currency
    """)
    void updateBalanceAmount(
            @Param("clientId") UUID clientId,
            @Param("currency") String currency,
            @Param("newAmount") BigDecimal newAmount
    );

    List<BalanceEntity> findByClientId(UUID clientId);
}
