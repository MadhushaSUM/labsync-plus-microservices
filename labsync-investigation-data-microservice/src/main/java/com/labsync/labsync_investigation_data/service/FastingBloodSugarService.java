package com.labsync.labsync_investigation_data.service;

import com.labsync.labsync_investigation_data.Dao.FastingBloodSugarDao;
import com.labsync.labsync_investigation_data.model.InvestigationRegister;
import com.labsync.labsync_investigation_data.model.investigations.FastingBloodSugar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FastingBloodSugarService {
    @Autowired
    FastingBloodSugarDao fastingBloodSugarDao;

    public ResponseEntity<String> addFastingBloodSugarData(InvestigationRegister investigationRegister, double fbsValue) {
        try {
            fastingBloodSugarDao.save(new FastingBloodSugar(
                    investigationRegister,
                    fbsValue
            ));

            return new ResponseEntity<>("fbs data saved", HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error while saving fbs data");
            e.printStackTrace();
            return new ResponseEntity<>("Error while saving fbs data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<FastingBloodSugar> getFastingBloodSugarData(int investigationRegisterId) {
        try {
            Optional<FastingBloodSugar> fbsData = fastingBloodSugarDao.findByInvestigationRegisterId(investigationRegisterId);

            return fbsData.map(fastingBloodSugar -> new ResponseEntity<>(fastingBloodSugar, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            System.err.println("Error while getting fbs data");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
