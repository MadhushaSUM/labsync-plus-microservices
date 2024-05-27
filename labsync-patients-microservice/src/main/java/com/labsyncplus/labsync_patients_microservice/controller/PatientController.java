package com.labsyncplus.labsync_patients_microservice.controller;

import com.labsyncplus.labsync_patients_microservice.model.Patient;
import com.labsyncplus.labsync_patients_microservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("allPatients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return patientService.getAllPatients();
    }
    @GetMapping("getById")
    public ResponseEntity<Patient> getPatientById(@RequestParam int id) {
        return patientService.getPatientById(id);
    }
    @PostMapping("add")
    public ResponseEntity<String> addNewPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }
}
