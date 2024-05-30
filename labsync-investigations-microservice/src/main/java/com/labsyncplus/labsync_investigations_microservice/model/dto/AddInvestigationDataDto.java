package com.labsyncplus.labsync_investigations_microservice.model.dto;

import lombok.Data;

import java.util.Map;

@Data
public class AddInvestigationDataDto {
    private int investigationRegisterId;
    private Map<String, Object> investigationData;
}
