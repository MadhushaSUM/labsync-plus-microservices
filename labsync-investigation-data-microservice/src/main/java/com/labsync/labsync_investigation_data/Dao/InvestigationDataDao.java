package com.labsync.labsync_investigation_data.Dao;

import com.labsync.labsync_investigation_data.model.entity.InvestigationData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvestigationDataDao extends JpaRepository<InvestigationData, Long> {
    List<InvestigationData> findByInvestigationRegisterIdAndInvestigationId(long investigationRegisterId, long investigationId);
}
