package com.example.organizationservice.configuration;


import com.example.organizationservice.model.event.OrganizationChangeModel;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class EventSender {

    private  StreamBridge streamBridge;

    public void sendEvent(String type, String action, String orgId, String correlationId){
        OrganizationChangeModel change =  OrganizationChangeModel.builder()
                .type(type)
                .action(action)
                .organizationId(orgId)
                .correlationId(correlationId)
                .build();
        streamBridge.send("output-out-0", change);
    }
}
