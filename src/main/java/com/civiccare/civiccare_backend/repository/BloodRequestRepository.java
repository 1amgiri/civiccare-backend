package com.civiccare.civiccare_backend.repository;

import com.civiccare.civiccare_backend.model.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodRequestRepository extends JpaRepository<BloodRequest, Integer> {
}
