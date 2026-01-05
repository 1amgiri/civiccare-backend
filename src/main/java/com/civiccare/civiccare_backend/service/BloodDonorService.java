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
        // FIX: Use the default constructor and setters for robust instantiation
        BloodDonor donor = new BloodDonor();
        donor.setName(request.getName());
        donor.setBloodGroup(request.getBloodGroup());
        donor.setCity(request.getCity());
        donor.setPhone(request.getPhone());
        donor.setAvailable(true); // New donors are always available
        bloodDonorRepository.save(donor);
    }

    public boolean updateDonor(Long id, UpdateBloodDonorRequest request) {

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

    public boolean deleteDonor(Long id) {

        if (!bloodDonorRepository.existsById(id)) {
            return false;
        }

        bloodDonorRepository.deleteById(id);
        return true;
    }
}
