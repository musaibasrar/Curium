package org.ideoholic.curium.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.multitenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppTenantIdFilter implements Filter {

	public static final String TENANT_HEADER = "X-curium-tenant-id";
	public static final String TENANT_REQUEST_PARAM = "tenantId";

	@Autowired
	private HttpSession httpSession;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.trace("Do filter called");
		HttpServletRequest req = (HttpServletRequest) request;

		// First - check for the header value
		String tenantValue = req.getHeader(TENANT_HEADER);
		
		if (StringUtils.hasLength(tenantValue)) {
			log.trace("Acquired tenant value from header:{}", tenantValue);
			TenantContext.setCurrentTenant(tenantValue);
			httpSession.setAttribute("tenantId", tenantValue);
		} else { // if the header does not exist
			// Check the request parameter
			tenantValue = req.getParameter(TENANT_REQUEST_PARAM);
			if (StringUtils.hasLength(tenantValue)) {
				log.trace("Acquired tenant value from requestparam:{}", tenantValue);
				TenantContext.setCurrentTenant(tenantValue);
				httpSession.setAttribute("tenantId", tenantValue);
			} else {// if the request parameter does not exist
				if(httpSession != null) {// only if session exists
					// Extract the value from the session
					tenantValue = (String) httpSession.getAttribute("tenantId");
					if (StringUtils.hasLength(tenantValue)) {
						log.trace("Acquired tenant value from session:{}", tenantValue);
						TenantContext.setCurrentTenant(tenantValue);
						
					}
				}
			}
		}
		log.trace("Do filter done");
		chain.doFilter(request, response);
	}

}
