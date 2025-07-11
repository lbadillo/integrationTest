package com.lbd.testliquibase.service;


import com.lbd.testliquibase.configuration.PropertiesData;
import com.lbd.testliquibase.domain.ExchangeRateDTO;
import com.lbd.testliquibase.domain.FixerDTO;
import com.lbd.testliquibase.entity.ExchangeRateEntity;
import com.lbd.testliquibase.exceptions.MyException;
import com.lbd.testliquibase.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeRateService {


    private final BankRepository repository;
    private final PropertiesData propertiesData;


    public Mono<FixerDTO> getFixerData(String base, String symbol) {

        WebClient webClient = WebClient.create(propertiesData.getUrl());


        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(propertiesData.getVersion())
                        .queryParam("base", base)
                        .queryParam("symbols", symbol)
                        .build())
                .exchangeToMono(response ->
                        response.statusCode().is2xxSuccessful()
                                ? response.bodyToMono(FixerDTO.class)
                                : Mono.error(new MyException("Not Found One", 404))
                ).flatMap(res -> {
                    return res.getRates().isEmpty()
                            ? Mono.error(new MyException("Not Found Second", 404))
                            : Mono.just(res);
                });


    }

    public Mono<ExchangeRateDTO> registerFixerData(String base, String symbol) {

        Mono<FixerDTO> fixer = getFixerData(base, symbol);
        return fixer.map(fixerDTO1 ->
                        ExchangeRateEntity.builder()
                                .date(fixerDTO1.getDate())
                                .originCurrency(base)
                                .finalCurrency(symbol)
                                .value(String.valueOf(fixerDTO1.getRates().get(symbol)))
                                .build()
                ).flatMap(repository::save)
                .flatMap(element -> Mono.just(ExchangeRateDTO.builder()
                        .id(element.getId())
                        .date(element.getDate())
                        .originCurrency(base)
                        .finalCurrency(symbol)
                        .value(String.valueOf(element.getValue()))
                        .build()));


    }

    public Flux<ExchangeRateDTO> getRates(String symbol) {

        Flux<ExchangeRateEntity> rate = repository.findByFinalCurrency(symbol);


        return rate.flatMap(element -> Flux.just(ExchangeRateDTO.builder()
                        .id(element.getId())
                        .date(element.getDate())
                        .originCurrency(element.getOriginCurrency())
                        .finalCurrency(element.getFinalCurrency())
                        .value(String.valueOf(element.getValue()))
                        .build()))
                .switchIfEmpty(Flux.error(new MyException("Not found", 2)));


    }

}
