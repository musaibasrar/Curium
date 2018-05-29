package com.model.multitenant;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class SchemaResolver implements CurrentTenantIdentifierResolver {
 
	@Override
	public String resolveCurrentTenantIdentifier() {
		return "school"; //TODO: Implement service to identify tenant like: userService.getCurrentlyAuthUser().getTenantId();
	}
 
	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}
}