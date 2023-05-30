package com.example.licenseservice.configuration;

import com.example.licenseservice.model.event.OrganizationChangeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class MessageHandler {

    @Bean
    public Consumer<Message<OrganizationChangeModel>> input() {
        return message -> {
            OrganizationChangeModel model = message.getPayload();
            log.info("Received an event for organization id {}", model.getOrganizationId());
        };
    }
}
