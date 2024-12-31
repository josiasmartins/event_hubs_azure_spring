package com.josiasmartins.event_hubs_spring.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class EventConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventConsumer.class);

    @Bean
    public Consumer<String> consume() {
        return message -> {
            log.info("Message received : {}", message);
        };
    }

    @ServiceActivator(inputChannel = "eventhubteste.$Default.errors")
    public void consumerError(Message<?> message) {
        log.error("Handling consumer ERROR: " + message);
    }


}
