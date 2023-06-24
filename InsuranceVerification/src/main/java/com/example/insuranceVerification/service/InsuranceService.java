package com.example.insuranceVerification.service;

import com.example.insuranceVerification.model.Insurance;
import com.example.insuranceVerification.repository.InsuranceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InsuranceService {


    private final InsuranceRepository insuranceRepository;

    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

   /* public boolean findVehicleInInsuranceDatabase(String registrationNum) {

        Optional<Insurance> optionalInsurance = insuranceVerificationRepository.findVehicleInsuranceByRegistrationNumber(registrationNum);

        if (optionalInsurance.isPresent() && optionalInsurance.get().getPolicyEndDate().isAfter(LocalDate.now()))
            return true;
        return false;
    }*/

    public Optional<Insurance> getInsuranceByRegistrationNumber(String registrationNum) {
        Optional<Insurance> optionalInsurance =
                insuranceRepository.findVehicleInsuranceByRegistrationNumber(registrationNum);
        return optionalInsurance;
    }

}

