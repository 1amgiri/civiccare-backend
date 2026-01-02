package com.civiccare.civiccare_backend.model;

public class BloodDonor {

    private int id;
    private String name;
    private String bloodGroup;
    private String city;
    private String phone;
    private boolean available;

    public BloodDonor(int id, String name, String bloodGroup, String city, String phone, boolean available) {
        this.id = id;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.city = city;
        this.phone = phone;
        this.available = available;
    }

    public int getId() {
        return id;
    }

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
