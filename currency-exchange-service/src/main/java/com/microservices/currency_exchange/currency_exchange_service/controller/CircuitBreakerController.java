package com.microservices.currency_exchange.currency_exchange_service.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    private Logger logger= LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name="default", fallbackMethod = "hardCodedResponse")
    //@CircuitBreaker(name="default", fallbackMethod = "hardCodedResponse")
    @RateLimiter(name= "default")
    @Bulkhead(name="default")
    public String sampleApi(){
        logger.info("Sample api call received");
//        ResponseEntity<String> responseEntity=new RestTemplate().getForEntity("http:localhost:8080/dummy",String.class);
//        return responseEntity.getBody();
        return "Sample-api";
    }

    public String hardCodedResponse(Exception ex){
        return "hardCodedResponse";
    }
}
