package com.labsyncplus.labsync_patients_microservice.Dao;

import com.labsyncplus.labsync_patients_microservice.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient, Long> {
}
