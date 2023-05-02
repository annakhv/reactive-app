package com.reactiveproject.sport.service;

import lombok.AllArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@AllArgsConstructor
public class ExternalService {

    private final WebClient webClient = WebClient.create("https://sports.api.decathlon.com");

    public Flux<DataBuffer> getSportData() {
        return  webClient.get()
                .uri("/sports")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(DataBuffer.class)
                .delayElements(Duration.ofMillis(500));

    }
}
