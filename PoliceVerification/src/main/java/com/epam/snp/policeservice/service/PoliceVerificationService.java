package com.epam.snp.policeservice.service;

import com.epam.snp.policeservice.model.PoliceVerification;
import com.epam.snp.policeservice.repository.PoliceVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceVerificationService {

    @Autowired
    private PoliceVerificationRepository verificationRepository;


    /*
    When method returns true if vehicle involved in theft.
    When method returns false ,if vehicle is not involved in theft.
    */
    public boolean vehicleInvolvedInTheft(String registrationNum){
        Optional<PoliceVerification> verification = verificationRepository.findByRegistrationNumber(registrationNum);
        return verification.isPresent();
    }

    public List<PoliceVerification>  findAllVehicles(){
        return verificationRepository.findAll();
    }

}
