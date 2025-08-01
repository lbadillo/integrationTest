package com.lbd.testliquibase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateDTO implements Serializable {

    private Integer id;
    private String originCurrency;
    private String finalCurrency;
    private String date;
    private String value;
}
