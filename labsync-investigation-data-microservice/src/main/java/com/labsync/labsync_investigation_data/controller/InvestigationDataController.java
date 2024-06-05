package com.labsync.labsync_investigation_data.controller;

import com.labsync.labsync_investigation_data.model.Dto.AddInvestigationDataDto;
import com.labsync.labsync_investigation_data.model.entity.InvestigationData;
import com.labsync.labsync_investigation_data.service.InvestigationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("investigationData")
public class InvestigationDataController {

    @Autowired
    InvestigationDataService investigationDataService;

    @PostMapping("add")
    public ResponseEntity<String> addInvestigationData(@RequestBody AddInvestigationDataDto dto) {
        return investigationDataService.addInvestigationData(
                dto.getInvestigationRegister(),
                dto.getInvestigationData()
        );
    }

    @GetMapping("get")
    public ResponseEntity<InvestigationData> getInvestigationData(@RequestParam long investigationRegisterId) {
        return investigationDataService.getInvestigationData(investigationRegisterId);
    }
}
