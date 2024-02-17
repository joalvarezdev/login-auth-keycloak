package com.joalvarez.keycloakauth.config;

import com.joalvarez.keycloakauth.interceptor.TenantSchemaInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.stream.Stream;

@Configuration
public class FilterConfig implements WebMvcConfigurer {

	private final TenantSchemaInterceptor testInterceptor;

	public FilterConfig(TenantSchemaInterceptor testInterceptor) {
		this.testInterceptor = testInterceptor;
	}

	private static final List<String> EXCLUDE_PATH = Stream.of(
		"/v3/api-docs/**",
		"/swagger-ui/**",
		"/swagger-ui.html",
		"/swagger-resources/**",
		"/webjars/**",
		"/users/login"
	).toList();

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.testInterceptor)
			.excludePathPatterns(EXCLUDE_PATH);
	}
}