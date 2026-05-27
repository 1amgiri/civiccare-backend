package com.civiccare.civiccare_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "emergency_services")
public class EmergencyService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String phone;
    private String city;
    private boolean verified;

    // JPA requires a no-args constructor
    public EmergencyService() {
    }

    public EmergencyService(int id, String name, String phone, String city, boolean verified) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.verified = verified;
    }

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

    public void setName(String name) {
        this.name = name;
    }


    public boolean isVerified() {
        return verified;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
