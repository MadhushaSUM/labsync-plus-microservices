package com.labsyncplus.labsync_investigations_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LabsyncInvestigationsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabsyncInvestigationsMicroserviceApplication.class, args);
	}

}
