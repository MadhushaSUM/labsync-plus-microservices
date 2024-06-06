package com.labsyncplus.labsync_patients_microservice.controller;

import com.labsyncplus.labsync_patients_microservice.model.entity.Doctor;
import com.labsyncplus.labsync_patients_microservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("getAll")
    public ResponseEntity<Page<Doctor>> getAllDoctors(@RequestParam int limit, @RequestParam int skip) {
        PageRequest pageable = PageRequest.of(skip/limit, limit);
        return doctorService.getAllDoctors(pageable);
    }

    @GetMapping("searchByName")
    public ResponseEntity<List<Doctor>> searchDoctorsByName(@RequestParam String query) {
        return doctorService.searchDoctorsByName(query);
    }

    @GetMapping("getById")
    public ResponseEntity<Doctor> getDoctorById(@RequestParam long id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping("add")
    public ResponseEntity<Boolean> addNewDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    @PutMapping("update")
    public ResponseEntity<Doctor> updateDoctor(@RequestParam long id, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }
}
