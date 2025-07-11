package com.lbd.testliquibase.service;


import com.lbd.testliquibase.configuration.PropertiesData;
import com.lbd.testliquibase.domain.ExchangeRateDTO;
import com.lbd.testliquibase.domain.FixerDTO;
import com.lbd.testliquibase.entity.ExchangeRateEntity;
import com.lbd.testliquibase.exceptions.MyException;
import com.lbd.testliquibase.exceptions.MyException2;
import com.lbd.testliquibase.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeRateService {

    private static String VERSION = "/latest";


    private final BankRepository repository;
    private final RestTemplate restTemplate;
    private final PropertiesData propertiesData;

    @Value("${services.fixer.name}")
    private String valueDa;

    public FixerDTO getData(String origin, String target) throws MyException {

        String fooResourceUrl = propertiesData.getUrl() + VERSION;
        log.info("este es el valor que buscabamos {}", valueDa);
        try {
            ResponseEntity<FixerDTO> response
                    = restTemplate.getForEntity(fooResourceUrl + "?base=" + origin + "&symbols=" + target, FixerDTO.class);
            return response.getBody();
        } catch (Exception ex) {
            throw new MyException("Base not found in external Rate service", 400);
        }

    }

    public ExchangeRateDTO getResponse(String origin, String target) {
        FixerDTO fixerDTO = getData(origin, target);
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
            ex.printStackTrace();
            throw new MyException2("Error with database insertion", 401);
        }

    }



}
