package com.joalvarez.keycloakauth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakSourceProperties {

	private String server;
	private String realmName;
	private String realmMaster;
	private String adminCli;
	private String userConsole;
	private String passConsole;
	private String clientSecret;
	private String clientId;
	private String urlLogin;
	private String port;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getRealmName() {
		return realmName;
	}

	public void setRealmName(String realmName) {
		this.realmName = realmName;
	}

	public String getRealmMaster() {
		return realmMaster;
	}

	public void setRealmMaster(String realmMaster) {
		this.realmMaster = realmMaster;
	}

	public String getAdminCli() {
		return adminCli;
	}

	public void setAdminCli(String adminCli) {
		this.adminCli = adminCli;
	}

	public String getUserConsole() {
		return userConsole;
	}

	public void setUserConsole(String userConsole) {
		this.userConsole = userConsole;
	}

	public String getPassConsole() {
		return passConsole;
	}

	public void setPassConsole(String passConsole) {
		this.passConsole = passConsole;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getUrlLogin() {
		return urlLogin;
	}

	public void setUrlLogin(String urlLogin) {
		this.urlLogin = urlLogin;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
