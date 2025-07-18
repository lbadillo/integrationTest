package com.lbd.testliquibase.repository;


import com.lbd.testliquibase.domain.ProjectionDTO;
import com.lbd.testliquibase.entity.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BankRepository extends JpaRepository<ExchangeRateEntity, Integer> {

    List<ProjectionDTO> findAllByOriginCurrency(String currency);
}
