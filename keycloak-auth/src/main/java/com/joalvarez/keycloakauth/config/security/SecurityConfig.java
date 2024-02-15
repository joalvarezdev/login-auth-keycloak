package com.joalvarez.keycloakauth.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final SecurityConfigProperties securityProperties;
	private final KeycloakRealmRoleConverter keycloakConverter;

	public SecurityConfig(KeycloakRealmRoleConverter keycloakConverter, SecurityConfigProperties securityProperties) {
		this.keycloakConverter = keycloakConverter;
		this.securityProperties = securityProperties;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> {

				auth.requestMatchers(HttpMethod.POST, "/users/login").permitAll();
				auth.requestMatchers("/users").hasRole("ADMIN");
				auth.requestMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("ADMIN");

				this.securityProperties.getEndpoints().forEach(
					endpoint -> auth.requestMatchers(endpoint.getPath()).hasAnyRole(endpoint.getAuthorities().toArray(String[]::new))
				);

				auth.requestMatchers(this.securityProperties.getExcludedPaths()).permitAll();
			})
			.oauth2ResourceServer(
				oauth -> oauth.jwt(
					jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(this.jwtAuthoritiesConverter()))
			)
			.cors(Customizer.withDefaults())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.build();
	}

	@Bean
	public WebMvcConfigurer corsConfiguration() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping(securityProperties.getMapping())
					.allowedOrigins(securityProperties.getAllowedOrigins().toArray(String[]::new))
					.allowedMethods(securityProperties.getAllowedMethods().toArray(String[]::new))
					.allowedHeaders(securityProperties.getAllowedHead().toArray(String[]::new));
			}
		};
	}

	private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthoritiesConverter() {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(this.keycloakConverter);

		return converter;
	}

}
