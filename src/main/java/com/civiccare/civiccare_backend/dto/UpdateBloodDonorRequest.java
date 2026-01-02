package com.civiccare.civiccare_backend.dto;

public class UpdateBloodDonorRequest {

    private String city;
    private String phone;
    private boolean available;

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
