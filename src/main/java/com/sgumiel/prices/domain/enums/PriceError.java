package com.sgumiel.prices.domain.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PriceError {

  PRICE_MANDATORY_DATE("prices.001"),
  PRICE_MANDATORY_BRAND_ID("prices.002"),
  PRICE_MANDATORY_PRODUCT_ID("prices.003");

  private final String code;

  public static PriceError valueOfCode(final String code) {

    return Arrays.stream(PriceError.values())
            .filter(value -> value.getCode().equals(code))
            .findAny()
            .orElse(null);
  }
}