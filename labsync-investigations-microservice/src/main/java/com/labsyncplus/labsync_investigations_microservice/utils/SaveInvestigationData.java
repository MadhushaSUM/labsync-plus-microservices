package com.labsyncplus.labsync_investigations_microservice.utils;

import com.labsyncplus.labsync_investigations_microservice.feign.InvestigationDataInterface;
import com.labsyncplus.labsync_investigations_microservice.model.InvestigationRegister;
import com.labsyncplus.labsync_investigations_microservice.model.dto.AddFBSDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SaveInvestigationData {

    private final InvestigationDataInterface investigationDataInterface;

    @Autowired
    public SaveInvestigationData(InvestigationDataInterface investigationDataInterface) {
        this.investigationDataInterface = investigationDataInterface;
    }

    public void saveData(InvestigationRegister investigationRegister, Map<String, Object> investigationData) {
        switch (investigationRegister.getInvestigation().getId()) {
            case 1:
                // Handle case 1 if needed
                break;
            case 2:
                AddFBSDataDto dto = new AddFBSDataDto();
                dto.setInvestigationRegister(investigationRegister);
                dto.setFbsValue((Double) investigationData.get("fbsValue"));

                investigationDataInterface.addFBSData(dto);
                break;
            // Add more cases as needed
            default:
                throw new IllegalArgumentException("Unsupported investigation ID: " + investigationRegister.getInvestigation().getId());
        }
    }
}
