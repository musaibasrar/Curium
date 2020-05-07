package com.model.multitenant;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
 

import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
 
public class MultiTenantProvider implements MultiTenantConnectionProvider, ServiceRegistryAwareService  {
 
	private static final long serialVersionUID = 4368575201221677384L;
	
	private C3P0ConnectionProvider connectionProvider = null;
 
	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}
 
	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		Map lSettings = serviceRegistry.getService(ConfigurationService.class).getSettings();
		
		connectionProvider = new C3P0ConnectionProvider();
		connectionProvider.injectServices(serviceRegistry);
		connectionProvider.configure(lSettings);
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
	public Connection getAnyConnection() throws SQLException {
		final Connection connection = connectionProvider.getConnection();
		return connection;
	}
 
	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		final Connection connection = getAnyConnection();
		try {
			connection.createStatement().execute("USE " + tenantIdentifier + "");
		}
		catch (Exception e) {
			System.out.println("Exception is "+e);
			try {
				throw new Exception("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return connection;
	}
 
	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		try {
			connection.createStatement().execute("USE school");
		}
		catch (SQLException e) {
			try {
				throw new Exception("Could not alter JDBC connection to specified schema [public]", e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		connectionProvider.closeConnection(connection);
	}
 
	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		releaseAnyConnection(connection);
	}
}
