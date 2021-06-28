package com.sgumiel.prices.infrastructure.rest.factory;

import com.sgumiel.prices.domain.exception.PriceException;
import com.sgumiel.prices.infrastructure.rest.model.ApiErrorResponse;

public interface ApiErrorResponseFactory {

  ApiErrorResponse createApiErrorResponse(PriceException priceException);
}
