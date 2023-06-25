package com.example.vehicleServiceStation.client;

import com.example.vehicleServiceStation.response.VehicleTheftVerificationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@Component
public class ServiceStationHelper {

    @Value("${endpoint.serviceUrl.insurance-service}")
    private String insuranceEndPoint;

    //@Value("${INSURANCE_VERIFICATION_SERVICE_URL}")
    @Value("${endpoint.serviceUrl.police-service}")
    String policeVerificationEndPoint;

    RestTemplate restTemplate = new RestTemplate();

    public boolean verifyVehicleInvolvedInTheft(String registrationNumber) {
        try {

            String urlWithParams = String.format("%s?registrationNumber=%s", policeVerificationEndPoint, registrationNumber);
            ResponseEntity<VehicleTheftVerificationResponse> verificationResult =
                    restTemplate.getForEntity(urlWithParams, VehicleTheftVerificationResponse.class);
           return Objects.requireNonNull(verificationResult.getBody()).isVerified();
        } catch (Exception e) {
            // Handle the exception and return a default value (e.g., false)
            System.out.println("Exception Occurred :"+ Arrays.toString(e.getStackTrace()));
            return false;
        }
    }
    public boolean performInsuranceVerification(String registrationNumber) {
        try {
            ResponseEntity<String> verificationResult = restTemplate.getForEntity(
                    insuranceEndPoint + registrationNumber, String.class);
            return !verificationResult.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            // Handle the exception and return a default value (e.g., false)
            System.out.println("Exception Occurred :"+ Arrays.toString(e.getStackTrace()));
            return false;
        }
    }


}
