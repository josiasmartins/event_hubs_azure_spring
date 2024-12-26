package com.josiasmartins.event_hubs_spring.controller;

import com.josiasmartins.event_hubs_spring.publisher.EventPublisher;
import com.josiasmartins.event_hubs_spring.service.EventhubsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequiredArgsConstructor
public class EventHubsController {

    private static final Logger log = LoggerFactory.getLogger(EventHubsController.class);

    private final EventhubsService eventhubsService;

    private final EventPublisher eventPublisher;

//    public EventHubsController(EventhubsService eventhubsService, EventPublisher eventPublisher) {
//        this.eventhubsService = eventhubsService;
//        this.eventPublisher = eventPublisher;
//    }

    @PostMapping("/publishMessage")
    public Mono<ResponseEntity<String>> publishMessage() {
        return eventhubsService.someMethod()
                .publishOn(Schedulers.boundedElastic())
                .doOnSuccess(eventPublisher::publishEvent)
                .map(ResponseEntity::ok);
    }

}
