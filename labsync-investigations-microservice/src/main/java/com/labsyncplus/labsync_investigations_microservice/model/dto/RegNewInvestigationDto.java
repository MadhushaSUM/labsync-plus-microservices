package com.labsyncplus.labsync_investigations_microservice.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegNewInvestigationDto {
    private int patient_id;
    private int investigation_id;
    private LocalDate investigation_date;
    private double investigation_cost;
}
