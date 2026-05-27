package com.civiccare.civiccare_backend.service;

import com.civiccare.civiccare_backend.model.EmergencyService;
import com.civiccare.civiccare_backend.repository.EmergencyServiceRepository;
import org.springframework.stereotype.Service;
import com.civiccare.civiccare_backend.dto.CreateEmergencyServiceRequest;

import java.util.List;

@Service
public class EmergencyServiceService {

    private final EmergencyServiceRepository emergencyServiceRepository;

    public EmergencyServiceService(EmergencyServiceRepository emergencyServiceRepository) {
        this.emergencyServiceRepository = emergencyServiceRepository;
    }

    public List<EmergencyService> getAllServices() {
        return emergencyServiceRepository.findAll();
    }

    public List<EmergencyService> getServicesByCity(String city) {
        return emergencyServiceRepository.findByCity(city);
    }

    public EmergencyService addService(EmergencyService service) {
        return emergencyServiceRepository.save(service);
    }

    public boolean deleteService(Long id) {
        if (!emergencyServiceRepository.existsById(id)) {
            return false;
        }
        emergencyServiceRepository.deleteById(id);
        return true;
    }
    public EmergencyService addService(CreateEmergencyServiceRequest request) {

        EmergencyService service = new EmergencyService();
        service.setName(request.getName());
        service.setPhone(request.getPhone());
        service.setCity(request.getCity());
        service.setVerified(request.isVerified());

        return emergencyServiceRepository.save(service);
    }
}
