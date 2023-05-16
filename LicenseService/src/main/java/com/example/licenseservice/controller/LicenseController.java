package com.example.licenseservice.controller;


import com.example.licenseservice.model.License;
import com.example.licenseservice.service.LicenseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value ="v1/license")
@AllArgsConstructor
@Slf4j
public class LicenseController {

    private LicenseService service;

@GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense( @PathVariable("licenseId") String licenseId){
    log.info("Request with license id {} was accepted", licenseId);
    License license = service.getLicense(licenseId);
    license.add(linkTo(methodOn(LicenseController.class)
                    .getLicense(license.getLicenseId()))
                    .withSelfRel(),
            linkTo(methodOn(LicenseController.class)
                    .createLicense(license, null))
                    .withRel("createLicense"),
            linkTo(methodOn(LicenseController.class)
                    .updateLicense(license))
                    .withRel("updateLicense"),
            linkTo(methodOn(LicenseController.class)
                    .deleteLicense(license.getLicenseId()))
                    .withRel("deleteLicense"));
    return ResponseEntity.ok(license);
}



@PutMapping()
    public ResponseEntity<License> updateLicense(@RequestBody License license){
    return ResponseEntity.ok(service.updateLicense(license));
}

@PostMapping()
    public ResponseEntity<License> createLicense(@RequestBody License license,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale){
    return ResponseEntity.ok(service.createLicense(license));
}
@DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") String licenseId){
    return ResponseEntity.ok(service.deleteLicense(licenseId));
}


}
