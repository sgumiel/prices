package com.sgumiel.prices.infrastructure.rest.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse implements Serializable {

  private static final long serialVersionUID = -7179127225510232928L;

  private String code;
  private String message;
  private String description;

}
