package com.labsyncplus.labsync_investigations_microservice.model.dto;

import lombok.Data;

@Data
public class InvestigationNormalRangesDto {
    private long id;
    private String fieldName;
    private double high;
    private double low;
}
