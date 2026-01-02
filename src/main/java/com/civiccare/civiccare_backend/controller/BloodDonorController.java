package com.civiccare.civiccare_backend.controller;

import org.springframework.validation.annotation.Validated;
import com.civiccare.civiccare_backend.dto.UpdateBloodDonorRequest;
import com.civiccare.civiccare_backend.dto.CreateBloodDonorRequest;
import com.civiccare.civiccare_backend.model.BloodDonor;
import com.civiccare.civiccare_backend.service.BloodDonorService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/blood-donors")
@Validated
public class BloodDonorController {

    private final BloodDonorService bloodDonorService;

    public BloodDonorController(BloodDonorService bloodDonorService) {
        this.bloodDonorService = bloodDonorService;
    }

    @GetMapping("/paged")
    public Page<BloodDonor> getDonorsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return bloodDonorService.getDonorsWithPagination(page, size, sortBy, direction);
    }


    @PostMapping
    public String addBloodDonor(
            @Valid @RequestBody CreateBloodDonorRequest request) {

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
