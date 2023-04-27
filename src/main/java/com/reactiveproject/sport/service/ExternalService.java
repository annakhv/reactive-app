package com.reactiveproject.sport.service;

import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class ExternalService {

    private final WebClient webClient=WebClient.create("https://sports.api.decathlon.com");

    public Flux<JSONArray> getSportData() {
        return webClient.get().uri("/sports")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(JSONArray.class)
                .limitRate(1)
                .log();




     }
}
