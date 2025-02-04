package org.ideoholic.curium.model.multitenant;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.ideoholic.curium.config.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MultiTenantConnectionProviderImpl
		implements MultiTenantConnectionProvider, ServiceRegistryAwareService, HibernatePropertiesCustomizer {
	private static final long serialVersionUID = 4368575201221677384L;

	@Autowired
	private DataSourceConfig dataSourceConfig;

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	@Override
	public boolean isUnwrappableAs(Class clazz) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> clazz) {
		return null;
	}

	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		// Inject services if needed
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		log.trace("Creating a new connection");
		return createDataSource(null).getConnection();
	}

	@Override
	public Connection getConnection(String schema) throws SQLException {
		Connection connection = null;
		try {
			log.trace("Creating a new connection for:{}", schema);
			connection = createDataSource(schema).getConnection();
			if (connection != null && !connection.isClosed()) {
				connection.setSchema(schema);
			} else {
				log.error("Connection is already closed...!!!");
			}
			// connection.setSchema(schema); Needs to be done in case of C3P0
		} catch (SQLException e) {
			log.error("Could not alter JDBC connection to specified schema [" + schema + "]", e);
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			log.error("Could not alter JDBC connection to specified schema", e);
			e.printStackTrace();
		}
	}

	@Override
	public void releaseConnection(String schema, Connection connection) throws SQLException {
		connection.setSchema(schema);
		releaseAnyConnection(connection);
	}

	@Override
	public void customize(Map<String, Object> hibernateProperties) {
		hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
	}

	// Method to create a DataSource, customize it as per your requirements
	private DataSource createDataSource(String tenant) {
		return getHikariDataSource(tenant);
	}

	private HikariDataSource getHikariDataSource(String tenant) {
		if (dataSourceConfig == null) {
			return getHikariDataSourceFromLocalData();
		}
		HikariDataSource dataSource = new HikariDataSource();
		try {
			tenant = tenant == null ? TenantContext.getCurrentTenant() : tenant;
			String localJdbcUrl = dataSourceConfig.getJdbcUrl() + tenant;
			log.trace("Driver URL:{}", localJdbcUrl);
			// Set JDBC properties
			dataSource.setDriverClassName(dataSourceConfig.getDriverClassName());
			dataSource.setJdbcUrl(localJdbcUrl);
			dataSource.addDataSourceProperty("user", dataSourceConfig.getUsername());
			dataSource.addDataSourceProperty("password", dataSourceConfig.getPassword());

			dataSource.setConnectionTimeout(dataSourceConfig.getConnectionTimeout());
			dataSource.setIdleTimeout(dataSourceConfig.getIdleTimeout());
			dataSource.setMaxLifetime(dataSourceConfig.getMaxLifetime());
			dataSource.setMaximumPoolSize(dataSourceConfig.getMaxPoolSize());
			dataSource.setMinimumIdle(dataSourceConfig.getMinIdle());
			dataSource.setAutoCommit(false);
		} catch (Exception e) {
			log.error("Hikari Driver loading failed", e.getMessage());
			e.printStackTrace();
		}
		return dataSource;
	}

	private HikariDataSource getHikariDataSourceFromLocalData() {
		HikariDataSource dataSource = new HikariDataSource();
		try {
			String localJdbcUrl = "jdbc:mariadb://localhost:3306/" + TenantContext.getCurrentTenant();
			log.trace("Static Driver URL:{}", localJdbcUrl);
			// Set JDBC properties
			dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
			dataSource.setJdbcUrl(localJdbcUrl);
			dataSource.addDataSourceProperty("user", "root");
			dataSource.addDataSourceProperty("password", "root");

			dataSource.setConnectionTimeout(30000); // Time before a connection is considered a timeout
			dataSource.setIdleTimeout(600000); // Time before idle connection is candidate for closure
			dataSource.setMaxLifetime(1800000); // Max lifetime of a connection in the pool
			dataSource.setMaximumPoolSize(20); // Max number of connections allowed in the pool
			dataSource.setMinimumIdle(10); // Minimum number of idle connections in the pool
			dataSource.setAutoCommit(false);
		} catch (Exception e) {
			log.error("Static Hikari Driver loading failed", e.getMessage());
			e.printStackTrace();
		}
		return dataSource;
	}

	private ComboPooledDataSource getComboPooledDataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			String localJdbcUrl = dataSourceConfig.getJdbcUrl() + TenantContext.getCurrentTenant();
			log.trace("Driver class name:{}", dataSourceConfig.getDriverClassName());
			log.trace("Driver URL:{}", localJdbcUrl);
			// Set JDBC properties
			dataSource.setJdbcUrl(localJdbcUrl);
			dataSource.setUser(dataSourceConfig.getUsername());
			dataSource.setPassword(dataSourceConfig.getPassword());
			dataSource.setDriverClass(dataSourceConfig.getDriverClassName());

			// Set pool properties
			dataSource.setMaxPoolSize(dataSourceConfig.getMaxPoolSize());
			dataSource.setMinPoolSize(dataSourceConfig.getMinPoolSize());
			dataSource.setInitialPoolSize(5); // Initial pool size
			dataSource.setMaxIdleTime(300); // Maximum idle time in seconds
			dataSource.setAcquireIncrement(5); // Number of connections to acquire at a time
			dataSource.setTestConnectionOnCheckin(true);
			dataSource.setTestConnectionOnCheckout(true);
			dataSource.setPreferredTestQuery("SELECT 1");

			// Set additional timeouts
			dataSource.setMaxIdleTimeExcessConnections(dataSourceConfig.getIdleTimeout());
			dataSource.setMaxConnectionAge(dataSourceConfig.getMaxLifetime());
			dataSource.setCheckoutTimeout(dataSourceConfig.getConnectionTimeout());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataSource;
	}

	@PostConstruct
	public void init() {
		if (dataSourceConfig == null) {
			log.error("DataSourceConfig is null!");
		} else {
			log.info("DataSourceConfig is initialized");
		}
	}

}
