package com.example.licenseservice.service;


import com.example.licenseservice.configuration.ServiceConfig;
import com.example.licenseservice.model.License;
import com.example.licenseservice.repo.LicenseRepo;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LicenseService {

    private MessageSource messages;
    private LicenseRepo repo;
    private ServiceConfig config;


    public License getLicense(String licenseId){
        License license = repo.findByLicenseId(licenseId);
        if(license == null){
            throw new IllegalArgumentException(String.format(messages.getMessage("license.search.error.message", null, null),
                    licenseId));
        }
        return license.withComment(config.getProperty());
    }

    public License createLicense(License license){
        license.setLicenseId(UUID.randomUUID().toString());
        repo.save(license);
        return license.withComment(config.getProperty());

    }

    public License updateLicense(License license){
        repo.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId){
        String responseMessage = null;
        License license = new License();
        license.setLicenseId(licenseId);
        repo.delete(license);
        responseMessage = String.format(messages.getMessage(
                "license.delete.message", null, null),licenseId);
        return responseMessage;
    }
}
