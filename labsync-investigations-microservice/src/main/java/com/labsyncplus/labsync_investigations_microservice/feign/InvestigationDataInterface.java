package com.labsyncplus.labsync_investigations_microservice.feign;

import com.labsyncplus.labsync_investigations_microservice.model.dto.AddFBSDataDto;
import com.labsyncplus.labsync_investigations_microservice.model.investigations.FastingBloodSugar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "LABSYNC-INVESTIGATION-DATA-MICROSERVICE")
public interface InvestigationDataInterface {
    @PostMapping("investigationData/FBS")
    public ResponseEntity<String> addFBSData(@RequestBody AddFBSDataDto addFBSDataDto);

    @GetMapping("investigationData/FBS/{investigationRegisterId}")
    public ResponseEntity<FastingBloodSugar> getFBSData(@RequestParam int investigationRegisterId);
}
