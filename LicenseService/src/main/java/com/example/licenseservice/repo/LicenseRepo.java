package com.example.licenseservice.repo;

import com.example.licenseservice.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepo extends CrudRepository<License, String> {

    List<License> findByOrganizationId(String organizationId);


    License findByLicenseId(String licenseId);

}
