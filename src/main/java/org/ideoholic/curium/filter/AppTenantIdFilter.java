package org.ideoholic.curium.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.multitenant.TenantContext;
import org.ideoholic.curium.model.multitenant.TenantRegistryEntry;
import org.ideoholic.curium.model.multitenant.TenantRegistryService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppTenantIdFilter implements Filter {

	public static final String TENANT_HEADER = "X-curium-tenant-id";
	public static final String TENANT_REQUEST_PARAM = "tenantId";
	public static final String TENANT_SESSION_KEY = "tenantId";
	public static final String TENANT_SCHEMA_SESSION_KEY = "tenantSchema";
	private static final String USER_AUTH = "userAuth";
	private static final String USER_AUTH_YES = "yes";
	private static final String LOGIN_PATH_SUFFIX = "/UserProcess/authenticateUser";

	private final TenantRegistryService tenantRegistryService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.trace("Do filter called");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		try {
			String headerTenant = req.getHeader(TENANT_HEADER);
			String paramTenant = req.getParameter(TENANT_REQUEST_PARAM);
			String requestTenant = StringUtils.hasLength(headerTenant) ? headerTenant : paramTenant;
			String normalizedRequestTenant = tenantRegistryService.normalizeTenantId(requestTenant);
			String sessionTenant = session == null ? null : (String) session.getAttribute(TENANT_SESSION_KEY);
			boolean isAuthenticated = session != null
					&& USER_AUTH_YES.equalsIgnoreCase(String.valueOf(session.getAttribute(USER_AUTH)));
			boolean isLoginRequest = req.getRequestURI() != null && req.getRequestURI().endsWith(LOGIN_PATH_SUFFIX);

			if (isAuthenticated) {
				if (!StringUtils.hasLength(sessionTenant)) {
					resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Tenant is not bound to current session");
					return;
				}
				String normalizedSessionTenant = tenantRegistryService.normalizeTenantId(sessionTenant);
				if (!StringUtils.hasLength(normalizedSessionTenant)) {
					resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid tenant bound to current session");
					return;
				}

				if (StringUtils.hasLength(normalizedRequestTenant)
						&& !normalizedSessionTenant.equals(normalizedRequestTenant)) {
					resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Tenant override is not allowed for authenticated requests");
					return;
				}

				if (!bindTenantById(normalizedSessionTenant, req, session, resp)) {
					return;
				}
			} else {
				if (isLoginRequest && !StringUtils.hasLength(normalizedRequestTenant)) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing tenantId in login request");
					return;
				}

				String tenantToBind = StringUtils.hasLength(normalizedRequestTenant)
						? normalizedRequestTenant
						: (StringUtils.hasLength(sessionTenant)
								? tenantRegistryService.normalizeTenantId(sessionTenant)
								: null);

				if (StringUtils.hasLength(tenantToBind)) {
					if (!bindTenantById(tenantToBind, req, session, resp)) {
						return;
					}
				}
			}

			log.trace("Do filter done");
			chain.doFilter(request, response);
		} finally {
			TenantContext.clear();
		}
	}

	private boolean bindTenantById(String tenantId, HttpServletRequest req, HttpSession session,
			HttpServletResponse resp) throws IOException {
		TenantRegistryEntry registryEntry = tenantRegistryService.findActiveTenant(tenantId).orElse(null);
		if (registryEntry == null) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Unknown or inactive tenant");
			return false;
		}

		HttpSession targetSession = session == null ? req.getSession(true) : session;
		targetSession.setAttribute(TENANT_SESSION_KEY, registryEntry.getTenantId());
		targetSession.setAttribute(TENANT_SCHEMA_SESSION_KEY, registryEntry.getSchemaName());

		TenantContext.setCurrentTenant(registryEntry.getSchemaName());
		req.setAttribute(TENANT_SESSION_KEY, registryEntry.getTenantId());
		req.setAttribute(TENANT_SCHEMA_SESSION_KEY, registryEntry.getSchemaName());
		return true;
	}

}
