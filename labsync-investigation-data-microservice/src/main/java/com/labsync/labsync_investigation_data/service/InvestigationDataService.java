package com.labsync.labsync_investigation_data.service;

import com.labsync.labsync_investigation_data.Dao.InvestigationDataDao;
import com.labsync.labsync_investigation_data.model.entity.Investigation;
import com.labsync.labsync_investigation_data.model.entity.InvestigationRegister;
import com.labsync.labsync_investigation_data.model.entity.InvestigationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InvestigationDataService {
    @Autowired
    InvestigationDataDao investigationDataDao;

    public ResponseEntity<String> addInvestigationData(InvestigationRegister investigationRegister, Investigation investigation, Map<String, Object> data) {
        try {
            InvestigationData investigationData = new InvestigationData();
            investigationData.setInvestigationRegister(investigationRegister);
            investigationData.setInvestigation(investigation);
            investigationData.setDataFromMap(data);

            investigationDataDao.save(investigationData);

            return new ResponseEntity<>("fbs data saved", HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error while saving fbs data");
            e.printStackTrace();
            return new ResponseEntity<>("Error while saving fbs data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Map<String, Object>>> getInvestigationData(long investigationRegisterId, long investigationId) {
        try {
            List<InvestigationData> investigationData = investigationDataDao.findByInvestigationRegisterIdAndInvestigationId(investigationRegisterId, investigationId);

            List<Map<String, Object>> list = new ArrayList<>();
            if (!investigationData.isEmpty()) list.add(investigationData.get(0).getDataAsMap());
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while getting investigation data");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
