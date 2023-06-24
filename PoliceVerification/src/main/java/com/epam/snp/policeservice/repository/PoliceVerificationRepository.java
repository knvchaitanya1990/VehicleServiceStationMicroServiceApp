package com.epam.snp.policeservice.repository;
import com.epam.snp.policeservice.model.PoliceVerification;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface PoliceVerificationRepository extends JpaRepository<PoliceVerification,Long> {

    Optional<PoliceVerification> findByRegistrationNumber(String registrationNum);


}
