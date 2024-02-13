package com.joalvarez.keycloakauth.data.dto.generals;

import com.joalvarez.keycloakauth.constants.IResponse;

import java.util.List;

public class ResponseDTO implements BaseDTO {

	private int code;
	private String message;
	private List<String> details;

	public ResponseDTO() {}

	public ResponseDTO(IResponse response) {
		this.code = response.code();
		this.message = response.message();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
}