package com.civiccare.civiccare_backend.service;

import com.civiccare.civiccare_backend.model.SosAlert;
import com.civiccare.civiccare_backend.repository.SosAlertRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SosAlertService {

    private final SosAlertRepository sosAlertRepository;

    public SosAlertService(SosAlertRepository sosAlertRepository) {
        this.sosAlertRepository = sosAlertRepository;
    }

    public SosAlert createSos(Double latitude, Double longitude) {
        SosAlert sos = new SosAlert();
        sos.setLatitude(latitude);
        sos.setLongitude(longitude);
        sos.setStatus("ACTIVE");
        sos.setCreatedAt(LocalDateTime.now());
        sos.setUserName("John Citizen"); // later replace with auth user
        sos.setCity("Tirupati");

        return sosAlertRepository.save(sos);
    }
}
