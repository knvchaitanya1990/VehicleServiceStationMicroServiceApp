package com.example.vehicleServiceStation.client;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface PoliceVerificationClient {

    @GetMapping("/api/v1/verify-theft/vehicle/{registrationNumber}")
    public boolean verifyVehicleInvolvedInTheft(@PathVariable("registrationNumber") String registrationNumber);

}
