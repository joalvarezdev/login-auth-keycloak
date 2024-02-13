package com.joalvarez.keycloakauth.constants;

public enum ErrorCode implements IResponse {

	ATTRIBUTE_VALIDATION(8000, "Some provided attribute has a problem"),
	CLIENT_CONNECTION_ERROR(8001, "The integration's client is unreachable or unavailable for the operation"),
	CLIENT_BAD_REQUEST_ERROR(8002, "The integration's client indicated an error while performing this operation"),
	NOT_IMPLEMENTED_ERROR(8003, "The resource you're trying to access or execute isn't implemented yet"),
	;

	private final int code;
	private final String message;

	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public int code() {
		return this.code;
	}

	@Override
	public String message() {
		return this.message;
	}
}