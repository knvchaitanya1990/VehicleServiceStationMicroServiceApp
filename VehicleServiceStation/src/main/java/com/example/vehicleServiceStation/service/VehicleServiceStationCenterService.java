package com.example.vehicleServiceStation.service;

import com.example.vehicleServiceStation.client.PoliceVerificationClient;
import com.example.vehicleServiceStation.helper.ServiceCostCalculation;
import com.example.vehicleServiceStation.model.Customer;
import com.example.vehicleServiceStation.model.ServiceRecord;
import com.example.vehicleServiceStation.model.Vehicle;
import com.example.vehicleServiceStation.model.VehicleRequest;
import com.example.vehicleServiceStation.repository.CustomerRepository;
import com.example.vehicleServiceStation.repository.ServiceRecordRepository;
import com.example.vehicleServiceStation.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceStationCenterService {



    @Autowired
    private PoliceVerificationClient policeVerificationClient;
    @Autowired NotificationService notificationService;
    @Autowired ServiceCostCalculation costCalculationService;
    @Autowired  VehicleRepository vehicleRepository;
    @Autowired  CustomerRepository customerRepository;
    @Autowired  ServiceRecordRepository serviceRecordRepository;


    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public ResponseEntity<Vehicle> getVehicleByRegistrationNumber(String registrationNumber) {
         Optional<Vehicle> vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);
        return vehicle.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    public ResponseEntity<String> acceptVehicleForService(VehicleRequest request) {
        Vehicle vehicle = request.getVehicle();
        Customer customer = request.getCustomer();

        if (!isValidVehicleType(vehicle)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle type not supported at our service station. " +
                    "We cannot accept this vehicle for service.");
        }

        if (!policeVerificationClient.verifyVehicleInvolvedInTheft(vehicle.getRegistrationNumber())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle failed police verification for theft. " +
                    "We cannot accept this vehicle for service.");
        }

        // Create a service record
        ServiceRecord serviceRecord = createServiceRecord(vehicle);

        // Save the customer and vehicle
        customerRepository.save(customer);
        vehicle.setCustomer(customer);
        vehicleRepository.save(vehicle);

        // Save the service record
        ServiceRecord savedRecord = serviceRecordRepository.save(serviceRecord);
        Long serviceRecordId = savedRecord.getId();

        // Send notification to the customer
        String message = "Your vehicle has been accepted for service with Request Number: " + serviceRecordId +
                " and estimated delivery of your vehicle on: " + savedRecord.getEstimateDeliveryDay();
        notificationService.sendNotificationToCustomer(vehicle.getCustomer(), message);

        return ResponseEntity.ok(message);
    }

    private boolean isValidVehicleType(Vehicle vehicle) {
        String vehicleType = vehicle.getVehicleType();
        return vehicleType.equals("BIKE") || vehicleType.equals("CAR");
    }

    private ServiceRecord createServiceRecord(Vehicle vehicle) {
        ServiceRecord serviceRecord = new ServiceRecord();
        serviceRecord.setVehicle(vehicle);
        double estimateCost = costCalculationService.estimateCost(serviceRecord);
        serviceRecord.setEstimatedCost(estimateCost);
        serviceRecord.setAcceptedForService(true);
        serviceRecord.setAcceptedDateTime(LocalDateTime.now());
        serviceRecord.setEstimateDeliveryDay(LocalDateTime.now().plusDays(3));
        return serviceRecord;
    }

    public ResponseEntity<String> completeServiceForVehicle(Long serviceRequestId) {
        ServiceRecord serviceRecord = serviceRecordRepository.getReferenceById(serviceRequestId);
        serviceRecord.setInsured(policeVerificationClient.verifyVehicleInvolvedInTheft(serviceRecord.getVehicle().getRegistrationNumber()));
        double totalServiceCost = costCalculationService.serviceCostCalculation(serviceRecord);
        serviceRecord.setServiceCompleted(true);
        serviceRecordRepository.save(serviceRecord);

        Vehicle vehicle = serviceRecord.getVehicle();
        Customer customer = vehicle.getCustomer();
        String message = "Your vehicle service is completed with a total Service Cost of " + totalServiceCost;
        notificationService.sendNotificationToCustomer(customer, message);

        return ResponseEntity.ok("Vehicle service completion updated successfully.");
    }


    public Optional<ServiceRecord> getServiceRecordbyID(Long serviceRecordId) {

        return serviceRecordRepository.findById(serviceRecordId);
    }


    public ResponseEntity<Optional<LocalDateTime>> getDeliveryDateBasedOnServiceRecord(Long serviceRecordID) {
        Optional<ServiceRecord> serviceRecord = serviceRecordRepository.findById(serviceRecordID);
        if (serviceRecord.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(serviceRecord.map(ServiceRecord::getEstimateDeliveryDay));
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    // Need to Fix this use case
    public ResponseEntity<Optional<LocalDateTime>> getDeliveryDateBasedOnVehicleRegNum(String registrationNumber) {

        Optional<Vehicle> vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);
        Long vehicleId = vehicle.map(Vehicle::getId).orElse(null);
        List<ServiceRecord> serviceRecordList = serviceRecordRepository.findByVehicleId(vehicleId);
        ServiceRecord lastServiceRecord = serviceRecordList.get(serviceRecordList.size() - 1);
        return getDeliveryDateBasedOnServiceRecord(lastServiceRecord.getId());
    }
}
