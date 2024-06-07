package com.labsync.labsync_investigation_data.controller;

import com.labsync.labsync_investigation_data.model.Dto.AddInvestigationDataDto;
import com.labsync.labsync_investigation_data.service.InvestigationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("investigationData")
@CrossOrigin(origins = "http://localhost:3000")
public class InvestigationDataController {

    @Autowired
    InvestigationDataService investigationDataService;

    @PostMapping("add")
    public ResponseEntity<String> addInvestigationData(@RequestBody AddInvestigationDataDto dto) {
        return investigationDataService.addInvestigationData(
                dto.getInvestigationRegister(),
                dto.getInvestigation(),
                dto.getInvestigationData()
        );
    }

    @GetMapping("get")
    public ResponseEntity<List<Map<String, Object>>> getInvestigationData(@RequestParam long investigationRegisterId, @RequestParam long investigationId) {
        return investigationDataService.getInvestigationData(investigationRegisterId, investigationId);
    }
}
