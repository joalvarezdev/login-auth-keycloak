package com.joalvarez.keycloakauth.exception.generals;

import com.joalvarez.keycloakauth.constants.ErrorCode;
import org.springframework.http.HttpStatus;

public class HttpErrorException extends GenericException {

	private String nested;

	public HttpErrorException(ErrorCode response, String nested) {
		this(HttpStatus.INTERNAL_SERVER_ERROR, response, nested);
	}

	public HttpErrorException(HttpStatus status, ErrorCode response, String nested) {
		super(status, response.code(), response.message());
		this.nested = nested;
	}

	public String getNested() {
		return nested;
	}

	public void setNested(String nested) {
		this.nested = nested;
	}
}
