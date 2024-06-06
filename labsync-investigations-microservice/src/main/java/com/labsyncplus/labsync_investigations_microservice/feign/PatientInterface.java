package com.labsyncplus.labsync_investigations_microservice.feign;

import com.labsyncplus.labsync_investigations_microservice.model.entity.Doctor;
import com.labsyncplus.labsync_investigations_microservice.model.entity.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "LABSYNC-PATIENTS-MICROSERVICE")
public interface PatientInterface {
    @GetMapping("patient/allPatients")
    public ResponseEntity<List<Patient>> getAllPatients();

    @GetMapping("patient/getById")
    public ResponseEntity<Patient> getPatientById(@RequestParam long id);

    @PostMapping("patient/add")
    public ResponseEntity<Boolean> addNewPatient(@RequestBody Patient patient);

    @GetMapping("doctor/getAll")
    public ResponseEntity<Page<Doctor>> getAllDoctors(@RequestParam int limit, @RequestParam int skip);

    @GetMapping("doctor/searchByName")
    public ResponseEntity<List<Doctor>> searchDoctorsByName(@RequestParam String query);

    @GetMapping("doctor/getById")
    public ResponseEntity<Doctor> getDoctorById(@RequestParam long id);

    @PostMapping("doctor/add")
    public ResponseEntity<Boolean> addNewDoctor(@RequestBody Doctor doctor);

    @PutMapping("doctor/update")
    public ResponseEntity<Doctor> updateDoctor(@RequestParam long id, @RequestBody Doctor doctor);
}
