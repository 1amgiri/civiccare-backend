package com.civiccare.civiccare_backend.controller;

import com.civiccare.civiccare_backend.model.SosAlert;
import com.civiccare.civiccare_backend.service.SosAlertService;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sos")
@CrossOrigin(originPatterns = "*")
public class SosAlertController {

    private final SosAlertService sosAlertService;

    public SosAlertController(SosAlertService sosAlertService) {
        this.sosAlertService = sosAlertService;
    }

    @PostMapping
    public Map<String, Object> triggerSos(@RequestBody Map<String, Object> body) {
        Double latitude = body.get("latitude") != null ? Double.valueOf(body.get("latitude").toString()) : 0.0;
        Double longitude = body.get("longitude") != null ? Double.valueOf(body.get("longitude").toString()) : 0.0;
        String city = body.get("city") != null ? body.get("city").toString() : "Metropolis";
        String userName = body.get("userName") != null ? body.get("userName").toString() : "John Citizen";

        SosAlert sos = sosAlertService.createSos(latitude, longitude, city, userName);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Map<String, Object> response = new HashMap<>();
        response.put("id", sos.getId());
        response.put("timestamp", sos.getCreatedAt().format(formatter));

        return response;
    }
}
