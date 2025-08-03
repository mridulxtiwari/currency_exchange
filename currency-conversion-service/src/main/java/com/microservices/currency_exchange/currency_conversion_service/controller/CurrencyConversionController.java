package com.microservices.currency_exchange.currency_conversion_service.controller;

import com.microservices.currency_exchange.currency_conversion_service.entity.CurrencyConversion;
import com.microservices.currency_exchange.currency_conversion_service.entity.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/currency-conversion/from/{fromCountry}/to/{toCountry}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String fromCountry, @PathVariable String toCountry, @PathVariable BigDecimal quantity){
        HashMap<String,String> uriVariables=new HashMap<>();
        uriVariables.put("fromCountry",fromCountry);
        uriVariables.put("toCountry",toCountry);
        ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{fromCountry}/to/{toCountry}", CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion=responseEntity.getBody();
        if(currencyConversion==null){
            throw new RuntimeException("Exchange service failed");
        }
        return new CurrencyConversion(currencyConversion.getId(), fromCountry,toCountry, quantity,currencyConversion.getExchangeRate(),quantity.multiply(currencyConversion.getExchangeRate()), currencyConversion.getEnvironment());
    }

    @GetMapping("/currency-conversion-feign/from/{fromCountry}/to/{toCountry}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String fromCountry, @PathVariable String toCountry, @PathVariable BigDecimal quantity){
        CurrencyConversion currencyConversion=proxy.retrieveExchangeValue(fromCountry, toCountry);
        if(currencyConversion==null){
            throw new RuntimeException("Exchange service failed");
        }
        return new CurrencyConversion(currencyConversion.getId(), fromCountry,toCountry, quantity,currencyConversion.getExchangeRate(),quantity.multiply(currencyConversion.getExchangeRate()), currencyConversion.getEnvironment()+"-feign");
    }
}
