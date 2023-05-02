package com.reactiveproject.sport.service;

import com.reactiveproject.sport.model.Sport;
import com.reactiveproject.sport.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class SportService {

    private final SportRepository sportRepository;
    public Mono<Sport> searchByName(String name) {
        return sportRepository.findByNameIgnoreCase(name)
                .log()
                .switchIfEmpty(Mono.just(new Sport(0000l,"dummySport")));
    }

    public Mono<Sport> createByName(String name) {
        return sportRepository.findByNameIgnoreCase(name)
                .switchIfEmpty(sportRepository.save(new Sport(name)));
    }

}
