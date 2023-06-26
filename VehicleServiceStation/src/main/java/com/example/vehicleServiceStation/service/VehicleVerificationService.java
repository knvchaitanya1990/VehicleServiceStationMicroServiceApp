package com.example.vehicleServiceStation.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

@Service
public class VehicleVerificationService {

    @Autowired
    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;

    @Value("${endpoint.serviceUrl.police-service}")
    String policeVerificationEndPoint;

    @Autowired
    public VehicleVerificationService(RestTemplate restTemplate, CircuitBreaker circuitBreaker) {
        this.restTemplate = restTemplate;
        this.circuitBreaker = circuitBreaker;
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public boolean verifyVehicleInvolvedInTheft(String registrationNumber) {
        try {
            String urlWithParams = String.format("%s?registrationNumber=%s", policeVerificationEndPoint, registrationNumber);

            // Wrap the service call with the circuit breaker
           String responseBody = circuitBreaker.executeSupplier(() ->
                            restTemplate.getForObject(urlWithParams, String.class)
            );
            // Process the response
            return processResponse(responseBody);
        } catch (Exception e) {
            // Handle the exception and return a default value (e.g., false)
            System.out.println("Exception Occurred: " + Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    private boolean processResponse(String responseBody) {
        // Process the response and return the result
        // Example implementation:
        return responseBody != null && responseBody.contains("verified");
    }
}

