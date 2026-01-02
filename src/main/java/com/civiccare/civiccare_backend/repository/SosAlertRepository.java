package com.civiccare.civiccare_backend.repository;

import com.civiccare.civiccare_backend.model.SosAlert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SosAlertRepository extends JpaRepository<SosAlert, Long> {
}
