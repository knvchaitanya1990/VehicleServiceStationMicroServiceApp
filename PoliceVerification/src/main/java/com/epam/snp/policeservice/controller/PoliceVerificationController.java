package com.epam.snp.policeservice.controller;

import com.epam.snp.policeservice.response.VehicleTheftVerificationResponse;
import com.epam.snp.policeservice.model.PoliceVerification;
import com.epam.snp.policeservice.service.PoliceVerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/verify-theft")
@Tag(description = "Validates the Vehicle against the Theft Database",
        name = "Vehicle Theft Resource")
public class PoliceVerificationController {


    private final PoliceVerificationService verificationService;

    public PoliceVerificationController(PoliceVerificationService verificationService) {
        this.verificationService =verificationService;
    }

    @Operation(summary = "Get Status of Vehicle involved in theft or not",
            description = "Provides true if vehicle not involved in Theft and false if it involves in Theft.")
    @GetMapping("/vehicleDB/{registrationNumber}")
    public ResponseEntity<VehicleTheftVerificationResponse> verifyVehicleTheft(@PathVariable("registrationNumber") String registrationNumber) {
        if (verificationService.vehicleInvolvedInTheft(registrationNumber)) {
                return ResponseEntity.ok(new VehicleTheftVerificationResponse(false, "Vehicle is involved in theft"));
            } else {
                return ResponseEntity.ok(new VehicleTheftVerificationResponse(true, "Vehicle is not involved in theft"));
            }
    }

    @Operation(summary = "List of all Vehicles involved in theft",
            description = "Provides the List of all Vehicles involved in theft")
    @GetMapping("/allVehicles")
    public List<PoliceVerification> getAllVehicles(){
        return  verificationService.findAllVehicles();
    }


    @Operation(summary = "Get Status of Vehicle involved in theft or not",
            description = "Provides true if vehicle not involved in Theft and false if it involves in Theft.")
    @GetMapping("/vehicle/{registrationNumber}")
    public boolean verifyVehicleInvolvedInTheft(@PathVariable("registrationNumber") String registrationNumber) {
        return verificationService.vehicleInvolvedInTheft(registrationNumber);
    }



}