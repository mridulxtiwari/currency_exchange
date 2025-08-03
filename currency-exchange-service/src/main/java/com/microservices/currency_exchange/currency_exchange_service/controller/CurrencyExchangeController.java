package com.microservices.currency_exchange.currency_exchange_service.controller;

import com.microservices.currency_exchange.currency_exchange_service.entity.CurrencyExchange;
import com.microservices.currency_exchange.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{fromCountry}/to/{toCountry}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String fromCountry, @PathVariable String toCountry){

        logger.info("retrieveExchangeValue service called {} to {}",fromCountry,toCountry);

        //CurrencyExchange currencyExchange = new CurrencyExchange(1000L, fromCountry, toCountry, BigDecimal.valueOf(50));
        CurrencyExchange currencyExchange= currencyExchangeRepository.findByFromCountryAndToCountry(fromCountry,toCountry);
        if(currencyExchange==null){
            throw  new RuntimeException("No exchange rate from country "+fromCountry+" to "+toCountry);
        }
        String port=environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
