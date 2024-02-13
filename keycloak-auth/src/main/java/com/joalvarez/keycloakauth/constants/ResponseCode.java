package com.joalvarez.keycloakauth.constants;

public enum ResponseCode implements IResponse {
	;

	private final int code;
	private final String message;

	public int code() {
		return this.code;
	}

	public String message() {
		return this.message;
	}

	ResponseCode (int code, String message) {
		this.code = code;
		this.message = message;
	}
}