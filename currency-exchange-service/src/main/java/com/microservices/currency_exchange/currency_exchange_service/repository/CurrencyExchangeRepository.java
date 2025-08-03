package com.microservices.currency_exchange.currency_exchange_service.repository;

import com.microservices.currency_exchange.currency_exchange_service.entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    public CurrencyExchange findByFromCountryAndToCountry(String fromCountry, String toCountry);
}
