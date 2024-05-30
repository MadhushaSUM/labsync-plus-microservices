package com.labsyncplus.labsync_investigations_microservice.service;

import com.labsyncplus.labsync_investigations_microservice.dao.InvestigationRegisterDao;
import com.labsyncplus.labsync_investigations_microservice.feign.PatientInterface;
import com.labsyncplus.labsync_investigations_microservice.model.Investigation;
import com.labsyncplus.labsync_investigations_microservice.model.InvestigationRegister;
import com.labsyncplus.labsync_investigations_microservice.model.Patient;
import com.labsyncplus.labsync_investigations_microservice.model.dto.RegNewInvestigationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class InvestigationRegisterService {

    @Autowired
    InvestigationRegisterDao investigationRegisterDao;
    @Autowired
    InvestigationService investigationService;
    @Autowired
    PatientInterface patientInterface;

    public ResponseEntity<String> addNewRegistration(int patientId, int investigationId, LocalDate investigationDate, double investigationCost) {
        try {
            Patient patient = patientInterface.getPatientById(patientId).getBody();
            Optional<Investigation> investigation = investigationService.getInvestigationById(investigationId).getBody();

            if (patient != null && investigation.isPresent()) {
                investigationRegisterDao.save(new InvestigationRegister(
                        patient,
                        investigation.get(),
                        investigationDate,
                        investigationCost
                ));

                return new ResponseEntity<>("Investigation registered", HttpStatus.CREATED);
            }

            return new ResponseEntity<>("Invalid patient or investigation", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println("Error while registering investigation");
            e.printStackTrace();
            return new ResponseEntity<>("Server error while registring investigation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
