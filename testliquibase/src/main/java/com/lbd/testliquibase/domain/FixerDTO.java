package com.lbd.testliquibase.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FixerDTO {
    private Boolean success;
    private long timestamp;
    private String base;
    private String date;
    private HashMap<String, Double> rates;



}
