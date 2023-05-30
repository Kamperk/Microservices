package com.example.organizationservice.controller;


import com.example.organizationservice.model.Organization;
import com.example.organizationservice.service.OrganizationService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/organization")
@AllArgsConstructor
public class OrganizationController {

    private OrganizationService service;

    //    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping(value = "/{organizationId}")
    public ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") String organizationId,
                                                        @RequestHeader(value = "tmx-correlation-id") String correlationId) {
        return ResponseEntity.ok(service.findById(organizationId, correlationId));
    }

    //    @RolesAllowed({"ADMIN", "USER"})
    @PutMapping
    public void updateOrganization(@RequestBody Organization organization,
                                   @RequestHeader(value = "tmx-correlation-id") String correlationId) {
        service.update(organization, correlationId);
    }

    //    @RolesAllowed({"ADMIN", "USER"})
    @PostMapping
    public ResponseEntity<Organization> saveOrganization(@RequestBody Organization organization,
                                                         @RequestHeader(value = "tmx-correlation-id") String correlationId) {
        return ResponseEntity.ok(service.create(organization, correlationId));
    }

    //    @RolesAllowed({"ADMIN"})
    @RequestMapping(value = "/{organizationId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("organizationId") String id,
                                   @RequestHeader(value = "tmx-correlation-id") String correlationId) {
        service.delete(id, correlationId);
    }
}
