package org.ideoholic.controller.infrastructure;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import({ WebXmlConfiguration.class })
@ImportResource({ "classpath*:appContext.xml" })
// @PropertySource(value="classpath:META-INF/spring/jdbc.properties")
@EnableAutoConfiguration
public class ApplicationConfiguration {

}
