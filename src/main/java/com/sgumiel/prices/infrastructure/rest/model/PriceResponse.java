package com.sgumiel.prices.infrastructure.rest.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponse implements Serializable {

  private static final long serialVersionUID = 8101213411646372148L;

  private Integer productId;
  private Integer brandId;
  private Integer priceList;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Double price;
}
