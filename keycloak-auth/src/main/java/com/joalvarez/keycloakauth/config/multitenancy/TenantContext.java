package com.joalvarez.keycloakauth.config.multitenancy;

public class TenantContext {

	public static final String DEFAULT_SCHEMA = "public";
	private static final ThreadLocal<String> CURRENT_TENANT_SCHEMA = new InheritableThreadLocal<>();

	public static String getCurrentTenantSchema() {
		return CURRENT_TENANT_SCHEMA.get();
	}

	public static void setCurrentTenantSchema(String tenantSchema) {
		CURRENT_TENANT_SCHEMA.set(tenantSchema);
	}

	public static void clear() {
		CURRENT_TENANT_SCHEMA.remove();
	}
}
