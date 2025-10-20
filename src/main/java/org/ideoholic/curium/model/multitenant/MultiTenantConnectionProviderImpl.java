package org.ideoholic.curium.model.multitenant;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.ideoholic.curium.config.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MultiTenantConnectionProviderImpl
		implements MultiTenantConnectionProvider, ServiceRegistryAwareService, HibernatePropertiesCustomizer {
	private static final long serialVersionUID = 4368575201221677384L;

	@Autowired
	private DataSourceConfig dataSourceConfig;
	
    // Cache one DataSource (Hikari pool) per tenant to avoid creating many pools
    private final ConcurrentMap<String, HikariDataSource> tenantDataSources = new ConcurrentHashMap<>();
    
    // Shared base HikariConfig used as a template for tenant-specific pools
    private HikariConfig baseHikariConfig;
    
    // Track last access instant per tenant pool (updated on any access)
    private final ConcurrentMap<String, Instant> tenantLastAccess = new ConcurrentHashMap<>();
    
    // Eviction scheduler
    private ScheduledExecutorService evictionExecutor;
    
    // Idle threshold (minutes) after which an unused tenant pool will be closed
    private static final long IDLE_MINUTES = 30L;

    // How often the eviction thread runs (minutes)
    private static final long EVICTION_RUN_INTERVAL_MINUTES = 5L;

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
        String tenant = TenantContext.getCurrentTenant();
        log.trace("getAnyConnection for tenant: {}", tenant);
        HikariDataSource ds = getOrCreateDataSource(tenant);
        updateLastAccess(tenant);
        return ds.getConnection();
	}

	@Override
	public Connection getConnection(String schema) throws SQLException {
		Connection connection = null;

		String tenant = schema == null ? TenantContext.getCurrentTenant() : schema;
		log.trace("Creating a new connection for:{}", tenant);
		HikariDataSource ds = getOrCreateDataSource(tenant);
		updateLastAccess(tenant);
		connection = ds.getConnection();
		try {
			if (connection != null && !connection.isClosed()) {
				// set schema only if needed and supported by DB/driver
				connection.setSchema(tenant);
			} else {
				log.error("Connection is already closed...!!!");
			}
		} catch (SQLException e) {
			// Some drivers/databases may not support setSchema; log and continue.
			log.error("Could not alter JDBC connection to specified schema [ {} ]", schema, e);
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		if (connection != null) {
			try {
				// Always close in application code (returns connection to pool)
				connection.close();
			} catch (SQLException e) {
				log.error("Could not alter JDBC connection to specified schema", e);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void releaseConnection(String schema, Connection connection) throws SQLException {
		// Just close; do not create/close pools here. Closing returns the connection to its pool; schema reset not required here.
		releaseAnyConnection(connection);
	}

	@Override
	public void customize(Map<String, Object> hibernateProperties) {
		hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
	}
	
    /**
     * Initialize the base HikariConfig template using DataSourceConfig (or local
     * defaults). This keeps tenant creation clean: we only override what varies
     * per tenant (jdbcUrl, username, password, poolName).
     */
    @PostConstruct
    public void init() {
        baseHikariConfig = new HikariConfig();
        try {
            if (dataSourceConfig == null) {
                log.warn("DataSourceConfig is null; using local defaults for baseHikariConfig");
                baseHikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
                baseHikariConfig.setJdbcUrl("jdbc:mariadb://localhost:3306/"); // tenant appended at runtime
                baseHikariConfig.setUsername("root");
                baseHikariConfig.setPassword("root");
                
                baseHikariConfig.setConnectionTimeout(30000); // 30s Time before a connection is considered a timeout
                baseHikariConfig.setIdleTimeout(600000); // 10m Time before idle connection is candidate for closure
                baseHikariConfig.setMaxLifetime(1800000); // 30m Max lifetime of a connection in the pool
                baseHikariConfig.setMaximumPoolSize(20); // Max number of connections allowed in the pool
                baseHikariConfig.setMinimumIdle(10); // Minimum number of idle connections in the pool
            } else {
            	log.info("DataSourceConfig is initialized: using the config values for baseHikariConfig");
                baseHikariConfig.setDriverClassName(dataSourceConfig.getDriverClassName());
                // jdbcUrl is used as template; tenant appended when creating each pool
                baseHikariConfig.setJdbcUrl(dataSourceConfig.getJdbcUrl());
                baseHikariConfig.setUsername(dataSourceConfig.getUsername());
                baseHikariConfig.setPassword(dataSourceConfig.getPassword());
                baseHikariConfig.setConnectionTimeout(dataSourceConfig.getConnectionTimeout());
                baseHikariConfig.setIdleTimeout(dataSourceConfig.getIdleTimeout());
                baseHikariConfig.setMaxLifetime(dataSourceConfig.getMaxLifetime());
                baseHikariConfig.setMaximumPoolSize(dataSourceConfig.getMaxPoolSize());
                baseHikariConfig.setMinimumIdle(dataSourceConfig.getMinIdle());
            }
            
            // Important Hikari settings : Should the auto-commit be false?
            baseHikariConfig.setAutoCommit(false);
            // Useful diagnostics to find leaks — adjust or remove for production.
            // Enable leak detection to help find programming errors where connections are not closed.
            // Set to a low value during testing (e.g. 2000ms). Raise for production or remove after fixing leaks.
            baseHikariConfig.setLeakDetectionThreshold(2000); // ms; set to 0 to disable
            // Test query or rely on JDBC isValid; MariaDB's driver supports isValid usually.
            baseHikariConfig.setConnectionTestQuery("SELECT 1");

            // Do not set poolName here; set per-tenant when building pool.
            log.info("Base HikariConfig initialized");
            
            // Start eviction thread
            evictionExecutor = Executors.newSingleThreadScheduledExecutor(runnable -> {
                Thread t = new Thread(runnable, "tenant-pool-eviction-thread");
                t.setDaemon(true);
                return t;
            });
            evictionExecutor.scheduleAtFixedRate(this::evictIdlePools,
                    EVICTION_RUN_INTERVAL_MINUTES, EVICTION_RUN_INTERVAL_MINUTES, TimeUnit.MINUTES);

            log.info("Eviction thread started (idle minutes = {})", IDLE_MINUTES);
        } catch (Exception e) {
            log.error("Failed to initialize base HikariConfig", e);
            throw new RuntimeException(e);
        }
    }
	
    @PreDestroy
    public void shutdown() {
        log.info("Shutting down eviction executor and closing tenant Hikari pools...");
        if (evictionExecutor != null) {
            try {
                evictionExecutor.shutdownNow();
            } catch (Exception e) {
                log.warn("Error shutting down eviction executor: {}", e.getMessage(), e);
            }
        }

        tenantDataSources.forEach((tenant, ds) -> {
            try {
                if (ds != null && !ds.isClosed()) {
                    log.info("Closing pool for tenant: {}", tenant);
                    ds.close();
                }
            } catch (Exception e) {
                log.warn("Error closing pool for tenant {}: {}", tenant, e.getMessage(), e);
            }
        });
        tenantDataSources.clear();
        tenantLastAccess.clear();
    }

    private HikariDataSource getOrCreateDataSource(String tenant) {
        tenant = tenant == null ? TenantContext.getCurrentTenant() : tenant;
        // computeIfAbsent ensures only one DataSource per tenant is created
        HikariDataSource ds = tenantDataSources.computeIfAbsent(tenant, this::createTenantDataSource);
        // update last access whenever the pool is used/obtained
        updateLastAccess(tenant);
        return ds;
    }

    /**
     * Create a HikariDataSource for the given tenant using a copy of the base
     * HikariConfig with tenant-specific jdbcUrl, username, and password.
     */
    private HikariDataSource createTenantDataSource(String tenant) {
        try {
            HikariConfig cfg = new HikariConfig(getBaseHikariConfig());
            // Copy base settings
            cfg.setPoolName("curium-tenant-" + tenant);
            cfg.setDriverClassName(baseHikariConfig.getDriverClassName());
            cfg.setConnectionTimeout(baseHikariConfig.getConnectionTimeout());
            cfg.setIdleTimeout(baseHikariConfig.getIdleTimeout());
            cfg.setMaxLifetime(baseHikariConfig.getMaxLifetime());
            cfg.setMaximumPoolSize(baseHikariConfig.getMaximumPoolSize());
            cfg.setMinimumIdle(baseHikariConfig.getMinimumIdle());
            cfg.setLeakDetectionThreshold(baseHikariConfig.getLeakDetectionThreshold());
            cfg.setConnectionTestQuery(baseHikariConfig.getConnectionTestQuery());
            cfg.setAutoCommit(baseHikariConfig.isAutoCommit());
            // Set tenant-specific JDBC URL and credentials
            String jdbcUrl;
            String username;
            String password;
            if (dataSourceConfig == null) {
                jdbcUrl = "jdbc:mariadb://localhost:3306/" + tenant;
				log.trace("Static Driver URL:{}", jdbcUrl);
                username = "root";
                password = "root";
            } else {
                jdbcUrl = dataSourceConfig.getJdbcUrl() + tenant;
				log.trace("Config loaded Driver URL:{}", jdbcUrl);
                username = dataSourceConfig.getUsername();
                password = dataSourceConfig.getPassword();
            }
            cfg.setJdbcUrl(jdbcUrl);
            cfg.setUsername(username);
            cfg.setPassword(password);

            log.info("Creating Hikari pool '{}' for tenant {} -> {}", cfg.getPoolName(), tenant, jdbcUrl);
            return new HikariDataSource(cfg);
        } catch (Exception e) {
            log.error("Failed to create HikariDataSource for tenant " + tenant, e);
            throw new RuntimeException(e);
        }
    }

	private Properties getBaseHikariConfig() {
		if (baseHikariConfig == null) {
			init();
		}
		return baseHikariConfig.getDataSourceProperties();
	}

	/**
     * Scans tenant pools and closes those that have been idle for more than IDLE_MINUTES.
     */
    private void evictIdlePools() {
        try {
            Instant now = Instant.now();
            Duration idleThreshold = Duration.ofMinutes(IDLE_MINUTES);

            for (Map.Entry<String, Instant> entry : tenantLastAccess.entrySet()) {
                String tenant = entry.getKey();
                Instant last = entry.getValue();
                if (last == null) continue;
                Duration idle = Duration.between(last, now);
                if (idle.compareTo(idleThreshold) > 0) {
                    HikariDataSource ds = tenantDataSources.get(tenant);
                    if (ds == null) {
                        // no pool present; remove lastAccess entry
                        tenantLastAccess.remove(tenant);
                        continue;
                    }
                    // attempt to remove the pool only if it's the same ds we saw
                    boolean removed = tenantDataSources.remove(tenant, ds);
                    if (removed) {
                        tenantLastAccess.remove(tenant);
                        try {
                            if (!ds.isClosed()) {
                                log.info("Evicting idle tenant pool '{}' (idle {} minutes) for tenant {}", ds.getPoolName(),
                                        idle.toMinutes(), tenant);
                                ds.close();
                            }
                        } catch (Exception e) {
                            log.warn("Error closing evicted datasource for tenant {}: {}", tenant, e.getMessage(), e);
                        }
                    } else {
                        // another thread may have replaced it; skip
                        log.debug("Skipped eviction for tenant {} because pool changed concurrently", tenant);
                    }
                }
            }
        } catch (Exception e) {
            log.warn("Exception while evicting idle tenant pools: {}", e.getMessage(), e);
        }
    }

    private void updateLastAccess(String tenant) {
        if (tenant == null) return;
        tenantLastAccess.put(tenant, Instant.now());
    }

    // Extra: legacy c3p0 factory kept for reference if needed
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
}
