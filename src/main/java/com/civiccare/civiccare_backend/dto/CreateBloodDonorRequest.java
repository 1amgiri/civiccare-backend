package com.civiccare.civiccare_backend.dto;

public class CreateBloodDonorRequest {

    private String name;
    private String bloodGroup;
    private String city;
    private String phone;
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
