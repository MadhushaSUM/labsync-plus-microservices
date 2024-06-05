package com.labsyncplus.labsync_investigations_microservice.model.dto;

import com.labsyncplus.labsync_investigations_microservice.model.entity.Investigation;
import com.labsyncplus.labsync_investigations_microservice.model.entity.InvestigationRegister;
import lombok.Data;

import java.util.Map;

@Data
public class AddInvestigationDataDto {
    private InvestigationRegister investigationRegister;
    private Investigation investigation;
    private Map<String, Object> investigationData;
}
