package com.civiccare.civiccare_backend.repository;

import com.civiccare.civiccare_backend.model.BloodDonor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodDonorRepository extends JpaRepository<BloodDonor, Long> {

    List<BloodDonor> findByBloodGroupIgnoreCase(String bloodGroup);

    long countByAvailableTrue();

    List<BloodDonor> findByBloodGroupAndAvailableTrue(String group);
}
