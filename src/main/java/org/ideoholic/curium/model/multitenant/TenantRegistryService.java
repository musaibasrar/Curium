package org.ideoholic.curium.model.multitenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantRegistryService {

    private static final Pattern SAFE_ID_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]{1,64}$");
    private static final String ACTIVE_STATUS = "ACTIVE";

    private final JdbcTemplate jdbcTemplate;

    @Value("${tenant.registry.cache-ttl-seconds:300}")
    private long cacheTtlSeconds;

    private final ConcurrentMap<String, CachedTenantEntry> cache = new ConcurrentHashMap<>();

    public Optional<TenantRegistryEntry> findActiveTenant(String rawTenantId) {
        String tenantId = normalizeTenantId(rawTenantId);
        if (tenantId == null) {
            return Optional.empty();
        }

        CachedTenantEntry cachedTenantEntry = cache.get(tenantId);
        if (cachedTenantEntry != null && !cachedTenantEntry.isExpired(cacheTtlSeconds)) {
            return Optional.of(cachedTenantEntry.getEntry());
        }

        Optional<TenantRegistryEntry> dbEntry = queryTenant(tenantId)
                .filter(entry -> ACTIVE_STATUS.equalsIgnoreCase(entry.getStatus()));

        dbEntry.ifPresent(entry -> cache.put(tenantId, new CachedTenantEntry(entry, Instant.now())));
        return dbEntry;
    }

    public String normalizeTenantId(String rawTenantId) {
        if (!StringUtils.hasLength(rawTenantId)) {
            return null;
        }

        String normalized = rawTenantId.trim().toLowerCase();
        if (!SAFE_ID_PATTERN.matcher(normalized).matches()) {
            log.warn("Rejected invalid tenant id format: {}", rawTenantId);
            return null;
        }
        return normalized;
    }

    public void invalidateTenant(String rawTenantId) {
        String tenantId = normalizeTenantId(rawTenantId);
        if (tenantId != null) {
            cache.remove(tenantId);
        }
    }

    private Optional<TenantRegistryEntry> queryTenant(String tenantId) {
        String sql = "SELECT tenant_id, schema_name, status, display_name "
                + "FROM platform.tenants WHERE tenant_id = ?";

        return jdbcTemplate.query(sql, new TenantRegistryRowMapper(), tenantId)
                .stream()
                .findFirst();
    }

    private static class TenantRegistryRowMapper implements RowMapper<TenantRegistryEntry> {
        @Override
        public TenantRegistryEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
            return TenantRegistryEntry.builder()
                    .tenantId(rs.getString("tenant_id"))
                    .schemaName(rs.getString("schema_name"))
                    .status(rs.getString("status"))
                    .displayName(rs.getString("display_name"))
                    .build();
        }
    }

    private static class CachedTenantEntry {
        private final TenantRegistryEntry entry;
        private final Instant loadedAt;

        private CachedTenantEntry(TenantRegistryEntry entry, Instant loadedAt) {
            this.entry = entry;
            this.loadedAt = loadedAt;
        }

        private TenantRegistryEntry getEntry() {
            return entry;
        }

        private boolean isExpired(long ttlSeconds) {
            if (ttlSeconds <= 0) {
                return true;
            }
            return Duration.between(loadedAt, Instant.now()).getSeconds() > ttlSeconds;
        }
    }
}