package com.labsyncplus.labsync_patients_microservice.service;

import com.labsyncplus.labsync_patients_microservice.Dao.DoctorDao;
import com.labsyncplus.labsync_patients_microservice.model.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorDao doctorDao;

    public ResponseEntity<Page<Doctor>> getAllDoctors(PageRequest pageable) {
        try {
            return new ResponseEntity<>(doctorDao.findAll(pageable), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while getting all doctor data");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Doctor> getDoctorById(long id) {
        Optional<Doctor> doctor = doctorDao.findById(id);
        if (doctor.isPresent()) {
            return new ResponseEntity<Doctor>(doctor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Boolean> addDoctor(Doctor doctor) {
        try {
            doctorDao.save(doctor);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error while adding new doctor");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Doctor> updateDoctor(long id, Doctor doctor) {
        try {
            doctorDao.save(doctor);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while updating the doctor");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
