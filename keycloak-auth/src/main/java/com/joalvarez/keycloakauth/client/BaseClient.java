package com.joalvarez.keycloakauth.client;

import com.joalvarez.keycloakauth.constants.ErrorCode;
import com.joalvarez.keycloakauth.data.dto.generals.BaseDTO;
import com.joalvarez.keycloakauth.exception.generals.HttpErrorException;
import com.joalvarez.keycloakauth.shared.HasLogger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.function.Supplier;

public abstract class BaseClient implements HasLogger {

	protected final RestTemplate wiredRestTemplate;

	public BaseClient(RestTemplate wiredRestTemplate) {
		this.wiredRestTemplate = wiredRestTemplate;
	}

	protected <T> ResponseEntity<T> ask(Supplier<ResponseEntity<T>> action, ErrorCode clientError, ErrorCode serverError) {
		ResponseEntity<T> response;
		try {
			response = action.get();
			if (response == null) {
				throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
					String.format("Empty response returned with communicating with the client {{%s}}", this.getClientName()));
			}
		} catch (HttpClientErrorException e) {
			throw new HttpErrorException((HttpStatus) e.getStatusCode(), clientError, e.getMessage());
		} catch (HttpServerErrorException e) {
			throw new HttpErrorException(HttpStatus.INTERNAL_SERVER_ERROR, serverError, e.getMessage());
		}

		return response;
	}

	public <T extends Serializable> T exchange(String url, BaseDTO dto, HttpMethod method, Class<T> responseType) {
		ResponseEntity<T> response =
			this.ask(() -> this.wiredRestTemplate.exchange(url, method, dto == null ? null : new HttpEntity<>(dto), responseType),
				ErrorCode.CLIENT_BAD_REQUEST_ERROR, ErrorCode.CLIENT_CONNECTION_ERROR);
		return response.getBody();
	}

	public <T extends Serializable> T exchange(String url, BaseDTO dto, HttpMethod method, ParameterizedTypeReference<T> type) {
		ResponseEntity<T> response =
			this.ask(() -> this.wiredRestTemplate.exchange(url, method, dto == null ? null : new HttpEntity<>(dto), type),
				ErrorCode.CLIENT_BAD_REQUEST_ERROR, ErrorCode.CLIENT_CONNECTION_ERROR);
		return response.getBody();
	}

	protected abstract String getClientName();
	protected abstract String getBaseUrl();
}
