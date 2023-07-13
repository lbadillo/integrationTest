package com.lbd.testliquibase.service;

import com.lbd.testliquibase.configuration.PropertiesData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = PropertiesData.class)
@TestPropertySource("classpath:application-test.properties")
public class ConfigurationPropertiesTest {

    @Autowired
    private PropertiesData propertiesData;

    @Test
    void namePropertyTest() {
        assertEquals("test", propertiesData.getName());


    }
}
