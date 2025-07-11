package com.lbd.testliquibase.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "exchange_rate_data")
public class ExchangeRateEntity {

    @Id
    private Integer id;
    private String originCurrency;
    private String finalCurrency;
    @Column("date1")
    private String date;
    @Column("value1")
    private String value;



}
