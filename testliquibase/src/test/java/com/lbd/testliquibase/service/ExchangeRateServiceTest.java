package com.lbd.testliquibase.service;

import com.lbd.testliquibase.configuration.PropertiesData;
import com.lbd.testliquibase.domain.FixerDTO;
import com.lbd.testliquibase.exceptions.MyException;
import com.lbd.testliquibase.exceptions.MyException2;
import com.lbd.testliquibase.repository.BankRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = PropertiesData.class)
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExchangeRateServiceTest {




    @Mock
    private BankRepository repository;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private CacheService cacheService;

    @Autowired
    private PropertiesData propertiesData;

    private FixerDTO tmp;


    private ExchangeRateService service;


    @BeforeAll
    public void initData() {
        HashMap<String, Double> tmp2 = new HashMap<>();
        tmp2.put("Hola", 23.0);
        tmp = FixerDTO.builder().date("das").base("asdf").success(true).rates(tmp2).build();
        service = new ExchangeRateService(repository, cacheService);


    }

    @Test
    public void primerTest() {
       // Mockito.when(propertiesData.getUrl()).thenReturn("hola");
        // ReflectionTestUtils.setField(service,"serviceUrl","test");

        Mockito.when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.ok(tmp));
        service.getData("USD", "PEN");
        Mockito.verify(cacheService, Mockito.times(1)).getData(anyString(), any());

    }

    @Test
    public void segundoTest() {


        Mockito.when(repository.save(any())).thenThrow(InvalidDataAccessResourceUsageException.class);
        Mockito.when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.ok(tmp));

        Exception exception = assertThrows(MyException2.class, () -> {
            service.getResponse("hola", "hola");
        });
        assertEquals("Error with database insertion", exception.getMessage());

    }


}
