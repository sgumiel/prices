package com.sgumiel.prices.infrastructure.db.repository.impl;

import java.util.List;

import com.sgumiel.prices.domain.Price;
import com.sgumiel.prices.domain.filter.PriceFilter;
import com.sgumiel.prices.domain.repository.PriceRepository;
import com.sgumiel.prices.infrastructure.db.jpa.PriceJpaRepository;
import com.sgumiel.prices.infrastructure.db.jpa.specification.PriceSpecificationService;
import com.sgumiel.prices.infrastructure.db.map.PriceJpaMapper;
import com.sgumiel.prices.infrastructure.db.model.PriceEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

  private final PriceSpecificationService priceSpecificationService;
  private final PriceJpaRepository priceJpaRepository;
  private final PriceJpaMapper priceJpaMapper;

  @Override
  public List<Price> findByFilter(PriceFilter priceFilter) {

    log.debug("Find by filter: {}", priceFilter);

    final Specification<PriceEntity> priceSpec = this.priceSpecificationService.createDefaultSpecification(priceFilter);

    final List<PriceEntity> priceEntityList = this.priceJpaRepository.findAll(priceSpec);
    log.debug("Prices found: {}", priceEntityList.size());

    final var priceList = this.priceJpaMapper.toDomain(priceEntityList);
    log.debug("Price entity list mapped to domain");

    return priceList;
  }
}
