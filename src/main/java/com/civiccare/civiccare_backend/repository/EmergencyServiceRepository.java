package com.civiccare.civiccare_backend.repository;

import com.civiccare.civiccare_backend.model.EmergencyService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmergencyServiceRepository
        extends JpaRepository<EmergencyService, Long> {

    // ✅ ADD THIS
    List<EmergencyService> findByCity(String city);

    // already used for dashboard
    long countByVerifiedTrue();
}
