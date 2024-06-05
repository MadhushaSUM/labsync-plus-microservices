package com.labsyncplus.labsync_investigations_microservice.dao;

import com.labsyncplus.labsync_investigations_microservice.model.entity.Investigation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestigationDao extends JpaRepository<Investigation, Long> {
}
