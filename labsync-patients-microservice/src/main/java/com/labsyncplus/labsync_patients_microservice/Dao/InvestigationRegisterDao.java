package com.labsyncplus.labsync_patients_microservice.Dao;

import com.labsyncplus.labsync_patients_microservice.model.entity.InvestigationRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestigationRegisterDao extends JpaRepository<InvestigationRegister, Long> {
    void deleteByPatientId(Long patientId);

    List<InvestigationRegister> findByPatientId(long id);
}
