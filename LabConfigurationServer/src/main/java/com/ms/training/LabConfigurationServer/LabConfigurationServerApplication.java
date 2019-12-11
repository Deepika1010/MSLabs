package com.ms.training.LabConfigurationServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class LabConfigurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabConfigurationServerApplication.class, args);
	}

}
