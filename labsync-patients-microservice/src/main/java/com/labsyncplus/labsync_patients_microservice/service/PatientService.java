package com.labsyncplus.labsync_patients_microservice.service;

import com.labsyncplus.labsync_patients_microservice.Dao.InvestigationDataDao;
import com.labsyncplus.labsync_patients_microservice.Dao.InvestigationRegisterDao;
import com.labsyncplus.labsync_patients_microservice.Dao.PatientDao;
import com.labsyncplus.labsync_patients_microservice.model.entity.InvestigationRegister;
import com.labsyncplus.labsync_patients_microservice.model.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientDao patientDao;
    @Autowired
    InvestigationRegisterDao investigationRegisterDao;
    @Autowired
    InvestigationDataDao investigationDataDao;

    public ResponseEntity<Page<Patient>> getAllPatients(PageRequest pageable) {
        try {
            return new ResponseEntity<>(patientDao.findAll(pageable), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while getting all patient data");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Patient> getPatientById(long id) {
        Optional<Patient> patient = patientDao.findById(id);
        if (patient.isPresent()) {
            return new ResponseEntity<Patient>(patient.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Boolean> addPatient(Patient patient) {
        try {
            patientDao.save(patient);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error while adding new patient");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Patient> updatePatient(int id, Patient patient) {
        try {
            patientDao.save(patient);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while updating the patient");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Boolean> deletePatientAnsAssociatedData(List<Long> ids) {
        try {
            for (long id: ids) {
                List<InvestigationRegister> registers = investigationRegisterDao.findByPatientId(id);

                if (registers == null) return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);

                for (InvestigationRegister register: registers) {
                    investigationDataDao.deleteByInvestigationRegisterId(register.getId());
                }
                investigationRegisterDao.deleteByPatientId(id);

                patientDao.deleteById(id);
            }
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while deleting the patients");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Patient>> searchPatientsByName(String query) {
        try {
            return new ResponseEntity<>(patientDao.findByNameContainingIgnoreCase(query), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while getting searched patient data");
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
}
