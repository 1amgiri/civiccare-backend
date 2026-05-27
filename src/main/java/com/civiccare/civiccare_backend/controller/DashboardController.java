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
@CrossOrigin(originPatterns = "*")
public class DashboardController {

    private final EmergencyServiceRepository emergencyRepo;
    private final BloodDonorRepository bloodRepo;
    private final com.civiccare.civiccare_backend.repository.SosAlertRepository sosRepo;

    public DashboardController(
            EmergencyServiceRepository emergencyRepo,
            BloodDonorRepository bloodRepo,
            com.civiccare.civiccare_backend.repository.SosAlertRepository sosRepo
    ) {
        this.emergencyRepo = emergencyRepo;
        this.bloodRepo = bloodRepo;
        this.sosRepo = sosRepo;
    }

    @GetMapping("/stats")
    public Map<String, Long> stats(
            @org.springframework.web.bind.annotation.RequestParam(required = false) String city
    ) {
        Map<String, Long> map = new HashMap<>();
        if (city == null || city.trim().isEmpty() || city.equalsIgnoreCase("ALL")) {
            map.put("verifiedServiceCount", emergencyRepo.countByVerifiedTrue());
            map.put("availableDonorCount", bloodRepo.countByAvailableTrue());
            map.put("activeSosCount", sosRepo.countByStatusIgnoreCase("ACTIVE"));
        } else {
            map.put("verifiedServiceCount", emergencyRepo.countByVerifiedTrueAndCityIgnoreCase(city));
            map.put("availableDonorCount", bloodRepo.countByAvailableTrueAndCityIgnoreCase(city));
            map.put("activeSosCount", sosRepo.countByStatusIgnoreCaseAndCityIgnoreCase("ACTIVE", city));
        }
        return map;
    }
}

