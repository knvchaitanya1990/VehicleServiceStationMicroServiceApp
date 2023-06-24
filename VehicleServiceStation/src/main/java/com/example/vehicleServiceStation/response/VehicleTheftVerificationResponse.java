package com.example.vehicleServiceStation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTheftVerificationResponse {
    private boolean verified;
    private String message;
}
