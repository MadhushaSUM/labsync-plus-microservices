package com.labsync.labsync_investigation_data.model.Dto;

import com.labsync.labsync_investigation_data.model.InvestigationRegister;
import lombok.Data;

@Data
public class AddFBSDataDto {
    private InvestigationRegister investigationRegister;
    private double fbsValue;
}
