package com.civiccare.civiccare_backend.controller;

import com.civiccare.civiccare_backend.model.EmergencyService;
import com.civiccare.civiccare_backend.service.EmergencyServiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emergency-services")
public class EmergencyServiceController {

    private final EmergencyServiceService emergencyServiceService;

    public EmergencyServiceController(EmergencyServiceService emergencyServiceService) {
        this.emergencyServiceService = emergencyServiceService;
    }

    @GetMapping
    public List<EmergencyService> getAllEmergencyServices() {
        return emergencyServiceService.getAllServices();
    }
}
