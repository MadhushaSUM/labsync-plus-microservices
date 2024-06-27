package com.labsync.labsync_investigation_data.model.Dto;

import lombok.Data;

import java.util.List;

@Data
public class PatientInvestigationAnalysisDto {
    private List<PatientHistoryEntry> data;
}

