package com.labsyncplus.labsync_investigations_microservice.dao;

import com.labsyncplus.labsync_investigations_microservice.model.entity.NormalRangeRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NormalRangeRuleDao extends JpaRepository<NormalRangeRule, Long> {
    List<NormalRangeRule> findByInvestigationId(@Param("investigationId") long investigationId);
}
