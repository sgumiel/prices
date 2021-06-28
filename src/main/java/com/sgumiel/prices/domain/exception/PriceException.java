package com.sgumiel.prices.domain.exception;

import com.sgumiel.prices.domain.enums.PriceError;
import lombok.Getter;

@Getter
public class PriceException extends RuntimeException {

  private PriceError error;

  public PriceException(PriceError error){
    this.error = error;
  }
}