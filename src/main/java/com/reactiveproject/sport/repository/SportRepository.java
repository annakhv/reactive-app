package com.reactiveproject.sport.repository;

import com.reactiveproject.sport.model.Sport;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SportRepository extends ReactiveCrudRepository<Sport,Long> {

    Mono<Sport> findByNameIgnoreCase(String name);

}
