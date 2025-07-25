package com.lbd.testliquibase.service;


import com.lbd.testliquibase.configuration.PropertiesData;
import com.lbd.testliquibase.domain.ExchangeRateDTO;
import com.lbd.testliquibase.domain.FixerDTO;
import com.lbd.testliquibase.domain.ProjectionDTO;
import com.lbd.testliquibase.entity.ExchangeRateEntity;
import com.lbd.testliquibase.exceptions.MyException;
import com.lbd.testliquibase.exceptions.MyException2;
import com.lbd.testliquibase.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeRateService {


    private final BankRepository repository;
    private final CacheService cacheService;

    @Value("${services.fixer.name}") // only for test
    private String valueDa;

    public FixerDTO getData(String origin, String target) {
        return cacheService.getData(origin, target);
    }


    public ExchangeRateDTO getResponse(String origin, String target) {
        FixerDTO fixerDTO = cacheService.getData(origin, target);
        try {
            ExchangeRateEntity tmp = repository.save(ExchangeRateEntity.builder()
                    .date(fixerDTO.getDate())
                    .originCurrency(origin)
                    .finalCurrency(target)
                    .value(String.valueOf(fixerDTO.getRates().get(target)))
                    .build());
            return ExchangeRateDTO.builder()
                    .id(tmp.getId())
                    .date(tmp.getDate())
                    .originCurrency(tmp.getOriginCurrency())
                    .finalCurrency(tmp.getFinalCurrency())
                    .value(tmp.getValue())
                    .build();
        } catch (Exception ex) {

            throw new MyException2("Error with database insertion", 401);
        }

    }

    public List<ProjectionDTO> getByOriginCurrency(String currency) {
        return repository.findAllByOriginCurrency(currency);

    }



}
