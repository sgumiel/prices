package com.sgumiel.prices.infrastructure.db.jpa.specification.impl;

import com.sgumiel.prices.domain.filter.PriceFilter;
import com.sgumiel.prices.infrastructure.db.jpa.specification.PriceSpecificationService;
import com.sgumiel.prices.infrastructure.db.model.PriceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PriceSpecificationServiceImpl implements PriceSpecificationService {

  @Override
  public Specification<PriceEntity> createDefaultSpecification(PriceFilter priceFilter) {
    // All attributes of priceFilter are mandatory

    log.debug("Creating spec of price entity. Filter: {}", priceFilter);
    Specification<PriceEntity> spec = Specification.where(null);

    spec = spec.and((root, cq, cb) -> cb.equal(root.get("brandId"), priceFilter.getBrandId()));
    spec = spec.and((root, cq, cb) -> cb.equal(root.get("productId"), priceFilter.getProductId()));
    spec = spec.and((root, cq, cb) -> cb.and(
            cb.greaterThanOrEqualTo(root.get("endDate"), priceFilter.getDate()),
            cb.lessThanOrEqualTo(root.get("startDate"), priceFilter.getDate())));

    return spec;
  }
}
