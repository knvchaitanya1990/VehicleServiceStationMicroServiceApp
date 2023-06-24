package com.example.insuranceVerification.controller;

import com.example.insuranceVerification.model.Insurance;
import com.example.insuranceVerification.service.InsuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/verify-insurance")
public class InsuranceController {


    private final InsuranceService verificationService;

    public InsuranceController(InsuranceService verificationService) {
        this.verificationService = verificationService;
    }

    @GetMapping("/{registrationNumber}")
    public ResponseEntity<String> verifyVehicleInsuranceDetails(@PathVariable("registrationNumber") String registrationNumber) {
        Optional<Insurance> optionalInsurance = verificationService.getInsuranceByRegistrationNumber(registrationNumber);
        if (optionalInsurance.isPresent() && optionalInsurance.get().getPolicyEndDate().isAfter(LocalDate.now()))
            return ResponseEntity.status(HttpStatus.OK).body("Vehicle is insured and insurance is valid");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle is not insured or the insurance has expired");
    }
}

