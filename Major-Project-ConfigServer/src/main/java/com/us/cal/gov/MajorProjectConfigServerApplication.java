package com.us.cal.gov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MajorProjectConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MajorProjectConfigServerApplication.class, args);
	}

}
