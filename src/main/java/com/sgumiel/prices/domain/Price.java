package com.sgumiel.prices.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Price {

  private Long id;
  private Integer brandId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Integer priceList;
  private Integer productId;
  private Integer priority;
  private Double price;
  private String currency;
}
