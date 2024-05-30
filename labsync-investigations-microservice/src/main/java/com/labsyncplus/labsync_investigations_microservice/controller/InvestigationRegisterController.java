package com.labsyncplus.labsync_investigations_microservice.controller;

import com.labsyncplus.labsync_investigations_microservice.model.dto.RegNewInvestigationDto;
import com.labsyncplus.labsync_investigations_microservice.service.InvestigationRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("investigationRegister")
public class InvestigationRegisterController {
    @Autowired
    InvestigationRegisterService investigationRegisterService;

    @PostMapping("registerNew")
    public ResponseEntity<String> registerNewInvestigation(@RequestBody RegNewInvestigationDto newInvestigationData) {
        return investigationRegisterService.addNewRegistration(
                newInvestigationData.getPatient_id(),
                newInvestigationData.getInvestigation_id(),
                newInvestigationData.getInvestigation_date(),
                newInvestigationData.getInvestigation_cost()
        );
    }
}
