package com.labsyncplus.labsync_investigations_microservice.feign;

import com.labsyncplus.labsync_investigations_microservice.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "LABSYNC-PATIENTS-MICROSERVICE")
public interface PatientInterface {
    @GetMapping("patient/allPatients")
    public ResponseEntity<List<Patient>> getAllPatients();

    @GetMapping("patient/getById")
    public ResponseEntity<Patient> getPatientById(@RequestParam int id);

    @PostMapping("patient/add")
    public ResponseEntity<String> addNewPatient(@RequestBody Patient patient);
}
