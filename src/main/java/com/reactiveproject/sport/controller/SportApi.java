package com.reactiveproject.sport.controller;


import com.reactiveproject.sport.model.Sport;
import com.reactiveproject.sport.parser.JsonParser;
import com.reactiveproject.sport.repository.SportRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RequestMapping("/sport")
@RestController
@AllArgsConstructor
public class SportApi {

    private final SportRepository repository;
    private final JsonParser jsonParser;


    //test api
    @GetMapping("/{id}/{sport}")
    public Mono<Sport> getName(@PathVariable long id,@PathVariable String sport) {
       return repository.save(new Sport(id,sport).setAsNew()).switchIfEmpty(Mono.just(new Sport(100l, "basketball")));
    }

    @GetMapping("/data")
    public Flux<String> getData() {
        return jsonParser.parseJsonToObj()
                .map(elem-> " [ element "+elem+ " is successfully saved in db ]");
    }

}
