package com.joalvarez.keycloakauth.service;

import com.joalvarez.keycloakauth.config.KeycloakSourceProperties;
import com.joalvarez.keycloakauth.constants.ErrorCode;
import com.joalvarez.keycloakauth.data.dto.UserRepresentationDTO;
import com.joalvarez.keycloakauth.data.mapper.KeycloakMapper;
import com.joalvarez.keycloakauth.exception.generals.GenericException;
import com.joalvarez.keycloakauth.service.interfaces.IKeycloakService;
import com.joalvarez.keycloakauth.shared.HasLogger;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakService implements IKeycloakService<UserRepresentationDTO, String>, HasLogger {

	private final KeycloakMapper mapper;
	private final KeycloakSourceProperties properties;

	public KeycloakService(KeycloakMapper mapper, KeycloakSourceProperties properties) {
		this.mapper = mapper;
		this.properties = properties;
	}

	@Override
	public List<UserRepresentationDTO> findAll() {
		return this.getUserResource().list().stream()
			.map(this.mapper::toDTO)
			.collect(Collectors.toList());
	}

	@Override
	public UserRepresentationDTO findById(String username) {
		return this.getUserResource().searchByUsername(username, true).stream()
			.map(this.mapper::toDTO)
			.toList().get(0);
	}

	@Override
	public UserRepresentationDTO update(UserRepresentationDTO dto) {

		UserRepresentation userRepresentation = this.mapper.fromDTO(dto);

		userRepresentation.setEmailVerified(true);
		userRepresentation.setEnabled(true);
		userRepresentation.setCredentials(Collections.singletonList(this.createPassword(dto.getPassword())));

		this.getUserResource().get(dto.getId()).update(userRepresentation);

		return dto;
	}


	@Override
	public void delete(String id) {
		this.getUserResource()
			.get(id)
			.remove();
	}

	@Override
	public UserRepresentationDTO create(UserRepresentationDTO dto) {
		RealmResource realmResource = this.getRealmResource();
		UsersResource userResource = realmResource.users();

		UserRepresentation userRepresentation = this.mapper.fromDTO(dto);

		userRepresentation.setEmailVerified(true);
		userRepresentation.setEnabled(true);

		Response response = userResource.create(userRepresentation);

		if (response.getStatus() != HttpStatus.CREATED.value()) {
			throw new GenericException(HttpStatus.NOT_FOUND, ErrorCode.CLIENT_CONNECTION_ERROR);
		}

		UserResource user = userResource.get(this.getIdUser(response.getLocation().getPath()));

		user.resetPassword(this.createPassword(dto.getPassword()));

		List<RoleRepresentation> roles = new ArrayList<>();

		roles.add(realmResource.roles().get(dto.isAdmin() ? "ADMIN" : "USER").toRepresentation());

		user.roles().realmLevel().add(roles);

		return dto;
		//return "User {".concat(dto.getUsername()).concat("} created successfully");
	}

	/**
	 * @param password creada por el usuario
	 */
	private CredentialRepresentation createPassword(String password) {
		CredentialRepresentation credentials = new CredentialRepresentation();

		credentials.setTemporary(false);
		credentials.setType(OAuth2Constants.PASSWORD);
		credentials.setValue(password);

		return credentials;
	}

	/**
	 *
	 * @param path ruta donde obtenemos el id de usuario creado
	 * @return id del usuario creado
	 */
	private String getIdUser(String path) {
		return path.substring(path.lastIndexOf("/") + 1);
	}

	private RealmResource getRealmResource() {
		return KeycloakBuilder.builder()
			.serverUrl(this.properties.getServer())
			.realm(this.properties.getRealmMaster())
			.clientId(this.properties.getAdminCli())
			.username(this.properties.getUserConsole())
			.password(this.properties.getPassConsole())
			.clientSecret(this.properties.getClientSecret())
			.resteasyClient(
				new ResteasyClientBuilderImpl()
					.connectionPoolSize(10)
					.build()
			)
			.build().realm(this.properties.getRealmName());
	}

	private UsersResource getUserResource() {
		return getRealmResource().users();
	}
}
