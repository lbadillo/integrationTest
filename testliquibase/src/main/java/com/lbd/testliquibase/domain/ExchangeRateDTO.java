package com.lbd.testliquibase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class ExchangeRateDTO {

    private int id;
    private String originCurrency;
    private String finalCurrency;
    private String date;
    private String value;
}
