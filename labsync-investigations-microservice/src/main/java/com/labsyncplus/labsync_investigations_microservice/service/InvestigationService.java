package com.labsyncplus.labsync_investigations_microservice.service;

import com.labsyncplus.labsync_investigations_microservice.dao.InvestigationDao;
import com.labsyncplus.labsync_investigations_microservice.model.entity.Investigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigationService {
    @Autowired
    InvestigationDao investigationDao;

    public ResponseEntity<List<Investigation>> getAllInvestigations() {
        try {
            return new ResponseEntity<>(investigationDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error retrieving investigations from DB");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Optional<Investigation>> getInvestigationById(int id) {
        try {
            return new ResponseEntity<>(investigationDao.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error retrieving investigation from DB");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
