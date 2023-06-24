package com.epam.snp.policeservice;

import com.epam.snp.policeservice.controller.PoliceVerificationController;
import com.epam.snp.policeservice.model.PoliceVerification;
import com.epam.snp.policeservice.response.VehicleTheftVerificationResponse;
import com.epam.snp.policeservice.service.PoliceVerificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PoliceVerificationControllerTest {


      /*
    When method returns true if vehicle involved in theft.
    When method returns false ,if vehicle is not involved in theft.
    */

    @Mock
    private PoliceVerificationService verificationService;
    @Test
    public void testVerifyVehicleTheft_WhenVehicleNotInvolvedInTheft_ReturnsFalse() {
        // Arrange
        String registrationNumber = "ABC123";
        when(verificationService.vehicleInvolvedInTheft(registrationNumber)).thenReturn(false);
        PoliceVerificationController controller = new PoliceVerificationController(verificationService);
        // Act
        ResponseEntity<VehicleTheftVerificationResponse> response = controller.verifyVehicleTheft(registrationNumber);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).isVehicleNotInvolved());
        assertEquals("Vehicle is involved in theft", response.getBody().getMessage());
        verify(verificationService, times(1)).vehicleInvolvedInTheft(registrationNumber);
    }

    @Test
    public void testVerifyVehicleTheft_WhenVehicleInvolvedInTheft_ReturnsFalse() {
        // Arrange
        String registrationNumber = "XYZ789";
        when(verificationService.vehicleInvolvedInTheft(registrationNumber)).thenReturn(false);
        PoliceVerificationController controller = new PoliceVerificationController(verificationService);

        // Act
        ResponseEntity<VehicleTheftVerificationResponse> response = controller.verifyVehicleTheft(registrationNumber);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(Objects.requireNonNull(response.getBody()).isVehicleNotInvolved());
        assertEquals("Vehicle is not involved in theft", response.getBody().getMessage());
        verify(verificationService, times(1)).vehicleInvolvedInTheft(registrationNumber);
    }

    @Test
    public void testGetAllVehicles_ReturnsListOfVehicles() {
        // Arrange
        List<PoliceVerification> vehicles = Arrays.asList(
                new PoliceVerification("ABC123", true),
                new PoliceVerification("XYZ789", false)
        );
        when(verificationService.findAllVehicles()).thenReturn(vehicles);
        PoliceVerificationController controller = new PoliceVerificationController(verificationService);

        // Act
        List<PoliceVerification> response = controller.getAllVehicles();

        // Assert
        assertEquals(vehicles.size(), response.size());
        assertEquals(vehicles.get(0).getRegistrationNumber(), response.get(0).getRegistrationNumber());
        assertEquals(vehicles.get(1).getRegistrationNumber(), response.get(1).getRegistrationNumber());
        verify(verificationService, times(1)).findAllVehicles();
    }

    @Test
    public void testVerifyVehicleInvolvedInTheft_WhenVehicleNotInvolvedInTheft_ReturnsTrue() {
        // Arrange
        String registrationNumber = "ABC123";
        when(verificationService.vehicleInvolvedInTheft(registrationNumber)).thenReturn(false);
        PoliceVerificationController controller = new PoliceVerificationController(verificationService);

        // Act
        boolean response = controller.verifyVehicleInvolvedInTheft(registrationNumber);

        // Assert
        assertEquals(true, response);
        verify(verificationService, times(1)).vehicleInvolvedInTheft(registrationNumber);
    }

    @Test
    public void testVerifyVehicleInvolvedInTheft_WhenVehicleInvolvedInTheft_ReturnsFalse() {
        // Arrange
        String registrationNumber = "XYZ789";
        when(verificationService.vehicleInvolvedInTheft(registrationNumber)).thenReturn(true);
        PoliceVerificationController controller = new PoliceVerificationController(verificationService);

        // Act
        boolean response = controller.verifyVehicleInvolvedInTheft(registrationNumber);

        // Assert
        assertEquals(false, response);
        verify(verificationService, times(1)).vehicleInvolvedInTheft(registrationNumber);
    }
}
