package com.labsyncplus.labsync_investigations_microservice.feign;

import com.labsyncplus.labsync_investigations_microservice.model.dto.AddInvestigationDataDto;
import com.labsyncplus.labsync_investigations_microservice.model.entity.InvestigationData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "LABSYNC-INVESTIGATION-DATA-MICROSERVICE")
public interface InvestigationDataInterface {

    @PostMapping("investigationData/add")
    public ResponseEntity<String> addInvestigationData(@RequestBody AddInvestigationDataDto dto);

    @GetMapping("investigationData/get")
    public ResponseEntity<InvestigationData> getInvestigationData(@RequestParam long investigationRegisterId);

    @PutMapping("investigationData/update")
    public ResponseEntity<String> updateInvestigationData(@RequestBody AddInvestigationDataDto dto, @RequestParam long investigationDataId);
}
