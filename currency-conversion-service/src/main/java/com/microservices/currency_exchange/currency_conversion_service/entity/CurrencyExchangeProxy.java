package com.microservices.currency_exchange.currency_conversion_service.entity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange",url="localhost:8000")
@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{fromCountry}/to/{toCountry}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String fromCountry, @PathVariable String toCountry);
}
