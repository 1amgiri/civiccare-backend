package com.civiccare.civiccare_backend.repository;
import com.civiccare.civiccare_backend.model.BloodDonor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodDonorRepository extends JpaRepository<BloodDonor, Integer> {

    // Custom query method
    List<BloodDonor> findByBloodGroupAndAvailableTrue(String bloodGroup);
}