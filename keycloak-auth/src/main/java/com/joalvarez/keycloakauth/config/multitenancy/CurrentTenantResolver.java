package com.joalvarez.keycloakauth.config.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class CurrentTenantResolver implements CurrentTenantIdentifierResolver {

	public static final String DEFAULT_SCHEMA = "public";

	@Override
	public String resolveCurrentTenantIdentifier() {
		return TenantContext.getCurrentTenantSchema() != null
			? TenantContext.getCurrentTenantSchema()
			: DEFAULT_SCHEMA;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
