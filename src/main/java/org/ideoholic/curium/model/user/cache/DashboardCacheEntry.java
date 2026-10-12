package org.ideoholic.curium.model.user.cache;

import org.ideoholic.curium.model.user.dto.SuperDashboardResponseDto;

/**
 * Immutable cache entry holding dashboard data with creation timestamp.
 * Used internally by DashboardCacheManager.
 */
public class DashboardCacheEntry {
    private final SuperDashboardResponseDto data;
    private final long createdAt;

    public DashboardCacheEntry(SuperDashboardResponseDto data) {
        this.data = data;
        this.createdAt = System.currentTimeMillis();
    }

    public SuperDashboardResponseDto getData() {
        return data;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Checks if this cache entry has expired.
     *
     * @param ttlMillis Time-to-live in milliseconds
     * @return true if expired, false otherwise
     */
    public boolean isExpired(long ttlMillis) {
        return (System.currentTimeMillis() - createdAt) > ttlMillis;
    }
}
