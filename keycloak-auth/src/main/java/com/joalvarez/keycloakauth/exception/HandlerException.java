package com.joalvarez.keycloakauth.exception;

import com.joalvarez.keycloakauth.constants.ErrorCode;
import com.joalvarez.keycloakauth.data.dto.generals.HttpDTO;
import com.joalvarez.keycloakauth.data.dto.generals.ResponseDTO;
import com.joalvarez.keycloakauth.shared.HasLogger;
import com.joalvarez.keycloakauth.exception.generals.HttpErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerException implements HasLogger {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleValidationExceptions(
		MethodArgumentNotValidException e) {
		ResponseDTO dto = new ResponseDTO();
		dto.setDetails(
			e.getBindingResult().getAllErrors().stream()
				.filter(error -> error instanceof FieldError)
				.map(error -> (FieldError) error)
				.map(
					(error) ->
						String.format(
							"The field {%s} is invalid for the value {%s} with the following cause: %s",
							error.getField(),
							error.getRejectedValue(),
							error.getDefaultMessage()))
				.collect(Collectors.toList()));
		return ResponseEntity.badRequest().body(dto);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ResponseDTO> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		ResponseDTO error = new ResponseDTO(ErrorCode.ATTRIBUTE_VALIDATION);
		error.setMessage(
			String.format(
				"The parameter {{%s}} has an invalid value of {{%s}} for the desired type {{%s}}",
				ex.getName(),
				ex.getValue(),
				ex.getRequiredType() != null
					? ex.getRequiredType().getSimpleName()
					: "Unknown"));
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ResponseDTO> handleMissingRequestParam(MissingServletRequestParameterException e) {
		ResponseDTO error = new ResponseDTO(ErrorCode.ATTRIBUTE_VALIDATION);
		error.setMessage(e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(HttpErrorException.class)
	public ResponseEntity<HttpDTO> handle(HttpErrorException e) {
		this.warn(
			"Nested error occurred with the following cause: {} ",
			String.valueOf(e.getNested()),
			e);
		HttpDTO response = new HttpDTO();
		response.setCode(e.getResponseCode());
		response.setMessage(e.getMessage());
		response.setNested(e.getNested());

		return new ResponseEntity<>(response, e.getCode());
	}
}
