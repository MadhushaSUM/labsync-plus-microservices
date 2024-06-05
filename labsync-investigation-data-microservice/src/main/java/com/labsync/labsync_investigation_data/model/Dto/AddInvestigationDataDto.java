package com.labsync.labsync_investigation_data.model.Dto;

import com.labsync.labsync_investigation_data.model.entity.InvestigationRegister;
import lombok.Data;

import java.util.Map;

@Data
public class AddInvestigationDataDto {
    private InvestigationRegister investigationRegister;
    private Map<String, Object> investigationData;
}
