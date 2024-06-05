package com.labsyncplus.labsync_investigations_microservice.service;

import com.labsyncplus.labsync_investigations_microservice.dao.InvestigationRegisterDao;
import com.labsyncplus.labsync_investigations_microservice.exceptions.InvestigationNotFoundException;
import com.labsyncplus.labsync_investigations_microservice.feign.PatientInterface;
import com.labsyncplus.labsync_investigations_microservice.model.entity.Investigation;
import com.labsyncplus.labsync_investigations_microservice.model.entity.InvestigationRegister;
import com.labsyncplus.labsync_investigations_microservice.model.entity.Patient;
import com.labsyncplus.labsync_investigations_microservice.utils.RequiredInvestigationFields;
import com.labsyncplus.labsync_investigations_microservice.utils.SaveInvestigationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InvestigationRegisterService {

    @Autowired
    InvestigationRegisterDao investigationRegisterDao;
    @Autowired
    InvestigationService investigationService;
    @Autowired
    PatientInterface patientInterface;
    @Autowired
    SaveInvestigationData saveInvestigationData;

    public ResponseEntity<String> addNewRegistration(int patientId, int investigationId, LocalDate investigationDate, double investigationCost) {
        try {
            Patient patient = patientInterface.getPatientById(patientId).getBody();
            Optional<Investigation> investigation = investigationService.getInvestigationById(investigationId).getBody();

            if (patient != null && investigation.isPresent()) {

                InvestigationRegister register = new InvestigationRegister();
                register.setPatient(patient);
                register.setInvestigation(investigation.get());
                register.setCost(investigationCost);
                register.setRegisteredDate(investigationDate);

                investigationRegisterDao.save(register);

                return new ResponseEntity<>("Investigation registered", HttpStatus.CREATED);
            }

            return new ResponseEntity<>("Invalid patient or investigation", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println("Error while registering investigation");
            e.printStackTrace();
            return new ResponseEntity<>("Server error while registering investigation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<InvestigationRegister>> getAllInvestigationRegistrations() {
        try {
            return new ResponseEntity<>(investigationRegisterDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while retrieving all investigation registrations");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<InvestigationRegister>> getAllNoDataInvestigationRegistrations() {
        try {
            return new ResponseEntity<>(investigationRegisterDao.findByIsDataAddedFalse(), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while retrieving all no data investigation registrations");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addInvestigationData(long investigationRegisterId, Map<String, Object> investigationData) {
        Optional<InvestigationRegister> investigationRegister = investigationRegisterDao.findById(investigationRegisterId);
        if (investigationRegister.isEmpty()) return new ResponseEntity<>("Invalid investigation register Id", HttpStatus.BAD_REQUEST);

        try {
            RequiredInvestigationFields.checkRequiredFieldAvailabilityAndType(
                    investigationRegister.get().getInvestigation().getId(),
                    investigationData
            );

            saveInvestigationData.saveData(
                    investigationRegister.get(),
                    investigationData
            );

            investigationRegisterDao.setDataAdded(investigationRegisterId);

            return new ResponseEntity<>("Investigation data saved", HttpStatus.CREATED);

        } catch (InvestigationNotFoundException e) {
            System.err.println("Investigation is not defined in required fields Map");
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<InvestigationRegister> getInvestigationRegistrationById(long investigationRegisterId) {
        try {
            Optional<InvestigationRegister> investigationRegister = investigationRegisterDao.findById(investigationRegisterId);

            if (investigationRegister.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(investigationRegister.get(), HttpStatus.OK);
            }

        } catch (Exception e) {
            System.err.println("Error while retrieving investigation registration by id");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<InvestigationRegister>> getAllNotPrintedInvestigationRegistrations() {
        try {
            return new ResponseEntity<>(investigationRegisterDao.findByIsPrintedFalse(), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while retrieving all no data investigation registrations");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
