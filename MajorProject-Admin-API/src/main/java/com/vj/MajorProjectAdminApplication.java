package com.vj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MajorProjectAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MajorProjectAdminApplication.class, args);
	}

}
