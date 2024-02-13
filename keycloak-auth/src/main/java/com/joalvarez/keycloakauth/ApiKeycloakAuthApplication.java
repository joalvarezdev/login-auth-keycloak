package com.joalvarez.keycloakauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication()
public class ApiKeycloakAuthApplication {

	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(ApiKeycloakAuthApplication.class, args);
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(this.objectMapper);
		this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		return converter;
	}

	@Bean
	public RestTemplate restTemplate(@Autowired MappingJackson2HttpMessageConverter converter) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(List.of(converter));
		return restTemplate;
	}

	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

}