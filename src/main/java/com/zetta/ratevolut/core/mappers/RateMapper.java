package com.zetta.ratevolut.core.mappers;

import com.zetta.ratevolut.core.rates.Rate;
import com.zetta.ratevolut.rest.rates.RateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RateMapper {

    RateResponse toResponse(Rate rate);
}