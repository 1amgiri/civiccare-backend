package com.civiccare.civiccare_backend.repository;

import com.civiccare.civiccare_backend.model.BloodDonor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BloodDonorRepository
        extends JpaRepository<BloodDonor, Long> {

    // ✅ ADD THIS
    List<BloodDonor> findByBloodGroupAndAvailableTrue(String bloodGroup);

    long countByAvailableTrue();
}
