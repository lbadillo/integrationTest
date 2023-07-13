package com.lbd.testliquibase.api;


import com.lbd.testliquibase.service.ExchangeRateService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class IntegrationIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExchangeRateService service;

    @Test
    void should_create_new_rate() throws Exception {
        ReflectionTestUtils.setField(service,"valueDa","testIntegration");
        this.mockMvc.perform(get("/api/v1/exchange-rate/register/{origin}/{target}", "USD", "PEN"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.originCurrency").value("USD"))
                .andExpect(jsonPath("$.id").value(1));

    }
}
