package com.civiccare.civiccare_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateBloodDonorRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Blood group is required")
    private String bloodGroup;

    @NotBlank(message = "City is required")
    private String city;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    // FIX: Add field back to match the frontend request body
    private boolean available;

    public String getName() {
        return name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isAvailable() {
        return available;
    }
}
