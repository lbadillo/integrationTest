package com.lbd.testliquibase.repository;


import com.lbd.testliquibase.entity.ExchangeRateEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BankRepository extends R2dbcRepository<ExchangeRateEntity, Integer> {
    Flux<ExchangeRateEntity> findByFinalCurrency(String finalCurrency);
}
