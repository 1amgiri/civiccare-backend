package com.civiccare.civiccare_backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.civiccare.civiccare_backend.model.EmergencyService;
import com.civiccare.civiccare_backend.service.EmergencyServiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.civiccare.civiccare_backend.dto.CreateEmergencyServiceRequest;
import java.util.List;

@RestController
@RequestMapping("/api/emergency-services")
public class EmergencyServiceController {

    private final EmergencyServiceService emergencyServiceService;

    public EmergencyServiceController(EmergencyServiceService emergencyServiceService) {
        this.emergencyServiceService = emergencyServiceService;
    }

    @GetMapping
    public List<EmergencyService> getEmergencyServices(
            @RequestParam(required = false) String city) {

        if (city == null || city.isEmpty()) {
            return emergencyServiceService.getAllServices();
        }
        return emergencyServiceService.getServicesByCity(city);
    }
    @PostMapping
    public EmergencyService addEmergencyService(
            @RequestBody CreateEmergencyServiceRequest request) {
        return emergencyServiceService.addService(request);
    }


}
