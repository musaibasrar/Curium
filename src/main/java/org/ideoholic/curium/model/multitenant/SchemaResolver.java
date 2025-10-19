package org.ideoholic.curium.model.multitenant;

import java.util.Map;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

@Component
public class SchemaResolver implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {

	@Override
	public String resolveCurrentTenantIdentifier() {
		return TenantContext.getCurrentTenant();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

	@Override
	public void customize(Map<String, Object> hibernateProperties) {
		hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
	}
}