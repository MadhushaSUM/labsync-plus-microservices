package com.labsyncplus.labsync_investigations_microservice.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RegNewInvestigationDto {
    private long patient_id;
    private long doctor_id;
    private List<Long> investigation_ids;
    private LocalDate investigation_date;
    private double investigation_cost;
}
