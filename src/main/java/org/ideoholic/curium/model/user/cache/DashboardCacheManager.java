package org.ideoholic.curium.model.user.cache;

import lombok.extern.slf4j.Slf4j;
import org.ideoholic.curium.model.user.dto.SuperDashboardFilterRequestDto;
import org.ideoholic.curium.model.user.dto.SuperDashboardResponseDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Lightweight cache manager for Super Dashboard aggregate queries.
 * Uses in-memory ConcurrentHashMap with TTL-based expiration.
 * Default TTL: 3 minutes (180,000 ms)
 * Configurable via TTL_MILLIS constant.
 */
@Slf4j
public class DashboardCacheManager {

    /**
     * Cache TTL in milliseconds. Default: 3 minutes (180,000 ms).
     * Adjust for different cache windows: 2 min = 120,000, 5 min = 300,000
     */
    private static final long TTL_MILLIS = 180_000L; // 3 minutes

    /**
     * Thread-safe in-memory cache storage.
     * Key: generated cache key from filter parameters
     * Value: DashboardCacheEntry containing response and timestamp
     */
    private static final Map<String, DashboardCacheEntry> cache = new ConcurrentHashMap<>();

    /**
     * Retrieves cached dashboard response if it exists and hasn't expired.
     *
     * @param filter The filter request DTO
     * @param sessionAcademicYear The session academic year
     * @return Cached SuperDashboardResponseDto if found and valid, null otherwise
     */
    public static SuperDashboardResponseDto getCached(SuperDashboardFilterRequestDto filter, String sessionAcademicYear) {
        String cacheKey = generateCacheKey(filter, sessionAcademicYear);
        DashboardCacheEntry entry = cache.get(cacheKey);

        if (entry == null) {
            return null;
        }

        if (entry.isExpired(TTL_MILLIS)) {
            cache.remove(cacheKey);
            log.debug("Cache expired for key: {}", cacheKey);
            return null;
        }

        log.debug("Cache hit for key: {}", cacheKey);
        return entry.getData();
    }

    /**
     * Stores a dashboard response in cache.
     *
     * @param filter The filter request DTO
     * @param sessionAcademicYear The session academic year
     * @param response The SuperDashboardResponseDto to cache
     */
    public static void cache(SuperDashboardFilterRequestDto filter, String sessionAcademicYear, SuperDashboardResponseDto response) {
        String cacheKey = generateCacheKey(filter, sessionAcademicYear);
        cache.put(cacheKey, new DashboardCacheEntry(response));
        log.debug("Cached dashboard data for key: {} (TTL: {} ms)", cacheKey, TTL_MILLIS);
    }

    /**
     * Clears all cached dashboard data.
     * Use when master data changes (e.g., fees, marks, attendance updates).
     */
    public static void clearCache() {
        cache.clear();
        log.info("Dashboard cache cleared");
    }

    /**
     * Clears cache for a specific branch to reflect recent changes.
     *
     * @param branchId The branch ID to clear cache for
     */
    public static void clearCacheForBranch(int branchId) {
        String branchIdText = String.valueOf(branchId);
        cache.entrySet().removeIf(entry -> isBranchKeyAffected(entry.getKey(), branchIdText));
        log.debug("Dashboard cache cleared for branch: {}", branchId);
    }

    /**
     * Generates a cache key from filter parameters.
     * Cache key includes all relevant filter parameters to ensure different
     * filter combinations are cached separately.
     *
     * @param filter The filter request DTO
     * @param sessionAcademicYear The session academic year
     * @return A unique cache key
     */
    private static String generateCacheKey(SuperDashboardFilterRequestDto filter, String sessionAcademicYear) {
        StringBuilder keyBuilder = new StringBuilder("dashboard:");

        // Academic year (use session if not specified)
        String academicYear = filter.getAcademicYear() != null ? filter.getAcademicYear().trim() : "";
        if (academicYear.isEmpty()) {
            academicYear = sessionAcademicYear != null ? sessionAcademicYear.trim() : "";
        }
        keyBuilder.append("year:").append(academicYear).append("|");

        // Branch IDs
        String branchIds = normalizeBranchIds(filter.getBranchIds());
        keyBuilder.append("branch:").append(branchIds).append("|");

        // Date range
        String fromDate = safeValue(filter.getFromDate());
        String toDate = safeValue(filter.getToDate());
        keyBuilder.append("from:").append(fromDate).append("|");
        keyBuilder.append("to:").append(toDate).append("|");

        // Class and section
        String selectedClass = safeValue(filter.getSelectedClass());
        String section = safeValue(filter.getSection());
        keyBuilder.append("class:").append(selectedClass.isEmpty() ? "all" : selectedClass).append("|");
        keyBuilder.append("section:").append(section.isEmpty() ? "all" : section).append("|");

        // Examination
        String examination = safeValue(filter.getExamination());
        keyBuilder.append("exam:").append(examination.isEmpty() ? "all" : examination).append("|");

        // Fee category
        String feeCategory = safeValue(filter.getFeeCategory());
        keyBuilder.append("feecat:").append(feeCategory.isEmpty() ? "all" : feeCategory).append("|");

        String sortBy = safeValue(filter.getSortBy());
        keyBuilder.append("sort:").append(sortBy.isEmpty() ? "highest_collection" : sortBy).append("|");

        return keyBuilder.toString();
    }

    private static String safeValue(String value) {
        return value == null ? "" : value.trim();
    }

    private static String normalizeBranchIds(String branchIdsCsv) {
        String raw = safeValue(branchIdsCsv);
        if (raw.isEmpty()) {
            return "all";
        }
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (String token : raw.split(",")) {
            String trimmed = token.trim();
            if (trimmed.isEmpty()) {
                continue;
            }
            try {
                ids.add(Integer.parseInt(trimmed));
            } catch (NumberFormatException ignored) {
                // Ignore non-numeric values to keep the key deterministic.
            }
        }
        if (ids.isEmpty()) {
            return "all";
        }
        Collections.sort(ids);
        StringBuilder normalized = new StringBuilder();
        for (int i = 0; i < ids.size(); i++) {
            if (i > 0) {
                normalized.append(',');
            }
            normalized.append(ids.get(i));
        }
        return normalized.toString();
    }

    private static boolean isBranchKeyAffected(String cacheKey, String branchIdText) {
        int start = cacheKey.indexOf("branch:");
        if (start < 0) {
            return true;
        }
        start += "branch:".length();
        int end = cacheKey.indexOf('|', start);
        String value = end >= 0 ? cacheKey.substring(start, end) : cacheKey.substring(start);
        if ("all".equals(value)) {
            return true;
        }
        for (String token : value.split(",")) {
            if (branchIdText.equals(token.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns current cache size (for monitoring/debugging).
     *
     * @return Number of entries in cache
     */
    public static int getCacheSize() {
        return cache.size();
    }

    /**
     * Returns cache statistics (for monitoring).
     *
     * @return String containing cache statistics
     */
    public static String getCacheStats() {
        return String.format("Cache size: %d entries, TTL: %d ms", cache.size(), TTL_MILLIS);
    }
}
