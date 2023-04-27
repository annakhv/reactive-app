package com.reactiveproject.sport.parser;

import com.reactiveproject.sport.model.Sport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
@AllArgsConstructor
@Component
public class JsonParser {

    private final DataManager dataManager;

        public Flux<Sport> parseJson(String url){

            return null;
        }
}
