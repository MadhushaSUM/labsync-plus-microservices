package com.labsyncplus.labsync_investigations_microservice.model.dto;

import com.labsyncplus.labsync_investigations_microservice.model.InvestigationRegister;
import lombok.Data;

@Data
public class AddFBSDataDto {
    private InvestigationRegister investigationRegister;
    private double fbsValue;
}
