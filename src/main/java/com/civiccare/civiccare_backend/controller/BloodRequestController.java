package com.civiccare.civiccare_backend.controller;

import com.civiccare.civiccare_backend.model.BloodRequest;
import com.civiccare.civiccare_backend.service.BloodRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-requests")
public class BloodRequestController {

    @Autowired
    private BloodRequestService bloodRequestService;

    @GetMapping
    public List<BloodRequest> getAllBloodRequests() {
        return bloodRequestService.getAllBloodRequests();
    }

    @PostMapping
    public BloodRequest createBloodRequest(@RequestBody BloodRequest bloodRequest) {
        return bloodRequestService.createBloodRequest(bloodRequest);
    }
}
