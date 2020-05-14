package org.ideoholic;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

@Configuration
@Profile("basicauth")
public class WebXmlConfiguration {

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