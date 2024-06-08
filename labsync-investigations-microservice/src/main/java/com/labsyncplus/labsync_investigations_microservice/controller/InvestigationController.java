package com.labsyncplus.labsync_investigations_microservice.controller;

import com.labsyncplus.labsync_investigations_microservice.model.dto.InvestigationNormalRangesDto;
import com.labsyncplus.labsync_investigations_microservice.model.entity.Investigation;
import com.labsyncplus.labsync_investigations_microservice.service.InvestigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("investigation")
public class InvestigationController {
    @Autowired
    InvestigationService investigationService;

    @GetMapping("allInvestigations")
    public ResponseEntity<List<Investigation>> getAllInvestigations() {
        return investigationService.getAllInvestigations();
    }

    @GetMapping("normalRanges")
    public ResponseEntity<List<InvestigationNormalRangesDto>> getInvestigationNormalRange(@RequestParam long investigationId) {
        return investigationService.getInvestigationNormalRange(investigationId);
    }
}
