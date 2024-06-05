package com.labsync.labsync_investigation_data.service;

import com.labsync.labsync_investigation_data.Dao.InvestigationDataDao;
import com.labsync.labsync_investigation_data.model.entity.InvestigationRegister;
import com.labsync.labsync_investigation_data.model.entity.InvestigationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class InvestigationDataService {
    @Autowired
    InvestigationDataDao investigationDataDao;

    public ResponseEntity<String> addInvestigationData(InvestigationRegister investigationRegister, Map<String, Object> data) {
        try {
            InvestigationData investigationData = new InvestigationData();
            investigationData.setInvestigationRegister(investigationRegister);
            investigationData.setDataFromMap(data);

            investigationDataDao.save(investigationData);

            return new ResponseEntity<>("fbs data saved", HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error while saving fbs data");
            e.printStackTrace();
            return new ResponseEntity<>("Error while saving fbs data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<InvestigationData> getInvestigationData(long investigationRegisterId) {
        try {
            Optional<InvestigationData> fbsData = investigationDataDao.findByInvestigationRegisterId(investigationRegisterId);

            return fbsData.map(fastingBloodSugar -> new ResponseEntity<>(fastingBloodSugar, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            System.err.println("Error while getting fbs data");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
