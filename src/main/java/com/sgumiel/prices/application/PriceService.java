package com.sgumiel.prices.application;

import java.util.Optional;

import com.sgumiel.prices.domain.Price;
import com.sgumiel.prices.domain.filter.PriceFilter;

public interface PriceService {

  Optional<Price> findByFilter(PriceFilter priceFilter);
}
