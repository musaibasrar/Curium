package org.ideoholic.curium.model.multitenant;

import lombok.extern.slf4j.Slf4j;
import org.ideoholic.curium.util.DataUtil;

@Slf4j
public class TenantContext {
	private static final String DEFAULT_TENANT = "school";
	private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

	public static void setCurrentTenant(String tenant) {
		log.trace("Setting tenant to " + tenant);
		currentTenant.set(tenant);
	}

	public static String getCurrentTenant() {
		String tenant = DataUtil.requireNonNullElse(currentTenant.get(), DEFAULT_TENANT);
		log.trace("Getting current tenant :" + tenant);
		return tenant;
	}

	public static void clear() {
		log.trace("Clearing current tenant");
		currentTenant.set(null);
	}
}