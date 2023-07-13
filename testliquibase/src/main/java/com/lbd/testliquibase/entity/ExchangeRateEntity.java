package com.lbd.testliquibase.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "exchange_rate_data")
public class ExchangeRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String originCurrency;
    private String finalCurrency;
    @Column(name = "date1")
    private String date;
    @Column(name = "value1")
    private String value;



}
