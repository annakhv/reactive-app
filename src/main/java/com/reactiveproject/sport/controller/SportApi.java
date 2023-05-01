package com.reactiveproject.sport.controller;


import com.reactiveproject.sport.parser.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RequestMapping("/sport")
@RestController
@AllArgsConstructor
public class SportApi {


    private final JsonParser jsonParser;


    @GetMapping("/data")
    public Flux<String> getData() {
        return jsonParser.parseJsonToObj()
                .map(elem -> " [ element " + elem + " is successfully saved in db ]");
    }

}
