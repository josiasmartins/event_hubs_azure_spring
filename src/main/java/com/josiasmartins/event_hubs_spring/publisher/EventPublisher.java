package com.josiasmartins.event_hubs_spring.publisher;

import com.josiasmartins.event_hubs_spring.consumer.EventConsumer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

//import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class EventPublisher {

    private static final Logger log = LoggerFactory.getLogger(EventPublisher.class);

    private final StreamBridge streamBridge;

//    public EventPublisher(StreamBridge streamBridge) {
//        this.streamBridge = streamBridge;
//    }

    public void publishEvent(String message) {
        log.info("Sending message: {}", message);
        streamBridge.send("supply-out-0", message);
        log.info("Send {}.", message);
    }

    @ServiceActivator(inputChannel = "eventhubteste.errors")
    public void produceError(Message<?> message) {
        log.error("Handling Producer ERROR: " + message);
    }

}
