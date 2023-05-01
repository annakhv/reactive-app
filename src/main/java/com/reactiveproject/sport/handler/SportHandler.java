package com.reactiveproject.sport.handler;

import com.reactiveproject.sport.model.Sport;
import com.reactiveproject.sport.service.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SportHandler {

    private final SportService sportService;
    private final String QUERY_PARAM_NAME = "name";
    private final String PATH_VARIABLE_NAME="sportname";

    public Mono<ServerResponse> findSport(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(sportService.searchByName(request.queryParam(QUERY_PARAM_NAME)
                        .orElseThrow(()->new RuntimeException("no name has been given for sport to search"))), Sport.class);
    }

    public Mono<ServerResponse> createSport(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(sportService.createByName(request.pathVariable(PATH_VARIABLE_NAME)), Sport.class);
    }
}
