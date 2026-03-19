CREATE SCHEMA IF NOT EXISTS platform;

CREATE TABLE IF NOT EXISTS platform.tenants (
    tenant_id VARCHAR(64) NOT NULL,
    schema_name VARCHAR(64) NOT NULL,
    status VARCHAR(16) NOT NULL DEFAULT 'ACTIVE',
    display_name VARCHAR(128) NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (tenant_id),
    UNIQUE KEY uk_platform_tenants_schema_name (schema_name)
);

INSERT INTO platform.tenants (tenant_id, schema_name, status, display_name)
SELECT 'school', 'school', 'ACTIVE', 'Default School Tenant'
WHERE NOT EXISTS (
    SELECT 1 FROM platform.tenants WHERE tenant_id = 'school'
);