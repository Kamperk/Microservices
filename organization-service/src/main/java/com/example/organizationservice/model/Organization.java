package com.example.organizationservice.model;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="organizations")
public class Organization {
    @Id
    String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;

}
