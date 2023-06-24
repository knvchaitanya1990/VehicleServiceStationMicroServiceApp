package com.example.insuranceVerification.repository;
import com.example.insuranceVerification.model.Insurance;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance,Long> {
       Optional<Insurance> findVehicleInsuranceByRegistrationNumber(String registrationNumber);

}
