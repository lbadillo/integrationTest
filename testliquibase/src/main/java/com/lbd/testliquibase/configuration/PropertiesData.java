package com.lbd.testliquibase.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@ConfigurationProperties(prefix = "services.fixer")
@Configuration
public class PropertiesData {
    private String url;
    private String name;
}
