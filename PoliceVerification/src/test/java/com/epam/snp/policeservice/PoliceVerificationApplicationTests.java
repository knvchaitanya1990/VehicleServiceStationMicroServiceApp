package com.epam.snp.policeservice;

import com.epam.snp.policeservice.model.PoliceVerification;
import com.epam.snp.policeservice.repository.PoliceVerificationRepository;
import com.epam.snp.policeservice.service.PoliceVerificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class PoliceVerificationApplicationTests {

	@Mock
	private PoliceVerificationRepository policeVerificationRepository;

	@InjectMocks
	private PoliceVerificationService policeVerificationService;

	@BeforeEach
	public void setup(){
		MockitoAnnotations.openMocks(this);
	}


	@Test
	public void testVerifyTheft_StatusTrue(){

		String registrationNumber = "TS070355";
		when(policeVerificationRepository.findByRegistrationNumber(registrationNumber))
				.thenReturn(Optional.of(new PoliceVerification(registrationNumber,false)));

		boolean result =policeVerificationService.vehicleInvolvedInTheft(registrationNumber);
		assertTrue(result);
	}

	@Test
	public void testVerifyTheft_StatusFalse(){


		String registrationNumber = "AP07035565";
		when(policeVerificationRepository.findByRegistrationNumber(registrationNumber))
				.thenReturn(Optional.empty());

		boolean result =policeVerificationService.vehicleInvolvedInTheft(registrationNumber);
		assertFalse(result);
	}



}
