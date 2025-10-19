package org.ideoholic.curium;

import org.ideoholic.curium.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

// Using a root package also allows the @ComponentScan annotation to be used without needing to specify a basePackage attribute
@ComponentScan
@SpringBootApplication
@EnableConfigurationProperties(DataSourceConfig.class)
@EntityScan(basePackages = "org.ideoholic.curium")
public class CuriumApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
       return application.sources(CuriumApplication.class);
    }

  public static void main(String[] args) {
    SpringApplication.run(CuriumApplication.class, args);
  }

}