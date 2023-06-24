package com.example.vehicleServiceStation.controller;

import com.example.vehicleServiceStation.model.ServiceRecord;
import com.example.vehicleServiceStation.model.Vehicle;
import com.example.vehicleServiceStation.model.VehicleRequest;
import com.example.vehicleServiceStation.service.VehicleServiceStationCenterService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/serviceStation")
public class ServiceStationController {

     private final VehicleServiceStationCenterService serviceStationService;
     public ServiceStationController(VehicleServiceStationCenterService serviceStationService){
        this.serviceStationService = serviceStationService;
    }

    @PostMapping("/acceptVehicle")
    public ResponseEntity<String> acceptVehicle(@RequestBody VehicleRequest request) {
        System.out.println("Service Request for the vehicle " + request.toString());
        return serviceStationService.acceptVehicleForService(request);
    }

    @PostMapping("/completeService/{serviceRecordId}")
    public ResponseEntity<String> completeServiceForVehicle(@PathVariable Long serviceRecordId) {
        return serviceStationService.completeServiceForVehicle(serviceRecordId);
    }


    // Returns the ServiceRecord details for the given serviceRecordID.
    @GetMapping("/{serviceRecordId}")
    public Optional<ServiceRecord> getServiceRecordDetails(@PathVariable("serviceRecordId") Long serviceRecordId) {
        return serviceStationService.getServiceRecordbyID(serviceRecordId);

    }

    @GetMapping("/delivery/{serviceRecordID}")
    public ResponseEntity<String> getDeliveryDateBasedOnServiceRecord(@PathVariable("serviceRecordID")
                                                                      Long serviceRecordID) {
        // Implement the logic to calculate the delivery date based on service requirements

        return ResponseEntity.ok("Estimated delivery date: " +
                serviceStationService.getDeliveryDateBasedOnServiceRecord(serviceRecordID));
    }


    @GetMapping("/delivery/vehicle/{registrationNumber}")
    public ResponseEntity<String> getDeliveryDateBasedOnVehicleRegNum(@PathVariable("registrationNumber")
                                                                      String registrationNumber) {
        return ResponseEntity.ok("Estimated delivery date: " +
                serviceStationService.getDeliveryDateBasedOnVehicleRegNum(registrationNumber));
    }

    @GetMapping("/vehicle/{registrationNumber}")
    public ResponseEntity<Vehicle>
    getServiceHistoryByVehicleRegistrationNumber(@PathVariable("registrationNumber")
                                                 String registrationNumber) {
        return serviceStationService.getVehicleByRegistrationNumber(registrationNumber);

    }


}