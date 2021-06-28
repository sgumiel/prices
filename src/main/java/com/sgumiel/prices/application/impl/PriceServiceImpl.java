package com.sgumiel.prices.application.impl;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sgumiel.prices.application.PriceService;
import com.sgumiel.prices.domain.Price;
import com.sgumiel.prices.domain.filter.PriceFilter;
import com.sgumiel.prices.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

  private final PriceRepository priceRepository;

  @Override
  public Optional<Price> findByFilter(PriceFilter priceFilter) {

    log.debug("Find by filter: {}", priceFilter);

    final var priceList =this.priceRepository.findByFilter(priceFilter);
    log.debug("Prices found: {}", priceList.size());

    // the bigger the number, the more priority
    final var priceListOrderByPriority = priceList.stream()
            .sorted(Comparator.comparing(Price::getPriority).reversed())
            .collect(Collectors.toList());
    log.debug("Price list ordered by priority");

    final var priceOp = priceListOrderByPriority.stream().findFirst();
    log.debug("Price found: {}", priceOp.isPresent());

    return priceOp;
  }
}
