package com.reactiveproject.sport.parser;

import com.reactiveproject.sport.model.Sport;
import com.reactiveproject.sport.repository.SportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class DataManager {

    private final SportRepository repository;

    public Mono<Sport> saveData(Sport sport){
       return repository.save(sport);
    }


}
