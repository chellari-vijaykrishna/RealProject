package com.vj.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "plan.module")
@EnableConfigurationProperties
@Data
public class AppConfig {
	private Map<String, String> messages;

}
