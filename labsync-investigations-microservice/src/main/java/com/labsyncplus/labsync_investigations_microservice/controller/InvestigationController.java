package com.labsyncplus.labsync_investigations_microservice.controller;

import com.labsyncplus.labsync_investigations_microservice.model.entity.Investigation;
import com.labsyncplus.labsync_investigations_microservice.model.entity.NormalRangeRule;
import com.labsyncplus.labsync_investigations_microservice.service.InvestigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("investigation")
@CrossOrigin(origins = "http://localhost:3000")
public class InvestigationController {
    @Autowired
    InvestigationService investigationService;

    @GetMapping("allInvestigations")
    public ResponseEntity<List<Investigation>> getAllInvestigations() {
        return investigationService.getAllInvestigations();
    }

    @GetMapping("normal-range-rules")
    public ResponseEntity<List<NormalRangeRule>> getNormalRangesForInvestigation(@RequestParam long investigationId) {
        return investigationService.getNormalRangesByInvestigationId(investigationId);
    }

    @PostMapping("normal-range-rules")
    public ResponseEntity<String> setNormalRangesForInvestigation(@RequestBody NormalRangeRule normalRangeRule) {
        return investigationService.setNormalRangesByInvestigationId(normalRangeRule);
    }
}
