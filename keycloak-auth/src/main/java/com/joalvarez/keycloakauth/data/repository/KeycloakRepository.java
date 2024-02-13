package com.joalvarez.keycloakauth.data.repository;

import com.joalvarez.keycloakauth.data.repository.generals.GenericRepository;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

@Component
public interface KeycloakRepository extends GenericRepository<UserRepresentation, String> {
}
