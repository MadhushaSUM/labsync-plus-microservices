package com.labsyncplus.labsync_patients_microservice.controller;

import com.labsyncplus.labsync_patients_microservice.model.entity.Patient;
import com.labsyncplus.labsync_patients_microservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patient")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("getAll")
    public ResponseEntity<Page<Patient>> getAllPatients(@RequestParam int limit, @RequestParam int skip) {
        PageRequest pageable = PageRequest.of(skip/limit, limit);
        return patientService.getAllPatients(pageable);
    }

    @GetMapping("getById")
    public ResponseEntity<Patient> getPatientById(@RequestParam long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("add")
    public ResponseEntity<Boolean> addNewPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @PutMapping("update")
    public ResponseEntity<Patient> updatePatient(@RequestParam int id, @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Boolean> deletePatients(@RequestParam List<Long> ids) {
        return patientService.deletePatientAnsAssociatedData(ids);
    }
}
