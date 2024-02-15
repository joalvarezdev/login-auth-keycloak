package com.joalvarez.keycloakauth.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "api.security")
public class SecurityConfigProperties {

	private final String[] excludedPaths = {
		"/v3/api-docs/**",
		"/swagger-ui/**",
		"/swagger-ui.html",
		"/swagger-resources/**",
		"/webjars/**"
	};
	private List<EndPointSecurityDTO> endpoints;
	private String mapping;
	private List<String> allowedOrigins;
	private List<String> allowedMethods;
	private List<String> allowedHead;

	public List<EndPointSecurityDTO> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(List<EndPointSecurityDTO> endpoints) {
		this.endpoints = endpoints;
	}

	public String[] getExcludedPaths() {
		return excludedPaths;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public List<String> getAllowedOrigins() {
		return allowedOrigins;
	}

	public void setAllowedOrigins(List<String> allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}

	public List<String> getAllowedMethods() {
		return allowedMethods;
	}

	public void setAllowedMethods(List<String> allowedMethods) {
		this.allowedMethods = allowedMethods;
	}

	public List<String> getAllowedHead() {
		return allowedHead;
	}

	public void setAllowedHead(List<String> allowedHead) {
		this.allowedHead = allowedHead;
	}
}
