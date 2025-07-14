package com.lbd.testliquibase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class TestLiquibaseReactiveApplication {


	public static void main(String[] args) {
		SpringApplication.run(TestLiquibaseReactiveApplication.class, args);
	}



}
