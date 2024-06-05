package com.labsyncplus.labsync_patients_microservice.Dao;

import com.labsyncplus.labsync_patients_microservice.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDao extends JpaRepository<Doctor, Long> {
}
