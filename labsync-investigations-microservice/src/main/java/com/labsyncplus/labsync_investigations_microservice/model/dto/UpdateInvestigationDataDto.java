package com.labsyncplus.labsync_investigations_microservice.model.dto;

import lombok.Data;

import java.util.Map;

@Data
public class UpdateInvestigationDataDto {
    private long investigationDataId;
    private long investigationRegisterId;
    private long investigationId;
    private Map<String, Object> investigationData;
}
