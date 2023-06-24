package com.example.vehicleServiceStation.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface InsuranceVerificationClient {

    @GetMapping("/{registrationNumber}")
    public ResponseEntity<String> verifyVehicleInsuranceDetails(@PathVariable("registrationNumber") String registrationNumber);

}
