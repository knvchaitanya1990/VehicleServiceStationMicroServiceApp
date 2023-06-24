package com.epam.snp.policeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="VEHICLE_THEFT_DB")
public class PoliceVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;

    @Column(name ="CUSTOMER_NAME")
    private String customerName;
    @Column(name ="EMAIL")
    private String email;

    public PoliceVerification(String registrationNumber, boolean b) {
        super();
    }


}

