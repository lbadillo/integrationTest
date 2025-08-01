package com.lbd.testliquibase.api;


import com.lbd.testliquibase.domain.ExchangeRateDTO;
import com.lbd.testliquibase.domain.FixerDTO;
import com.lbd.testliquibase.domain.ProjectionDTO;
import com.lbd.testliquibase.service.ExchangeRateService;
import com.lbd.testliquibase.service.RedisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/redis")
@Slf4j
public class RedisController {


    private RedisService service;


    @PutMapping(value = "/{origin}/{target}")
    public ExchangeRateDTO createRate(@PathVariable("origin") String origin,
                                      @PathVariable("target") String target) {
        return service.saveRate(origin, target);


    }

    @GetMapping(value = "/{id}")
    public ExchangeRateDTO getData(@PathVariable("id") Integer id) {
        return service.getRate(id);
    }


}
