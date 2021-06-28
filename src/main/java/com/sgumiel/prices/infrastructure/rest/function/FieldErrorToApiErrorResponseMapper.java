package com.sgumiel.prices.infrastructure.rest.function;

import java.util.function.Function;

import com.sgumiel.prices.domain.enums.PriceError;
import com.sgumiel.prices.domain.exception.PriceException;
import com.sgumiel.prices.infrastructure.rest.factory.ApiErrorResponseFactory;
import com.sgumiel.prices.infrastructure.rest.model.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

@Slf4j
@Service
public class FieldErrorToApiErrorResponseMapper implements Function<FieldError, ApiErrorResponse> {

  @Autowired
  private ApiErrorResponseFactory apiErrorResponseFactory;

  @Override
  public ApiErrorResponse apply(FieldError fieldError) {

    final var code = fieldError.getDefaultMessage();
    final var priceError = PriceError.valueOfCode(code);
    final var bankException = new PriceException(priceError);

    final var apiErrorResponse = this.apiErrorResponseFactory.createApiErrorResponse(bankException);

    return apiErrorResponse;
  }
}