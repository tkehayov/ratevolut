package com.zetta.ratevolut.repositories.conversions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConversionRepository extends JpaRepository<ConversionEntity, UUID> {

}
