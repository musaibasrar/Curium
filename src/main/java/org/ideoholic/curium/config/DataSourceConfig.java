package org.ideoholic.curium.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {
	@Value("${url:jdbc:mysql://localhost:3306/}")
	private String jdbcUrl;

	@Value("${username:root}")
	private String username;

	@Value("${password:root}")
	private String password;

	@Value("${driver-class-name:org.mariadb.jdbc.Driver}")
	private String driverClassName;

	@Value("${hikari.maximum-pool-size:10}")
	private int maxPoolSize;
	
	@Value("${hikari.minimum-pool-size:5}")
	private int minPoolSize;

	@Value("${hikari.minimum-idle:5}")
	private int minIdle;

	@Value("${hikari.idle-timeout:300000}")
	private int idleTimeout; // 5 minutes in milliseconds

	@Value("${hikari.max-lifetime:600000}")
	private int maxLifetime; // 10 minutes in milliseconds

	@Value("${hikari.connection-timeout:30000}")
	private int connectionTimeout; // 30 seconds in milliseconds

}
