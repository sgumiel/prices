package com.sgumiel.prices.domain.repository;

import java.util.List;

import com.sgumiel.prices.domain.Price;
import com.sgumiel.prices.domain.filter.PriceFilter;

public interface PriceRepository {

  List<Price> findByFilter(PriceFilter priceFilter);
}
