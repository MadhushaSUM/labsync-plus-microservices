package com.labsyncplus.labsync_investigations_microservice.service;

import com.labsyncplus.labsync_investigations_microservice.dao.InvestigationRegisterDao;
import com.labsyncplus.labsync_investigations_microservice.model.InvestigationRegister;
import com.labsyncplus.labsync_investigations_microservice.model.dto.RegNewInvestigationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InvestigationRegisterService {
    @Autowired
    InvestigationRegisterDao investigationRegisterDao;


    public ResponseEntity<String> addNewRegistration(RegNewInvestigationDto newInvestigationData) {
        try {
            investigationRegisterDao.save(new InvestigationRegister());
        } catch (Exception e) {

        }
    }
}
