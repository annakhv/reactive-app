package com.reactiveproject.sport.service;

import com.reactiveproject.sport.model.Sport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class SportService {


    public Mono<Sport> searchByName(String name) {
        return null;
    }

    public Mono<Sport> createByName(String name) {
        return null;
    }

}
