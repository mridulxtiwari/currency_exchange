package com.microservices.currency_exchange.currency_exchange_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrencyExchange {
    @Id
    private Long id;
    private String fromCountry;
    private String toCountry;
    private BigDecimal exchangeRate;
    private String environment;

    public CurrencyExchange(Long id, String fromCountry, String toCountry, BigDecimal exchangeRate) {
        this.toCountry = toCountry;
        this.id = id;
        this.fromCountry = fromCountry;
        this.exchangeRate = exchangeRate;
    }
}
