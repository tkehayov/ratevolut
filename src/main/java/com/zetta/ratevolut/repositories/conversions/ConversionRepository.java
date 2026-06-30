package com.zetta.ratevolut.repositories.conversions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConversionRepository extends JpaRepository<ConversionEntity, UUID> {
    @Query("""
                SELECT c FROM ConversionEntity c
                WHERE (:clientId IS NULL OR c.client.id = :clientId)
            """)
    Page<ConversionEntity> findAllConversions(@Param("clientId") UUID clientId, Pageable pageable);

    Optional<ConversionEntity> findByIdempotencyKey(String idempotencyKey);
}
