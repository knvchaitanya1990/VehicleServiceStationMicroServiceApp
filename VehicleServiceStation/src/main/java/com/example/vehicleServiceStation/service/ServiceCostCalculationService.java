package com.example.vehicleServiceStation.service;

import com.example.vehicleServiceStation.model.ServiceRecord;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ServiceCostCalculationService {


    public double serviceCostCalculation(ServiceRecord serviceRecord) {

        // Check if the vehicle has valid  insurance  - Call insurance Service

        double disCountedValue = 0.0;
        if (serviceRecord.isInsured()) {
            double disCountPercentage = getDiscountBasedOnService(0, 50);
            disCountedValue = calculateDiscount(serviceRecord.getEstimatedCost(), disCountPercentage);
        }
        return disCountedValue;
    }

    private static double calculateDiscount(Double originalCost, double disCountPercentage) {

        double disCountAmount =  originalCost*(disCountPercentage/100);
        return originalCost -disCountAmount;
    }

    private double getDiscountBasedOnService(int min, int max) {
        Random random =new Random();
        return random.nextInt(max-min+1)+min;

    }

    public double estimateCost(ServiceRecord serviceRecord) {

        double minValue = 200.0;
        double maxValue = 25000.0;
        return  minValue + (int)(Math.random() * ((maxValue - minValue) + 1));
    }
}
