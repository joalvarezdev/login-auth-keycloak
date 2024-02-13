package com.joalvarez.keycloakauth.exception.generals;

import com.joalvarez.keycloakauth.constants.ErrorCode;
import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException {

	private HttpStatus code;
	private int errorCode;

	public GenericException(HttpStatus code, ErrorCode error) {
		this(code, error.code(), error.message());
	}

	public GenericException(HttpStatus code, int errorCode, String message) {
		super(message);

		this.code = code;
		this.errorCode = errorCode;
	}

	public HttpStatus getCode() {
		return this.code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public int getResponseCode() {
		return this.errorCode;
	}

	public void setResponseCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
