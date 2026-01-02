package com.civiccare.civiccare_backend.controller;

import com.civiccare.civiccare_backend.dto.CreateEmergencyServiceRequest;
import com.civiccare.civiccare_backend.model.EmergencyService;
import com.civiccare.civiccare_backend.service.EmergencyServiceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency-services")
public class EmergencyServiceController {

    private final EmergencyServiceService emergencyServiceService;

    public EmergencyServiceController(EmergencyServiceService emergencyServiceService) {
        this.emergencyServiceService = emergencyServiceService;
    }

    @PostMapping
    public EmergencyService addEmergencyService(
            @Valid @RequestBody CreateEmergencyServiceRequest request) {

        return emergencyServiceService.addService(request);
    }

    @GetMapping
    public List<EmergencyService> getAll() {
        return emergencyServiceService.getAllServices();
    }
}
