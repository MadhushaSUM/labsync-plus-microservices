package com.labsyncplus.labsync_investigations_microservice.service;

import com.labsyncplus.labsync_investigations_microservice.dao.InvestigationDao;
import com.labsyncplus.labsync_investigations_microservice.dao.NormalRangeRuleDao;
import com.labsyncplus.labsync_investigations_microservice.model.entity.Investigation;
import com.labsyncplus.labsync_investigations_microservice.model.entity.NormalRangeRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvestigationService {
    @Autowired
    InvestigationDao investigationDao;
    @Autowired
    NormalRangeRuleDao normalRangeRuleDao;

    public ResponseEntity<List<Investigation>> getAllInvestigations() {
        try {
            return new ResponseEntity<>(investigationDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error retrieving investigations from DB");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Optional<Investigation>> getInvestigationById(long id) {
        try {
            return new ResponseEntity<>(investigationDao.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error retrieving investigation from DB");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<NormalRangeRule>> getNormalRangesByInvestigationId(long investigationId) {
        try {
            List<NormalRangeRule> normalRanges = normalRangeRuleDao.findByInvestigationId(investigationId);

            return new ResponseEntity<>(normalRanges, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error retrieving investigation normal ranges from DB");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> setNormalRangesByInvestigationId(NormalRangeRule normalRangeRule) {
        try {
            normalRangeRuleDao.save(normalRangeRule);
            return new ResponseEntity<>("Normal range rule saved", HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error saving investigation normal ranges");
            e.printStackTrace();
            return new ResponseEntity<>("Error saving investigation normal ranges", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
