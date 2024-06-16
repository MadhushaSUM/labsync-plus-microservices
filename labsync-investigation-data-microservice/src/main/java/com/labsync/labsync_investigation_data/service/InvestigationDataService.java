package com.labsync.labsync_investigation_data.service;

import com.labsync.labsync_investigation_data.Dao.InvestigationDataDao;
import com.labsync.labsync_investigation_data.model.entity.Investigation;
import com.labsync.labsync_investigation_data.model.entity.InvestigationRegister;
import com.labsync.labsync_investigation_data.model.entity.InvestigationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

            return new ResponseEntity<>("Investigation data saved", HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error while saving fbs data");
            e.printStackTrace();
            return new ResponseEntity<>("Error while saving investigation data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Map<String, Object>>> getInvestigationData(long investigationRegisterId, long investigationId) {
        try {
            List<InvestigationData> investigationData = investigationDataDao.findByInvestigationRegisterIdAndInvestigationId(investigationRegisterId, investigationId);

            List<Map<String, Object>> list = new ArrayList<>();
            if (!investigationData.isEmpty()) {
                Map<String, Object> dataMap = investigationData.get(0).getDataAsMap();
                dataMap.put("investigationDataId", investigationData.get(0).getId());

                list.add(dataMap);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while getting investigation data");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> updateInvestigationData(long investigationDataId, Investigation investigation, InvestigationRegister investigationRegister, Map<String, Object> investigationDataMap) {
        try {
            InvestigationData investigationData = new InvestigationData();

            investigationData.setId(investigationDataId);
            investigationData.setInvestigationRegister(investigationRegister);
            investigationData.setInvestigation(investigation);
            investigationData.setDataFromMap(investigationDataMap);

            investigationDataDao.save(investigationData);

            return new ResponseEntity<>("Investigation data updated", HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error while saving fbs data");
            e.printStackTrace();
            return new ResponseEntity<>("Error while updating investigation data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<InvestigationData>> getInvestigationDataWithinDateRange(
            long patientId,
            long investigationId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        try {
            List<InvestigationData> dataList = investigationDataDao.findByPatientIdAndInvestigationIdAndDateRange(
                    patientId,
                    investigationId,
                    startDate,
                    endDate
            );

            return new ResponseEntity<>(dataList, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error while getting investigation data within given date range");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
