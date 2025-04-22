package com.us.cal.gov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MajorProjectEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MajorProjectEurekaServerApplication.class, args);
	}

}
