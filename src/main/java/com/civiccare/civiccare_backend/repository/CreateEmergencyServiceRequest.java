package com.civiccare.civiccare_backend.dto;

public class CreateEmergencyServiceRequest {

    private String name;
    private String phone;
    private String city;
    private boolean verified;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public boolean isVerified() {
        return verified;
    }
}
