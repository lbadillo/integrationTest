package com.lbd.testliquibase.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class ReactiveServiceTest {
    @InjectMocks
    private ReactiveService service;

    @Test
    void getNamesTest() {
        Flux<String> names = service.getNames();
        names.subscribe(System.out::println);

        StepVerifier.create(names)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void getCharsNamesTest() {
        Flux<String> names = service.getCharsNames();
        names.subscribe(System.out::println);
        StepVerifier.create(names)
                .expectNextCount(30)
                .verifyComplete();

    }

    @Test
    void transformNamesTest() {
        Flux<String> names = service.getNames();
        Flux<String> transform = names.transform(x -> x.map(String::toUpperCase));
        transform.subscribe(System.out::println);
        StepVerifier.create(names)
                .expectNextCount(4)
                .verifyComplete();

    }

    @Test
    void filterTest() {
        Flux<String> names = service.getNames();
        Flux<String> filter = names
                .filter(x -> x.length()<=3)
                .defaultIfEmpty("No name");

        filter.subscribe(System.out::println);
        StepVerifier.create(filter)
                .expectNextCount(1)
                .verifyComplete();

    }

    @Test
    void concatTest() {
        Flux<String> names = service.getNames();
        Flux<String> lastNames = service.getLastNames();

        Flux<String> concat = Flux.concat(names,lastNames);
        concat.subscribe(System.out::println);
        StepVerifier.create(concat)
                .expectNextCount(8)
                .verifyComplete();


    }

    @Test
    void mergeTest() {
        Flux<String> names = service.getNames();
        Flux<String> lastNames = service.getLastNames();

        Flux<String> merge = Flux.merge(names,lastNames);
        merge.subscribe(System.out::println);
        StepVerifier.create(merge)
                .expectNextCount(8)
                .verifyComplete();


    }


}
