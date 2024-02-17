package com.joalvarez.keycloakauth.config.multitenancy;

import com.joalvarez.keycloakauth.ApiKeycloakAuthApplication;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MultiTenantSchemaHibernateConfiguration {

	private final JpaProperties jpaProperties;

	@Autowired
	public MultiTenantSchemaHibernateConfiguration(JpaProperties jpaProperties) {
		this.jpaProperties = jpaProperties;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
		DataSource dataSource,
		MultiTenantConnectionProvider multiTenantConnectionProvider,
		CurrentTenantIdentifierResolver tenantIdentifierResolver) {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(ApiKeycloakAuthApplication.class.getPackage().getName());
		em.setJpaVendorAdapter(this.jpaVendorAdapter());

		Map<String, Object> jpaPropertiesMap = new HashMap<>(jpaProperties.getProperties());
		jpaPropertiesMap.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
		jpaPropertiesMap.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantIdentifierResolver);
		em.setJpaPropertyMap(jpaPropertiesMap);

		return em;
	}
}
