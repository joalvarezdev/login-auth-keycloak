package com.joalvarez.keycloakauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.stream.Stream;

@Configuration
public class FilterConfig implements WebMvcConfigurer {

	// TODO: adding your interceptors here

	private static final List<String> EXCLUDE_PATH = Stream.of(
		"/v3/api-docs/**",
		"/swagger-ui/**",
		"/swagger-ui.html",
		"/swagger-resources/**",
		"/webjars/**"
	).toList();

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO uncomment following code
/*
		registry.addInterceptor()
			.excludePathPatterns(EXCLUDE_PATH);
*/
	}
}