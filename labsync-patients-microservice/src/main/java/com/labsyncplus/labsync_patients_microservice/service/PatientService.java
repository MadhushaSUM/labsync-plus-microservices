package com.labsyncplus.labsync_patients_microservice.service;

import com.labsyncplus.labsync_patients_microservice.Dao.PatientDao;
import com.labsyncplus.labsync_patients_microservice.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientDao patientDao;

    public ResponseEntity<List<Patient>> getAllPatients() {
        try {
            return new ResponseEntity<>(patientDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while getting all patient data");
            e.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Patient> getPatientById(int id) {
        Optional<Patient> patient = patientDao.findById(id);
        if (patient.isPresent()) {
            return new ResponseEntity<Patient>(patient.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> addPatient(Patient patient) {
        try {
            patientDao.save(patient);
            return new ResponseEntity<>("Patient added", HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error while adding new patient");
            e.printStackTrace();
            return new ResponseEntity<>("Error adding patient", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
