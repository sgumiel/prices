package com.sgumiel.prices.infrastructure.rest.map;

import com.sgumiel.prices.domain.Price;
import com.sgumiel.prices.domain.filter.PriceFilter;
import com.sgumiel.prices.infrastructure.rest.model.PriceResponse;
import com.sgumiel.prices.infrastructure.rest.model.PricesRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceRestMapper {

  PriceFilter toDomain(PricesRequest source);

  PriceResponse toResponse(Price source);
}
