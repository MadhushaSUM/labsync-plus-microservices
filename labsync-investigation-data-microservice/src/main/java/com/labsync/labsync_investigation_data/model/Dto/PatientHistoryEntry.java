package com.labsync.labsync_investigation_data.model.Dto;

import lombok.Data;

import java.util.Map;

@Data
public class PatientHistoryEntry {
    private String date;
    private Map<String, Object> data;
}
