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
		"/swagger-resources/**", "/webjars/**"
	};
	private List<EndPointSecurityDTO> endpoints;
	private List<String> origins;

	public List<EndPointSecurityDTO> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(List<EndPointSecurityDTO> endpoints) {
		this.endpoints = endpoints;
	}

	public String[] getExcludedPaths() {
		return excludedPaths;
	}

	public List<String> getOrigins() {
		return origins;
	}

	public void setOrigins(List<String> origins) {
		this.origins = origins;
	}
}
