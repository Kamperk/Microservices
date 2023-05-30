package com.example.licenseservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="licenses")
public class License extends RepresentationModel<License> {

    @Id
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
    private String comment;

    public License withComment(String comment){
        this.setComment(comment);
        return this;
    }
}
