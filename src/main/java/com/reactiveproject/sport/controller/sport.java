package com.reactiveproject.sport.controller;


import com.reactiveproject.sport.model.Sport;
import com.reactiveproject.sport.repository.SportRepository;
import com.reactiveproject.sport.service.ExternalService;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/sport")
@RestController
@AllArgsConstructor
public class sport {

    private final SportRepository repository;
    private final ExternalService externalService;


    //test api
    @GetMapping("/{id}")
    public Mono<Sport> getName(@PathVariable long id) {
        return repository.findById(id).defaultIfEmpty(new Sport(100, "basketball"));

    }
    @GetMapping("/data")
    public Flux<JSONArray> getData() {
        return externalService.getSportData();
    }

}
