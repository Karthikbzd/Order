package com.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.order")
public class AppConfig {

	RestTemplate restTemplate = null;

	@Bean
	RestTemplate restTemplate() {
		restTemplate = new RestTemplate();
		return restTemplate;
	}

}
