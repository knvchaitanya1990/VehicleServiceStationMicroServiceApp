package com.example.insuranceVerification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="CENTRAL_INSURANCE")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;

    @Column(name = "ENGINE_NUMBER")
    private String engineNumber;

    @Column(name = "POLICY_START_DATE")
    private LocalDate policyStartDate;

    @Column(name = "POLICY_END_DATE")
    private LocalDate policyEndDate;

    @Column(name = "COMPLETE_POLICY_COVERAGE")
    private boolean fullyCovered;

    @Column(name ="CUSTOMER_NAME")
    private String customerName;

    @Column(name ="EMAIL")
    private String email;

    @Column(name ="CONTACT_NUMBER")
    private String contactNumber;

    @Column(name="INSURANCE_PROVIDER")
    private String insuranceProvider;
}




