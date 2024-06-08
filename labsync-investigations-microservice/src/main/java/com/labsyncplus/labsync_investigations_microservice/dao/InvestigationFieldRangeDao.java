package com.labsyncplus.labsync_investigations_microservice.dao;

import com.labsyncplus.labsync_investigations_microservice.model.entity.InvestigationFieldRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestigationFieldRangeDao extends JpaRepository<InvestigationFieldRange, Long> {
    List<InvestigationFieldRange> findByInvestigationId(long investigationId);
}
