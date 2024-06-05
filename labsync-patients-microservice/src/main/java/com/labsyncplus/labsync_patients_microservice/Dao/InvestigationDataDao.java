package com.labsyncplus.labsync_patients_microservice.Dao;

import com.labsyncplus.labsync_patients_microservice.model.entity.InvestigationData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestigationDataDao extends JpaRepository<InvestigationData, Long> {
    void deleteByInvestigationRegisterId(Long investigationRegisterId);
}
