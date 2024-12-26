package com.josiasmartins.event_hubs_spring.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EventhubsService {

    public Mono<String> someMethod() {
        return Mono.just("Some String data");
    }

}
