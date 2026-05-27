package com.civiccare.civiccare_backend.service;

import com.civiccare.civiccare_backend.model.BloodRequest;
import com.civiccare.civiccare_backend.repository.BloodRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodRequestService {

    @Autowired
    private BloodRequestRepository bloodRequestRepository;

    public List<BloodRequest> getAllBloodRequests() {
        return bloodRequestRepository.findAll();
    }

    public BloodRequest createBloodRequest(BloodRequest bloodRequest) {
        return bloodRequestRepository.save(bloodRequest);
    }
}
