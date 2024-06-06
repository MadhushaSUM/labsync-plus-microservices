package com.labsyncplus.labsync_investigations_microservice.controller;

import com.labsyncplus.labsync_investigations_microservice.model.entity.InvestigationRegister;
import com.labsyncplus.labsync_investigations_microservice.model.dto.AddInvestigationDataRequestDto;
import com.labsyncplus.labsync_investigations_microservice.model.dto.RegNewInvestigationDto;
import com.labsyncplus.labsync_investigations_microservice.service.InvestigationRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("investigationRegister")
@CrossOrigin(origins = "http://localhost:3000")
public class InvestigationRegisterController {
    @Autowired
    InvestigationRegisterService investigationRegisterService;

    @PostMapping("add")
    public ResponseEntity<String> registerNewInvestigation(@RequestBody RegNewInvestigationDto newInvestigationData) {
        return investigationRegisterService.addNewRegistration(
                newInvestigationData.getPatient_id(),
                newInvestigationData.getDoctor_id(),
                newInvestigationData.getInvestigation_ids(),
                newInvestigationData.getInvestigation_date(),
                newInvestigationData.getInvestigation_cost()
        );
    }

    @GetMapping("getAll")
    public ResponseEntity<Page<InvestigationRegister>> getAllInvestigationRegistrations(@RequestParam int limit, @RequestParam int skip) {
        PageRequest pageable = PageRequest.of(skip/limit, limit);
        return investigationRegisterService.getAllInvestigationRegistrations(pageable);
    }

    @GetMapping("getById")
    public ResponseEntity<InvestigationRegister> getInvestigationRegistrationById(@RequestParam int investigationRegisterId) {
        return investigationRegisterService.getInvestigationRegistrationById(investigationRegisterId);
    }

    @GetMapping("allNoDataRegistrations")
    public ResponseEntity<List<InvestigationRegister>> getAllNoDataInvestigationRegistrations() {
        return investigationRegisterService.getAllNoDataInvestigationRegistrations();
    }

    @GetMapping("allNotPrintedRegistrations")
    public ResponseEntity<List<InvestigationRegister>> getAllNotPrintedInvestigationRegistrations() {
        return investigationRegisterService.getAllNotPrintedInvestigationRegistrations();
    }

    @PostMapping("addInvestigationData")
    public ResponseEntity<String> addInvestigationData(@RequestBody AddInvestigationDataRequestDto addInvestigationDataRequestDto) {
        return investigationRegisterService.addInvestigationData(
                addInvestigationDataRequestDto.getInvestigationRegisterId(),
                addInvestigationDataRequestDto.getInvestigationId(),
                addInvestigationDataRequestDto.getInvestigationData()
        );
    }
}
