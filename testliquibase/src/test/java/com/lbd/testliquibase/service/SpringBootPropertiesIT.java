package com.lbd.testliquibase.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class SpringBootPropertiesIT {

    @Value("${services.fixer.url}")
    String foo;

    @Test
    void test(){
        assertThat(foo).isEqualTo("http://localhost:3000/fixer");
    }
}
