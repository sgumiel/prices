package com.sgumiel.prices.infrastructure.rest.controller;

import javax.validation.Valid;

import com.sgumiel.prices.application.PriceService;
import com.sgumiel.prices.infrastructure.rest.map.PriceRestMapper;
import com.sgumiel.prices.infrastructure.rest.model.PriceResponse;
import com.sgumiel.prices.infrastructure.rest.model.PricesRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("price")
public class PriceController {

  private final PriceService priceService;
  private final PriceRestMapper priceRestMapper;

  @Operation(summary = "Get prices")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Get prices",
                  content = {@Content(mediaType = "application/json",
                          schema = @Schema(implementation = PriceResponse.class))})})
  @GetMapping
  public ResponseEntity<PriceResponse> findPrices(@Valid PricesRequest pricesRequest) {

    log.debug("Find price: {}", pricesRequest);

    final var filter = this.priceRestMapper.toDomain(pricesRequest);

    final var priceOp = this.priceService.findByFilter(filter);
    log.debug("Price found: {}", priceOp.isPresent());

    if (priceOp.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    final var priceResponse = this.priceRestMapper.toResponse(priceOp.get());
    log.debug("Price mapped to response");

    return ResponseEntity.ok(priceResponse);

  }
}
