package com.sgumiel.prices.infrastructure.db.map;

import java.util.List;

import com.sgumiel.prices.domain.Price;
import com.sgumiel.prices.infrastructure.db.model.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceJpaMapper {

  Price toDomain(PriceEntity source);

  List<Price> toDomain(List<PriceEntity> source);
}
