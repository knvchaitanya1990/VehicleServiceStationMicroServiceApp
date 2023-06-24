package com.example.vehicleServiceStation.service;

import com.example.vehicleServiceStation.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    void sendNotificationToCustomer(Customer customer, String message) {
        System.out.println("Sending notification to customer: " + customer.getEmail());
        System.out.println("Message: " + message);
    }
}