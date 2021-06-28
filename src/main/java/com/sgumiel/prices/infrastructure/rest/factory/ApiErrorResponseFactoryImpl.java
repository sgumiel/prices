package com.sgumiel.prices.infrastructure.rest.factory;

import com.sgumiel.prices.domain.exception.PriceException;
import com.sgumiel.prices.infrastructure.rest.model.ApiErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiErrorResponseFactoryImpl implements ApiErrorResponseFactory {

  private final MessageSource messageSource;

  @Override
  public ApiErrorResponse createApiErrorResponse(PriceException priceException) {

    final var priceError = priceException.getError();

    final var messageCode = priceError.getCode() + ".message";
    final var descriptionCode = priceError.getCode() + ".description";
    final var message = this.messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());
    final var description = this.messageSource.getMessage(descriptionCode, null, LocaleContextHolder.getLocale());

    final var apiErrorResponse = ApiErrorResponse.builder()
            .code(priceError.getCode())
            .message(message)
            .description(description)
            .build();

    return apiErrorResponse;
  }
}
