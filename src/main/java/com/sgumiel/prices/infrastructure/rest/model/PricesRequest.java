package com.sgumiel.prices.infrastructure.rest.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PricesRequest implements Serializable {

  private static final long serialVersionUID = -4257068418063005251L;

  @NotNull(message = "prices.001")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;

  @NotNull(message = "prices.002")
  private Integer brandId;

  @NotNull(message = "prices.003")
  private Integer productId;

}
