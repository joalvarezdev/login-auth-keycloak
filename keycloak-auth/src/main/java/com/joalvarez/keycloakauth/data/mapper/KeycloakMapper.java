package com.joalvarez.keycloakauth.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.keycloakauth.data.dto.UserRepresentationDTO;
import com.joalvarez.keycloakauth.data.mapper.generals.BaseMapper;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

@Component
public class KeycloakMapper extends BaseMapper<UserRepresentationDTO, UserRepresentation> {

	public KeycloakMapper(ObjectMapper objectMapper) {
		super(objectMapper);
	}
}
