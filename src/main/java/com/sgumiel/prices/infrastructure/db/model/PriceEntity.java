package com.sgumiel.prices.infrastructure.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@Entity
@Table(name = "prices")
public class PriceEntity implements Serializable {

  @Id
  private Long id;

  @Column(name = "BRAND_ID")
  private Integer brandId;

  @Column(name = "START_DATE")
  private LocalDateTime startDate;

  @Column(name = "END_DATE")
  private LocalDateTime endDate;

  @Column(name = "PRICE_LIST")
  private Integer priceList;

  @Column(name = "PRODUCT_ID")
  private Integer productId;

  @Column(name = "PRIORITY")
  private Integer priority;

  @Column(name = "PRICE")
  private Double price;

  @Column(name = "CURR")
  private String currency;

}
