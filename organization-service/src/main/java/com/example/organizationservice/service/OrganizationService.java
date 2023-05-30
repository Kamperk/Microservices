package com.example.organizationservice.service;

import com.example.organizationservice.configuration.EventSender;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.repository.OrganizationRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class OrganizationService {

    private OrganizationRepo repository;
    private EventSender eventSender;

    public Organization findById(String organizationId, String correlationId) {
        Optional<Organization> opt = repository.findById(organizationId);
        eventSender.sendEvent("GET", "", organizationId, UUID.randomUUID().toString());
        return opt.orElse(null);
    }

    public Organization create(Organization organization, String correlationId){
        organization.setId( UUID.randomUUID().toString());
        organization = repository.save(organization);
        eventSender.sendEvent("CREATE", "", organization.getId(), correlationId);
        return organization;
    }

    public void update(Organization organization, String correlationId){
        repository.save(organization);
        eventSender.sendEvent("UPDATE", "", organization.getId(), UUID.randomUUID().toString());
    }

    public void delete(String organizationId, String correlationId){
        repository.deleteById(organizationId);
        eventSender.sendEvent("DELETE", "", organizationId, UUID.randomUUID().toString());
    }
}
