package com.lbd.testliquibase.service;


import com.lbd.testliquibase.configuration.PropertiesData;
import com.lbd.testliquibase.domain.FixerDTO;
import com.lbd.testliquibase.exceptions.MyException;
import com.lbd.testliquibase.repository.BankRepository;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class ExchangeRateServiceRateTest {



    private MockWebServer mockWebServer;
    @InjectMocks
    private static ExchangeRateService service;
    @Mock
    private BankRepository rep;
    @Mock
    private PropertiesData prop;
    @BeforeEach
    void setUp() {
        mockWebServer = new MockWebServer();
        WebClient mockedWebClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();
        service = new ExchangeRateService(rep, prop, mockedWebClient);

    }
    @AfterEach
    void finalizeAll() throws IOException {
        mockWebServer.close();
    }

    @Test
    void getFixerDataOKTest() {
        when(prop.getVersion()).thenReturn("/latest");
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(HttpStatus.OK.value())
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody("{ \"base\" : \"TEST\", \"rates\": {\"test\":1.0}}")
        );


        Mono<FixerDTO> fixerMono = service.getFixerData("test", "test");

        StepVerifier.create(fixerMono)
                .expectNextMatches(fixer -> fixer.getBase()
                        .equals("TEST"))
                .verifyComplete();

    }

    @Test
    void getFixerDataNoOK1Test() {
        when(prop.getVersion()).thenReturn("/latest");
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(HttpStatus.BAD_REQUEST.value())
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody("{ \"base\" : \"TEST\", \"rates\": {\"test\":1.0}}")
        );


        Mono<FixerDTO> fixerMono = service.getFixerData("test", "test");

        StepVerifier.create(fixerMono)
                .expectErrorMessage("Web Error")
                .verify();
    }

    @Test
    void getFixerDataNoOK2Test() {
        when(prop.getVersion()).thenReturn("/latest");
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(HttpStatus.OK.value())
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody("{ \"base\" : \"TEST\", \"rates\": {}}")
        );


        Mono<FixerDTO> fixerMono = service.getFixerData("test", "test");

        StepVerifier.create(fixerMono)
                .expectErrorMatches(dta -> dta instanceof MyException &&
                        ((MyException) dta).getStatusCode() == 4)

                .verify();
    }


}
