package com.reactiveproject.sport.config;

import com.reactiveproject.sport.handler.SportHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> router(SportHandler sportHandler) {
        return route()
                .GET("/api/v1/sport", RequestPredicates.queryParam("name", n -> true), sportHandler::findSport)
                .POST("/api/v1/sport/{sportname}",sportHandler::createSport )
                .build();
    }


}
