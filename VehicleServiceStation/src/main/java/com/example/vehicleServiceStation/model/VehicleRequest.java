package com.example.vehicleServiceStation.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {

    private Customer customer;
    private Vehicle vehicle;

}
