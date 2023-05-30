package com.example.organizationservice.model.event;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationChangeModel {

    private String type;
    private String action;
    private String organizationId;
    private String correlationId;

}
