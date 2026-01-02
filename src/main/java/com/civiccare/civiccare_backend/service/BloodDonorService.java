package com.civiccare.civiccare_backend.service;

import com.civiccare.civiccare_backend.dto.CreateBloodDonorRequest;
import com.civiccare.civiccare_backend.model.BloodDonor;
import org.springframework.stereotype.Service;
import com.civiccare.civiccare_backend.dto.UpdateBloodDonorRequest;


import java.util.ArrayList;
import java.util.List;

@Service
public class BloodDonorService {

    private final List<BloodDonor> donors = new ArrayList<>();
    private int idCounter = 1;

    public BloodDonorService() {
        donors.add(new BloodDonor(idCounter++, "Ravi", "O+", "Tirupati", "9876543210", true));
        donors.add(new BloodDonor(idCounter++, "Suresh", "A+", "Tirupati", "9123456780", true));
        donors.add(new BloodDonor(idCounter++, "Mahesh", "B+", "Chennai", "9000012345", true));
    }

    public List<BloodDonor> getAllDonors() {
        return donors;
    }

    public List<BloodDonor> getDonorsByGroup(String group) {

        List<BloodDonor> result = new ArrayList<>();

        for (BloodDonor donor : donors) {
            if (donor.getBloodGroup().equalsIgnoreCase(group)
                    && donor.isAvailable()) {
                result.add(donor);
            }
        }
        return result;
    }

    public boolean updateDonor(int id, UpdateBloodDonorRequest request) {

        for (BloodDonor donor : donors) {
            if (donor.getId() == id) {

                // Update only allowed fields
                donor.setCity(request.getCity());
                donor.setPhone(request.getPhone());
                donor.setAvailable(request.isAvailable());

                return true;
            }
        }
        return false; // donor not found
    }

    public boolean deleteDonor(int id) {

        return donors.removeIf(donor -> donor.getId() == id);
    }


    public void addDonor(CreateBloodDonorRequest request) {

        BloodDonor donor = new BloodDonor(
                idCounter++,
                request.getName(),
                request.getBloodGroup(),
                request.getCity(),
                request.getPhone(),
                request.isAvailable()
        );

        donors.add(donor);
    }

}
