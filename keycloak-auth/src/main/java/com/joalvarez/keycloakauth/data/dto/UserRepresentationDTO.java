package com.joalvarez.keycloakauth.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joalvarez.keycloakauth.data.dto.generals.BaseDTO;
import org.keycloak.representations.idm.CredentialRepresentation;

import java.util.List;


public class UserRepresentationDTO implements BaseDTO {

	private String id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private boolean admin;
	private boolean emailVerified;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private List<CredentialRepresentation> credentials;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public List<CredentialRepresentation> getCredentials() {
		return credentials;
	}

	public void setCredentials(List<CredentialRepresentation> credentials) {
		this.credentials = credentials;
	}
}
