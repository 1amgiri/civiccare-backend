package com.civiccare.civiccare_backend.controller;

import com.civiccare.civiccare_backend.model.SosAlert;
import com.civiccare.civiccare_backend.service.SosAlertService;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sos")
@CrossOrigin(origins = "http://localhost:3000")
public class SosAlertController {

    private final SosAlertService sosAlertService;

    public SosAlertController(SosAlertService sosAlertService) {
        this.sosAlertService = sosAlertService;
    }

    @PostMapping
    public Map<String, Object> triggerSos(@RequestBody Map<String, Double> body) {
        SosAlert sos = sosAlertService.createSos(
                body.get("latitude"),
                body.get("longitude")
        );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Map<String, Object> response = new HashMap<>();
        response.put("id", sos.getId());
        response.put("timestamp", sos.getCreatedAt().format(formatter));

        return response;
    }
}
