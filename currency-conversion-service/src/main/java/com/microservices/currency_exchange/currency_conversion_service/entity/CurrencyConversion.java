package com.microservices.currency_exchange.currency_conversion_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversion {
    private Long id;
    private String fromCountry;
    private String toCountry;
    private BigDecimal quantity;
    private BigDecimal exchangeRate;
    private BigDecimal convertedAmount;
    private String environment;
}
