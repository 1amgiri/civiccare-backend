package com.civiccare.civiccare_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @GetMapping("/stats")
    public Map<String, Integer> getStats() {

        Map<String, Integer> stats = new HashMap<>();
        stats.put("activeSosCount", 0);
        stats.put("availableDonorCount", 0);
        stats.put("verifiedServiceCount", 0);

        return stats;
    }
}
