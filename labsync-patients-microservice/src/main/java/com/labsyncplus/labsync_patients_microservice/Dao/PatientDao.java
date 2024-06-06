package com.labsyncplus.labsync_patients_microservice.Dao;

import com.labsyncplus.labsync_patients_microservice.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDao extends JpaRepository<Patient, Long> {

    List<Patient> findByNameContainingIgnoreCase(@Param("name") String name);
}
