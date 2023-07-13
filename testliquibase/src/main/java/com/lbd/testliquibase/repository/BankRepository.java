package com.lbd.testliquibase.repository;


import com.lbd.testliquibase.entity.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BankRepository extends JpaRepository<ExchangeRateEntity, Integer> {
}
