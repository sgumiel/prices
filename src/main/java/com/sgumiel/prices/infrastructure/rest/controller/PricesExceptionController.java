package com.sgumiel.prices.infrastructure.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.sgumiel.prices.domain.exception.PriceException;
import com.sgumiel.prices.infrastructure.rest.factory.ApiErrorResponseFactory;
import com.sgumiel.prices.infrastructure.rest.function.FieldErrorToApiErrorResponseMapper;
import com.sgumiel.prices.infrastructure.rest.model.ApiErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class PricesExceptionController {

  private final ApiErrorResponseFactory apiErrorResponseFactory;
  private final FieldErrorToApiErrorResponseMapper fieldErrorToApiErrorResponseMapper;

  @ExceptionHandler({PriceException.class})
  public ResponseEntity<ApiErrorResponse> handlePriceException(PriceException priceException) {
    log.debug("Handle for price exception: {}", priceException);

    final var apiErrorResponse = this.apiErrorResponseFactory.createApiErrorResponse(priceException);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);

  }

  @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
  public ResponseEntity<List<ApiErrorResponse>> handlePriceException(BindException exception) {
    log.debug("Handle for exception: {}", exception);

    final var errorList =  exception.getBindingResult().getAllErrors();

    final var apiErrorList = errorList.stream()
            .map( objectError -> (FieldError)objectError)
            .map( fieldError -> this.fieldErrorToApiErrorResponseMapper.apply(fieldError))
            .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorList);

  }

  @ExceptionHandler
  public ResponseEntity<List<ApiErrorResponse>> handlePriceException(MissingServletRequestParameterException exception) {
    log.debug("Handle for exception: {}", exception);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

  }
}
