package com.lbd.testliquibase.api;


import com.lbd.testliquibase.domain.ExchangeRateDTO;
import com.lbd.testliquibase.domain.FixerDTO;
import com.lbd.testliquibase.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/exchange-rate")
@Slf4j
public class ExchangeRateController {


    private ExchangeRateService service;

    @GetMapping(value = "/{origin}/{target}")
    public Mono<FixerDTO> getExchangeRate2(@PathVariable("origin") String origin,
                                           @PathVariable("target") String target,
                                           @RequestHeader(required = false) String header1,
                                           @RequestParam(required = false) String source) {

        log.info("header1: " + header1);
        log.info("Parameter: " + source);
        return service.getFixerData(origin, target);

    }

    @GetMapping(value = "/{target}")
    public Flux<ExchangeRateDTO> getRate(@PathVariable("target") String target) {
        return service.getRates(target);
    }


    @PostMapping
    public Mono<ExchangeRateDTO> getExchangeRate(@RequestBody ExchangeRateDTO exchangeRateDTO) {
        return service.registerFixerData(exchangeRateDTO.getOriginCurrency(),
                exchangeRateDTO.getFinalCurrency());
    }


}
