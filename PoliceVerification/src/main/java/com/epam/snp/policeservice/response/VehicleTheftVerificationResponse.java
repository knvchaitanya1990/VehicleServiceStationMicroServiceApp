package com.epam.snp.policeservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTheftVerificationResponse {
    private boolean isVehicleNotInvolved;
    private String message;
}
