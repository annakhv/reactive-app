package com.reactiveproject.sport.parser;

import com.reactiveproject.sport.model.Sport;
import com.reactiveproject.sport.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DataManager {

    private final SportRepository repository;

    public Mono<Sport> persistToDB(Sport sport) {;
       return repository.findById(sport.getId())
               .switchIfEmpty(repository.save(sport));
    }


}
