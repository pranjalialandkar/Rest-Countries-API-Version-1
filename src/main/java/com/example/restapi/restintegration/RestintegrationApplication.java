package com.example.restapi.restintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
public class RestintegrationApplication {
	
	 @Bean
	 public RestTemplate restTemplate(){
	        return new RestTemplate();
	 }

	public static void main(String[] args) {
		SpringApplication.run(RestintegrationApplication.class, args);
	}

}
