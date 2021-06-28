package com.sgumiel.prices.infrastructure.db.jpa.specification;

import com.sgumiel.prices.domain.filter.PriceFilter;
import com.sgumiel.prices.infrastructure.db.model.PriceEntity;
import org.springframework.data.jpa.domain.Specification;

public interface PriceSpecificationService {

  Specification<PriceEntity> createDefaultSpecification(PriceFilter priceFilter);
}