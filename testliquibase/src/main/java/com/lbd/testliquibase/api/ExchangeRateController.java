package com.lbd.testliquibase.api;


import com.lbd.testliquibase.domain.ExchangeRateDTO;
import com.lbd.testliquibase.domain.ProjectionDTO;
import com.lbd.testliquibase.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/exchange-rate")
@Slf4j
public class ExchangeRateController {


    private ExchangeRateService service;


    @GetMapping(value = "/register/{origin}/{target}")
    public ExchangeRateDTO getExchangeRate2(@PathVariable("origin") String origin,
                                            @PathVariable("target") String target,
                                            @RequestHeader(required = false) String header1,
                                            @RequestParam(required = false) String source) {

        log.info("header1: " + header1);
        log.info("Parameter: " + source);

        return service.getResponse(origin, target);


    }

    @GetMapping("/projection/{currency}")
    public List<ProjectionDTO> getCurrencies(
            @PathVariable("currency") String currency) {

        return service.getByOriginCurrency(currency);
    }


}
