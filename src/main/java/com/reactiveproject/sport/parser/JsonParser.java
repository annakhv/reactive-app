package com.reactiveproject.sport.parser;

import com.reactiveproject.sport.model.Sport;
import com.reactiveproject.sport.service.ExternalService;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Component
public class JsonParser {

    private final DataManager dataManager;
    private final ExternalService externalService;

    public Flux<Sport> parseJsonToObj() {
        return externalService.getSportData()
                .flatMap(this::convertBufferToString)
                .collect(Collectors.joining())
                .flatMap(this::convertStringToJasonObjectList)
                .flatMapMany(Flux::fromIterable)
                .flatMap(this::parseJsonToSport)
                .flatMap(dataManager::persistToDB);

    }

    private Mono<Sport> parseJsonToSport(JSONObject data) {
        long id = (long) data.get("id");
        JSONObject attributes = (JSONObject) data.get("attributes");
        String name = (String) attributes.get("name");
        Sport sport = new Sport(id, name).setAsNew();
        return Mono.just(sport);
    }

    private Mono<String> convertBufferToString(DataBuffer dataBuffer) {
        byte[] bytes = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(bytes);
        DataBufferUtils.release(dataBuffer);
        return Mono.just(new String(bytes, StandardCharsets.UTF_8));
    }

    private Mono<List<JSONObject>> convertStringToJasonObjectList(String data) {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(data);
            JSONArray array = (JSONArray) jsonObject.get("data");
            return Mono.just(IntStream.range(0, array.size()).parallel().boxed().map(i -> (JSONObject) array.get(i)).toList());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
