package com.lbd.testliquibase.service;

import com.lbd.testliquibase.configuration.PropertiesData;
import com.lbd.testliquibase.domain.FixerDTO;
import com.lbd.testliquibase.exceptions.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class CacheService {

    private final static String VERSION = "/latest";
    private final RestTemplate restTemplate;
    private final PropertiesData propertiesData;

    @Cacheable(value = "testData")
    public FixerDTO getData(String origin, String target) {

        String resourceUrl = propertiesData.getUrl() + VERSION;

        try {
            ResponseEntity<FixerDTO> response
                    = restTemplate.getForEntity(resourceUrl + "?base=" + origin + "&symbols=" + target, FixerDTO.class);
            return response.getBody();
        } catch (Exception ex) {
            throw new MyException("Base not found in external Rate service", 400);
        }

    }
}
