package com.civiccare.civiccare_backend.model;

public class EmergencyService {

    private int id;
    private String name;
    private String phone;
    private String city;
    private boolean verified;

    // Constructor
    public EmergencyService(int id, String name, String phone, String city, boolean verified) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.verified = verified;
    }

    // Getters
    public int getId() {
        return id;
    }

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
