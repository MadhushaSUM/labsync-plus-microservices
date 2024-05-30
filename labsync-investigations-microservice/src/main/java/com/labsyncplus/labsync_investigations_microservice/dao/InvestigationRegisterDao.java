package com.labsyncplus.labsync_investigations_microservice.dao;

import com.labsyncplus.labsync_investigations_microservice.model.InvestigationRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestigationRegisterDao extends JpaRepository<InvestigationRegister, Integer> {
}
