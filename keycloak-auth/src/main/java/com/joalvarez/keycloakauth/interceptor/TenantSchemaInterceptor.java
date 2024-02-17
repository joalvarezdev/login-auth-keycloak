package com.joalvarez.keycloakauth.interceptor;

import com.joalvarez.keycloakauth.config.multitenancy.TenantContext;
import com.joalvarez.keycloakauth.constants.ErrorCode;
import com.joalvarez.keycloakauth.exception.generals.GenericException;
import com.joalvarez.keycloakauth.shared.HasLogger;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Component
public class TenantSchemaInterceptor implements HandlerInterceptor, HasLogger {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		var authorization = request.getHeader("Authorization");

		if (Objects.isNull(authorization) || authorization.isEmpty()) {
			throw new GenericException(
				HttpStatus.UNAUTHORIZED,
				ErrorCode.USER_NOT_FOUND
			);
		}

		var token = authorization.replace("Bearer ", "");

		if (token.isEmpty()) {
			throw new GenericException(
				HttpStatus.UNAUTHORIZED,
				ErrorCode.USER_NOT_FOUND
			);
		}
		JWTClaimsSet claims = JWTParser.parse(token).getJWTClaimsSet();

		TenantContext.setCurrentTenantSchema((String) claims.getClaim("family_name"));

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		TenantContext.clear();
	}
}