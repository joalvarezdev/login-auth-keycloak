package com.joalvarez.keycloakauth.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

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

				auth.requestMatchers(HttpMethod.POST, "/keycloak/users/login").permitAll();
				auth.requestMatchers(HttpMethod.POST, "/keycloak/users").hasRole("ADMIN");
				auth.requestMatchers(HttpMethod.GET, "/keycloak/users").hasRole("ADMIN");
				auth.requestMatchers(HttpMethod.PUT, "/keycloak/users").hasRole("ADMIN");
				auth.requestMatchers(HttpMethod.DELETE, "/keycloak/users/{id}").hasRole("ADMIN");

				this.securityProperties.getEndpoints().forEach(endpoint -> {
					auth.requestMatchers(endpoint.getPath()).hasAnyRole(endpoint.getAuthorities().toArray(String[]::new));
				});

				auth.requestMatchers(this.securityProperties.getExcludedPaths()).permitAll();

				auth.anyRequest().authenticated();
			})
			.oauth2ResourceServer(oauth -> {
				oauth.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(this.jwtAuthoritiesConverter()));
			})
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.build();
	}

	private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthoritiesConverter() {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(this.keycloakConverter);

		return converter;
	}

}
