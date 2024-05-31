package com.labsync.labsync_investigation_data.controller;

import com.labsync.labsync_investigation_data.model.Dto.AddFBSDataDto;
import com.labsync.labsync_investigation_data.model.investigations.FastingBloodSugar;
import com.labsync.labsync_investigation_data.service.FastingBloodSugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("investigationData")
public class InvestigationDataController {

    @Autowired
    FastingBloodSugarService fastingBloodSugarService;

    @PostMapping("FBS")
    public ResponseEntity<String> addFBSData(@RequestBody AddFBSDataDto addFBSDataDto) {
        return fastingBloodSugarService.addFastingBloodSugarData(
                addFBSDataDto.getInvestigationRegister(),
                addFBSDataDto.getFbsValue()
        );
    }

    @GetMapping("FBS/{investigationRegisterId}")
    public ResponseEntity<FastingBloodSugar> getFBSData(@PathVariable int investigationRegisterId) {
        return fastingBloodSugarService.getFastingBloodSugarData(investigationRegisterId);
    }
}
