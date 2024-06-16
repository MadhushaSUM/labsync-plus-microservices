package com.labsyncplus.labsync_investigations_microservice.utils;

import com.labsyncplus.labsync_investigations_microservice.feign.InvestigationDataInterface;
import com.labsyncplus.labsync_investigations_microservice.model.entity.Investigation;
import com.labsyncplus.labsync_investigations_microservice.model.entity.InvestigationRegister;
import com.labsyncplus.labsync_investigations_microservice.model.dto.AddInvestigationDataDto;
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

    public void saveData(InvestigationRegister investigationRegister, Investigation investigation, Map<String, Object> investigationData) {
        try  {
            AddInvestigationDataDto dto = new AddInvestigationDataDto();
            dto.setInvestigationRegister(investigationRegister);
            dto.setInvestigation(investigation);
            dto.setInvestigationData(investigationData);
            investigationDataInterface.addInvestigationData(dto);

        } catch (Exception e) {
            throw new IllegalArgumentException("Investigation data saving failed");
        }
    }

    public void updateData(long investigationDataId, InvestigationRegister investigationRegister, Investigation investigation, Map<String, Object> investigationData) {
        try  {
            AddInvestigationDataDto dto = new AddInvestigationDataDto();
            dto.setInvestigationRegister(investigationRegister);
            dto.setInvestigation(investigation);
            dto.setInvestigationData(investigationData);
            investigationDataInterface.updateInvestigationData(dto, investigationDataId);

        } catch (Exception e) {
            throw new IllegalArgumentException("Investigation data updating failed");
        }
    }
}
