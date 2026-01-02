package com.civiccare.civiccare_backend.service;

import com.civiccare.civiccare_backend.dto.CreateBloodDonorRequest;
import com.civiccare.civiccare_backend.dto.UpdateBloodDonorRequest;
import com.civiccare.civiccare_backend.model.BloodDonor;
import com.civiccare.civiccare_backend.repository.BloodDonorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodDonorService {

    private final BloodDonorRepository bloodDonorRepository;

    public BloodDonorService(BloodDonorRepository bloodDonorRepository) {
        this.bloodDonorRepository = bloodDonorRepository;
    }

    public List<BloodDonor> getAllDonors() {
        return bloodDonorRepository.findAll();
    }

    public List<BloodDonor> getDonorsByGroup(String group) {
        return bloodDonorRepository.findByBloodGroupAndAvailableTrue(group);
    }

    public void addDonor(CreateBloodDonorRequest request) {

        BloodDonor donor = new BloodDonor();
        donor.setAvailable(request.isAvailable());
        donor.setCity(request.getCity());
        donor.setPhone(request.getPhone());

        // fields set only once
        donor.setAvailable(request.isAvailable());

        // set immutable fields via constructor-like approach
        donor = new BloodDonor(
                0,
                request.getName(),
                request.getBloodGroup(),
                request.getCity(),
                request.getPhone(),
                request.isAvailable()
        );

        bloodDonorRepository.save(donor);
    }

    public boolean updateDonor(int id, UpdateBloodDonorRequest request) {

        Optional<BloodDonor> optionalDonor = bloodDonorRepository.findById(id);

        if (optionalDonor.isEmpty()) {
            return false;
        }

        BloodDonor donor = optionalDonor.get();
        donor.setCity(request.getCity());
        donor.setPhone(request.getPhone());
        donor.setAvailable(request.isAvailable());

        bloodDonorRepository.save(donor);
        return true;
    }

    public boolean deleteDonor(int id) {

        if (!bloodDonorRepository.existsById(id)) {
            return false;
        }

        bloodDonorRepository.deleteById(id);
        return true;
    }
}
