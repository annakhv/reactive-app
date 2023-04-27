package com.reactiveproject.sport.controller;


import com.reactiveproject.sport.model.Sport;
import com.reactiveproject.sport.repository.SportRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/sport")
@RestController
public class sport {

    private final SportRepository repository;

    public sport(SportRepository repository) {
        this.repository = repository;
    }

    //test api
    @GetMapping("/{id}")
    public Mono<Sport> getName(@PathVariable long id){
        return repository.findById(id).defaultIfEmpty(new Sport(100,"basketball"));

    }

    public Flux<Sport> getDataFromUrl(){
        return null;
    }

}
