package com.joalvarez.keycloakauth.config.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@SuppressWarnings("unchecked")
public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@Override
	public Collection<GrantedAuthority> convert(Jwt source) {
		if (Objects.isNull(source.getClaims())) {
			return Collections.emptyList();
		}

		final Map<String, List<String>> realmAccess = (Map<String, List<String>>) source.getClaims().get("realm_access");

		return realmAccess.get("roles").stream()
			.map(rol -> new SimpleGrantedAuthority("ROLE_".concat(rol)))
			.collect(Collectors.toList());
	}
}
