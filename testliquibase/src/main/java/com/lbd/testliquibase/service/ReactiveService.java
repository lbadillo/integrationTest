package com.lbd.testliquibase.service;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
@NoArgsConstructor
public class ReactiveService {

    public Flux<String> getNames() {
        return Flux.just("David", "Alberto", "Francisco", "Esperanza");
    }

    public Flux<String> getLastNames() {
        List<String> lastNamesList = List.of("Jimenez", "Rodriguez", "Badillo", "Perez");
        return Flux.fromIterable(lastNamesList);

    }
    public Flux<Void> getEmpty(){
        return Flux.empty();
    }

    public Flux<String> getCharsNames() {
        return getNames().flatMap(x -> Flux.just(x.split("")));
    }



}
