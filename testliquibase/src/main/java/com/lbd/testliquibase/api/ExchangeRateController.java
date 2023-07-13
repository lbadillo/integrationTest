package com.lbd.testliquibase.api;


import com.lbd.testliquibase.domain.ExchangeRateDTO;
import com.lbd.testliquibase.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/exchange-rate")
@Slf4j
public class ExchangeRateController {


    private ExchangeRateService service;


/*    @PostMapping("/register")
    public Mono<ExchangeRateDTO> getExchangeRate(@RequestBody ExchangeRateDTO exchangeRateDTO) {
        return service.buildResponse(exchangeRateDTO.getOriginCurrency(), exchangeRateDTO.getFinalCurrency());
    }*/

    @GetMapping(value = "/register/{origin}/{target}")
    public ExchangeRateDTO getExchangeRate2(@PathVariable("origin") String origin,
                                            @PathVariable("target") String target,
                                            @RequestHeader(required = false) String header1,
                                            @RequestParam(required = false) String source) {

        log.info("header1: " + header1);
        log.info("Parameter: " + source);

        return service.getResponse(origin, target);


    }


}
