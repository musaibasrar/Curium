package org.ideoholic.controller.infrastructure;

import javax.servlet.Filter;
import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

/**
 * This Configuration replaces what formerly was in web.xml.
 *
 * @see <a href=
 *      "http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-convert-an-existing-application-to-spring-boot">#howto-convert-an-existing-application-to-spring-boot</a>
 */
@Configuration
@Profile("basicauth")
public class WebXmlConfiguration {

	@Bean
	public Filter springSecurityFilterChain() {
		return new DelegatingFilterProxy();
	}

	@Bean
	public ServletRegistrationBean<Servlet> jersey() {
		Servlet jerseyServlet = new SpringServlet();
		ServletRegistrationBean<Servlet> jerseyServletRegistration = new ServletRegistrationBean<Servlet>();
		jerseyServletRegistration.setServlet(jerseyServlet);
		jerseyServletRegistration.addUrlMappings("/rest/v1/*");
		jerseyServletRegistration.setName("jersey-servlet");
		jerseyServletRegistration.setLoadOnStartup(1);
		jerseyServletRegistration.addInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
		jerseyServletRegistration.addInitParameter("com.sun.jersey.config.feature.DisableWADL", "true");
		jerseyServletRegistration.addInitParameter("com.sun.jersey.config.property.packages", "org.ideoholic");
		// debugging for development:
		// jerseyServletRegistration.addInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters",
		// LoggingFilter.class.getName());
		return jerseyServletRegistration;
	}
}