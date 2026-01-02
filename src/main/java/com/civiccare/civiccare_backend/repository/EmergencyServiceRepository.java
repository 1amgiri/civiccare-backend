package com.civiccare.civiccare_backend.repository;

import com.civiccare.civiccare_backend.model.EmergencyService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmergencyServiceRepository extends JpaRepository<EmergencyService, Integer> {

    List<EmergencyService> findByCity(String city);

    List<EmergencyService> findByVerifiedTrue();
}
