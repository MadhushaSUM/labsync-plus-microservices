package com.labsyncplus.labsync_investigations_microservice.dao;

import com.labsyncplus.labsync_investigations_microservice.model.InvestigationRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestigationRegisterDao extends JpaRepository<InvestigationRegister, Integer> {

    List<InvestigationRegister> findByIsDataAddedFalse();
}
