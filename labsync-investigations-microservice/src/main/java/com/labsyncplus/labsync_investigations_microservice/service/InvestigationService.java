package com.labsyncplus.labsync_investigations_microservice.service;

import com.labsyncplus.labsync_investigations_microservice.dao.InvestigationDao;
import com.labsyncplus.labsync_investigations_microservice.dao.InvestigationFieldRangeDao;
import com.labsyncplus.labsync_investigations_microservice.model.dto.InvestigationNormalRangesDto;
import com.labsyncplus.labsync_investigations_microservice.model.entity.Investigation;
import com.labsyncplus.labsync_investigations_microservice.model.entity.InvestigationFieldRange;
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
    InvestigationFieldRangeDao investigationFieldRangeDao;

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

    public ResponseEntity<List<InvestigationNormalRangesDto>> getInvestigationNormalRange(long investigationId) {
        try {
            List<InvestigationFieldRange> ranges = investigationFieldRangeDao.findByInvestigationId(investigationId);

            List<InvestigationNormalRangesDto> normalRanges = new ArrayList<>();
            for (InvestigationFieldRange range: ranges) {
                InvestigationNormalRangesDto dto = new InvestigationNormalRangesDto();
                dto.setId(range.getId());
                dto.setFieldName(range.getFieldName());
                dto.setHigh(range.getHigh());
                dto.setLow(range.getLow());

                normalRanges.add(dto);
            }

            return new ResponseEntity<>(normalRanges, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error retrieving investigation normal ranges from DB");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
