package com.lbd.testliquibase.service;

import com.lbd.testliquibase.domain.ExchangeRateDTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Data
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<Integer, ExchangeRateDTO> redisTemplate;

    public ExchangeRateDTO saveRate(String origin, String target) {
        Random random = new Random();
        ExchangeRateDTO rate = ExchangeRateDTO.builder()
                .originCurrency(origin)
                .finalCurrency(target)
                .value("val")
                .id(random.nextInt(100))
                .build();
        redisTemplate.opsForValue().set(rate.getId(), rate);
        return rate;
    }

    public ExchangeRateDTO getRate(Integer id) {
        return redisTemplate.opsForValue().get(id);
    }

}
