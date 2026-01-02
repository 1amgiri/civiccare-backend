package com.civiccare.civiccare_backend.controller;

import com.civiccare.civiccare_backend.dto.UpdateBloodDonorRequest;
import com.civiccare.civiccare_backend.dto.CreateBloodDonorRequest;
import com.civiccare.civiccare_backend.model.BloodDonor;
import com.civiccare.civiccare_backend.service.BloodDonorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-donors")
public class BloodDonorController {

    private final BloodDonorService bloodDonorService;

    public BloodDonorController(BloodDonorService bloodDonorService) {
        this.bloodDonorService = bloodDonorService;
    }

    @GetMapping
    public List<BloodDonor> getBloodDonors(
            @RequestParam(required = false) String group) {

        if (group == null || group.isEmpty()) {
            return bloodDonorService.getAllDonors();
        }
        return bloodDonorService.getDonorsByGroup(group);
    }

    @PostMapping
    public String addBloodDonor(@RequestBody CreateBloodDonorRequest request) {

        bloodDonorService.addDonor(request);
        return "Donor added successfully";
    }
    @PutMapping("/{id}")
    public String updateBloodDonor(
            @PathVariable int id,
            @RequestBody UpdateBloodDonorRequest request) {

        boolean updated = bloodDonorService.updateDonor(id, request);

        if (!updated) {
            return "Donor not found";
        }
        return "Donor updated successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteBloodDonor(@PathVariable int id) {

        boolean deleted = bloodDonorService.deleteDonor(id);

        if (!deleted) {
            return "Donor not found";
        }
        return "Donor deleted successfully";
    }

}
