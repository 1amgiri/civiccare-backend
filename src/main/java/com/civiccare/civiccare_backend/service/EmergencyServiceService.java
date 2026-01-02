package com.civiccare.civiccare_backend.service;

import com.civiccare.civiccare_backend.model.EmergencyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmergencyServiceService {

    public List<EmergencyService> getAllServices() {

        List<EmergencyService> services = new ArrayList<>();

        services.add(new EmergencyService(1, "Police", "100", "Tirupati", true));
        services.add(new EmergencyService(2, "Ambulance", "108", "Tirupati", true));
        services.add(new EmergencyService(3, "Fire", "101", "Tirupati", true));
        services.add(new EmergencyService(4, "Police", "100", "Chennai", true));

        return services;
    }
}
