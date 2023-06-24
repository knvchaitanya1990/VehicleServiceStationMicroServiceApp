package com.example.vehicleServiceStation.repository;


import com.example.vehicleServiceStation.model.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord,Long> {

    List<ServiceRecord> findByVehicleId(Long vehicleId);

}
