package com.example.vehicleServiceStation.client;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class VehicleTheftServiceClient {
    private final DiscoveryClient discoveryClient;
    private final WebClient webClient;

    public VehicleTheftServiceClient(DiscoveryClient discoveryClient, WebClient webClient) {
        this.discoveryClient = discoveryClient;
        this.webClient = webClient;
    }

    public Boolean verifyVehicleInvolvedInTheft(String registrationNumber) {
        List<ServiceInstance> instances = discoveryClient.getInstances("PoliceVerification-Service");
        if (instances.isEmpty()) {
            throw new IllegalStateException("Police Verification service not found in service registry.");
        }

        // Fetch the host details of the Police Verification service from Eureka
        ServiceInstance serviceInstance = instances.get(0);
        String policeVerificationServiceUrl = serviceInstance.getUri().toString();

        // Make the API call to the Police Verification service
        String verifyVehicleUrl = policeVerificationServiceUrl+"/api/v1/verify-theft/vehicle/";

        // Build the request URL with the registrationNumber as a query parameter
        String urlWithParams = String.format("%s?registrationNumber=%s", policeVerificationServiceUrl, registrationNumber);


        return webClient
                .method(HttpMethod.GET)
                .uri(urlWithParams)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

    }
}
