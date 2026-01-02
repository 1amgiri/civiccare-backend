package com.civiccare.civiccare_backend.controller;

import com.civiccare.civiccare_backend.repository.BloodDonorRepository;
import com.civiccare.civiccare_backend.repository.EmergencyServiceRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {

    private final EmergencyServiceRepository emergencyRepo;
    private final BloodDonorRepository bloodRepo;

    public DashboardController(
            EmergencyServiceRepository emergencyRepo,
            BloodDonorRepository bloodRepo
    ) {
        this.emergencyRepo = emergencyRepo;
        this.bloodRepo = bloodRepo;
    }

    @GetMapping("/stats")
    public Map<String, Long> stats() {
        Map<String, Long> map = new HashMap<>();
        map.put("verifiedServiceCount", emergencyRepo.countByVerifiedTrue());
        map.put("availableDonorCount", bloodRepo.countByAvailableTrue());
        map.put("activeSosCount", 0L); // placeholder
        return map;
    }
}

