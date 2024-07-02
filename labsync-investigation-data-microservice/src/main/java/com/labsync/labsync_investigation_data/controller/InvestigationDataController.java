package com.labsync.labsync_investigation_data.controller;

import com.labsync.labsync_investigation_data.model.Dto.AddInvestigationDataDto;
import com.labsync.labsync_investigation_data.model.Dto.PatientInvestigationAnalysisDto;
import com.labsync.labsync_investigation_data.model.entity.InvestigationData;
import com.labsync.labsync_investigation_data.service.InvestigationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PutMapping("update")
    public ResponseEntity<String> updateInvestigationData(@RequestBody AddInvestigationDataDto dto, @RequestParam long investigationDataId) {
        return investigationDataService.updateInvestigationData(
                investigationDataId,
                dto.getInvestigation(),
                dto.getInvestigationRegister(),
                dto.getInvestigationData()
        );
    }

    @GetMapping("get")
    public ResponseEntity<List<Map<String, Object>>> getInvestigationData(@RequestParam long investigationRegisterId, @RequestParam long investigationId) {
        return investigationDataService.getInvestigationData(investigationRegisterId, investigationId);
    }

    @GetMapping("patient-analysis")
    public ResponseEntity<PatientInvestigationAnalysisDto> getInvestigationDataWithinDateRange(
            @RequestParam Long patientId,
            @RequestParam Long investigationId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return investigationDataService.getInvestigationDataWithinDateRange(patientId, investigationId, startDate, endDate);
    }

    @GetMapping("test-analysis-overview")
    public ResponseEntity<List<InvestigationData>> getTestAnalysisOverview(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return investigationDataService.getTestAnalysisOverview(startDate, endDate);
    }
}
