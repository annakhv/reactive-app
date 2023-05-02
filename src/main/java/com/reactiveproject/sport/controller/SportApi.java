package com.reactiveproject.sport.controller;


import com.reactiveproject.sport.parser.JsonParser;
import lombok.AllArgsConstructor;
import org.reactivestreams.Subscription;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.BaseSubscriber;


@RequestMapping("/sport")
@RestController
@AllArgsConstructor
public class SportApi {


    private final JsonParser jsonParser;


    @GetMapping("/data")
    public void getData() {
        jsonParser.parseJsonToObj()
                .map(elem -> " [ element " + elem + " is successfully saved in db ]")
                .log()
                .subscribe(new BaseSubscriber<String>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        System.out.println("subscribed 20 items");
                        subscription.request(20);
                    }

                    @Override
                    protected void hookOnNext(String value) {
                        System.out.println("received 20 items");
                        request(20);
                    }

                });


    }


}
