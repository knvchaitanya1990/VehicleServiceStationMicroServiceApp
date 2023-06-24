package com.example.vehicleServiceStation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private LocalDateTime acceptedDateTime;
    private LocalDateTime estimateDeliveryDay;
    private double  estimatedCost;
    private double  totalCost;
    private boolean isServiceCompleted;
    private boolean isInsured;
    private boolean isAcceptedForService;

}
