package org.ideoholic.curium.model.user.service;

import lombok.extern.slf4j.Slf4j;
import org.ideoholic.curium.model.user.cache.DashboardCacheManager;
import org.ideoholic.curium.model.user.dao.SuperDashboardDao;
import org.ideoholic.curium.model.user.dto.SuperDashboardFilterRequestDto;
import org.ideoholic.curium.model.user.dto.SuperDashboardResponseDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SuperDashboardService {

    private static final DateTimeFormatter MONTH_LABEL = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);

    public SuperDashboardResponseDto buildDashboardData(SuperDashboardFilterRequestDto filter, String sessionAcademicYear) {
        SuperDashboardResponseDto result = SuperDashboardResponseDto.builder().build();

        try {
            // Check cache first - avoid expensive queries if data is fresh
            SuperDashboardResponseDto cachedResult = DashboardCacheManager.getCached(filter, sessionAcademicYear);
            if (cachedResult != null) {
                log.debug("Returning cached dashboard data");
                return cachedResult;
            }

            SuperDashboardDao dao = new SuperDashboardDao();

            String academicYear = emptyIfNull(filter.getAcademicYear());
            if (academicYear.isEmpty()) {
                academicYear = emptyIfNull(sessionAcademicYear);
            }

            LocalDate toDate = parseDateOrDefault(filter.getToDate(), LocalDate.now());
            LocalDate fromDate = parseDateOrDefault(filter.getFromDate(), toDate.withDayOfMonth(1));
            if (fromDate.isAfter(toDate)) {
                LocalDate swap = fromDate;
                fromDate = toDate;
                toDate = swap;
            }

            List<Object[]> branchRows = dao.listBranches();
            log.debug("SuperDashboard branches from DAO: {}", branchRows == null ? 0 : branchRows.size());
            Map<Integer, String> branchNames = new LinkedHashMap<Integer, String>();
            for (Object[] row : branchRows) {
                Integer branchId = toInt(row[0]);
                if (branchId != null) {
                    branchNames.put(branchId, toText(row[1], "Branch " + branchId));
                }
            }
            log.debug("SuperDashboard normalized branch map size: {}", branchNames.size());

            List<Integer> selectedBranchIds = parseBranchIds(filter.getBranchIds());
            log.debug("SuperDashboard selected branchIds from request: {}", selectedBranchIds);
            if (selectedBranchIds.isEmpty()) {
                selectedBranchIds = new ArrayList<Integer>(branchNames.keySet());
            }
            List<Integer> filteredBranchIds = new ArrayList<Integer>();
            for (Integer branchId : selectedBranchIds) {
                if (branchNames.containsKey(branchId)) {
                    filteredBranchIds.add(branchId);
                }
            }
            selectedBranchIds = filteredBranchIds;
            if (selectedBranchIds.isEmpty()) {
                selectedBranchIds = new ArrayList<Integer>(branchNames.keySet());
            }
            log.debug("SuperDashboard effective branchIds after validation: {}", selectedBranchIds);

            String classFilter = emptyIfNull(filter.getSelectedClass());
            String sectionFilter = emptyIfNull(filter.getSection());
            String feeCategory = emptyIfNull(filter.getFeeCategory());
            Integer feeCategoryId = (!feeCategory.isEmpty() && isInteger(feeCategory)) ? Integer.valueOf(feeCategory) : null;

            List<Object[]> studentBranchRows = dao.fetchStudentCountsByBranch(selectedBranchIds, academicYear, classFilter, sectionFilter);
            List<Object[]> teacherBranchRows = dao.fetchTeacherCountsByBranch(selectedBranchIds);
            List<Object[]> feesBranchRows = dao.fetchFeeTotalsByBranch(selectedBranchIds, academicYear, classFilter, sectionFilter, feeCategoryId);

            YearMonth currentMonth = YearMonth.from(toDate);
            YearMonth previousMonth = currentMonth.minusMonths(1);

            List<Object[]> currentCollectionRows = dao.fetchMonthlyCollectionByBranch(
                    selectedBranchIds,
                    Date.valueOf(currentMonth.atDay(1)),
                    Date.valueOf(currentMonth.atEndOfMonth())
            );

            List<Object[]> previousCollectionRows = dao.fetchMonthlyCollectionByBranch(
                    selectedBranchIds,
                    Date.valueOf(previousMonth.atDay(1)),
                    Date.valueOf(previousMonth.atEndOfMonth())
            );

            List<Object[]> attendanceRows = dao.fetchAttendanceByBranch(
                    selectedBranchIds,
                    Date.valueOf(fromDate),
                    Date.valueOf(toDate),
                    academicYear
            );

            String examination = emptyIfNull(filter.getExamination());

            Integer examId = (!examination.isEmpty() && isInteger(examination)) ? Integer.valueOf(examination) : null;
            String examName = (!examination.isEmpty() && !isInteger(examination)) ? examination : null;

            List<Object[]> resultRows = dao.fetchResultByBranch(selectedBranchIds, academicYear, examId);
            Object examCountObj = dao.countExamsConducted(selectedBranchIds, academicYear, Date.valueOf(toDate), examName);

            List<Object[]> studentDimensions = dao.fetchStudentDimensions(selectedBranchIds, academicYear, classFilter, sectionFilter);

            YearMonth trendStartMonth = currentMonth.minusMonths(11);
            List<Object[]> monthlyReceiptRows = dao.fetchMonthlyReceiptRows(
                    selectedBranchIds,
                    Date.valueOf(trendStartMonth.atDay(1)),
                    Date.valueOf(currentMonth.atEndOfMonth())
            );

            List<Object[]> feeCategoryRows = dao.fetchFeeCategoryAnalysis(selectedBranchIds, academicYear);
            List<Object[]> examPerformanceRows = dao.fetchExamPerformance(selectedBranchIds, academicYear);
            List<Object[]> examOptionRows = dao.fetchExamOptions(selectedBranchIds);
            List<Object[]> feeCategoryOptionRows = dao.fetchFeeCategoryOptions(selectedBranchIds);

            Map<Integer, Long> studentsByBranch = toLongMap(studentBranchRows);
            Map<Integer, Long> teachersByBranch = toLongMap(teacherBranchRows);
            Map<Integer, List<Long>> feesByBranch = toTripleLongMap(feesBranchRows);
            Map<Integer, Long> currentCollectionByBranch = toLongMap(currentCollectionRows);
            Map<Integer, Long> previousCollectionByBranch = toLongMap(previousCollectionRows);
            Map<Integer, Double> resultsByBranch = toDoubleMap(resultRows);

            Map<Integer, long[]> attendanceByBranch = new LinkedHashMap<Integer, long[]>();
            for (Object[] row : attendanceRows) {
                Integer branchId = toInt(row[0]);
                if (branchId == null) {
                    continue;
                }
                String status = toText(row[1], "").toLowerCase(Locale.ENGLISH);
                long count = toLong(row[2]);
                long[] bucket = attendanceByBranch.get(branchId);
                if (bucket == null) {
                    bucket = new long[]{0L, 0L};
                    attendanceByBranch.put(branchId, bucket);
                }
                bucket[1] += count;
                if (status.startsWith("p")) {
                    bucket[0] += count;
                }
            }

            Map<String, Long> classWise = new LinkedHashMap<String, Long>();
            Map<String, Long> genderWise = new LinkedHashMap<String, Long>();
            Map<String, Long> categoryWise = new LinkedHashMap<String, Long>();
            for (Object[] row : studentDimensions) {
                String classStudying = toText(row[1], "");
                String[] split = classStudying.split("--");
                String className = split.length > 0 ? split[0].trim() : "Unknown";
                if (className.isEmpty()) {
                    className = "Unknown";
                }
                classWise.put(className, classWise.getOrDefault(className, 0L) + 1L);

                String gender = toText(row[2], "Unspecified").trim();
                if (gender.isEmpty()) {
                    gender = "Unspecified";
                }
                genderWise.put(gender, genderWise.getOrDefault(gender, 0L) + 1L);

                String category = toText(row[3], "Unspecified").trim();
                if (category.isEmpty()) {
                    category = "Unspecified";
                }
                categoryWise.put(category, categoryWise.getOrDefault(category, 0L) + 1L);
            }

            List<Map<String, Object>> branchCards = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> rankingRows = new ArrayList<Map<String, Object>>();

            long totalStudents = 0L;
            long totalStaff = 0L;
            long totalFees = 0L;
            long totalPaid = 0L;
            long totalDue = 0L;
            long totalCurrentMonth = 0L;
            long totalPreviousMonth = 0L;
            long attendancePresent = 0L;
            long attendanceOverall = 0L;

            double resultsSum = 0.0;
            int resultsCount = 0;

            for (Integer branchId : selectedBranchIds) {
                long students = studentsByBranch.getOrDefault(branchId, 0L);
                long staff = teachersByBranch.getOrDefault(branchId, 0L);
                List<Long> feeTuple = feesByBranch.getOrDefault(branchId, Arrays.asList(0L, 0L, 0L));
                long fees = feeTuple.get(0);
                long paid = feeTuple.get(1);
                long due = feeTuple.get(2);
                long currentMonthCollection = currentCollectionByBranch.getOrDefault(branchId, 0L);
                long previousMonthCollection = previousCollectionByBranch.getOrDefault(branchId, 0L);
                long[] attendance = attendanceByBranch.getOrDefault(branchId, new long[]{0L, 0L});
                double attendancePct = attendance[1] == 0 ? 0.0 : (attendance[0] * 100.0) / attendance[1];
                double resultPct = resultsByBranch.getOrDefault(branchId, 0.0);
                double collectionPct = fees == 0 ? 0.0 : (paid * 100.0) / fees;

                Map<String, Object> branchCard = new LinkedHashMap<String, Object>();
                branchCard.put("branchId", branchId);
                branchCard.put("branchName", branchNames.getOrDefault(branchId, "Branch " + branchId));
                branchCard.put("students", students);
                branchCard.put("staff", staff);
                branchCard.put("feesCollected", paid);
                branchCard.put("pendingFees", due);
                branchCard.put("attendancePct", round2(attendancePct));
                branchCard.put("resultPct", round2(resultPct));
                branchCard.put("collectionPct", round2(collectionPct));
                branchCard.put("currentMonthCollection", currentMonthCollection);
                branchCard.put("previousMonthCollection", previousMonthCollection);
                branchCard.put("lastUpdated", LocalDate.now().toString());
                branchCards.add(branchCard);

                Map<String, Object> rankingRow = new LinkedHashMap<String, Object>(branchCard);
                rankingRows.add(rankingRow);

                totalStudents += students;
                totalStaff += staff;
                totalFees += fees;
                totalPaid += paid;
                totalDue += due;
                totalCurrentMonth += currentMonthCollection;
                totalPreviousMonth += previousMonthCollection;
                attendancePresent += attendance[0];
                attendanceOverall += attendance[1];
                if (resultPct > 0) {
                    resultsSum += resultPct;
                    resultsCount++;
                }
            }

            int sortMode = parseSortMode(filter.getSortBy());
            sortBranchCards(branchCards, sortMode);

            List<Map<String, Object>> rankingSorted = new ArrayList<Map<String, Object>>(rankingRows);
            Collections.sort(rankingSorted, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> a, Map<String, Object> b) {
                    return Long.compare(toLong(b.get("feesCollected")), toLong(a.get("feesCollected")));
                }
            });
            for (int i = 0; i < rankingSorted.size(); i++) {
                rankingSorted.get(i).put("rank", i + 1);
            }

            double overallAttendancePct = attendanceOverall == 0 ? 0.0 : (attendancePresent * 100.0) / attendanceOverall;
            double averageResultPct = resultsCount == 0 ? 0.0 : (resultsSum / resultsCount);
            long examsConducted = toLong(examCountObj);
            Map<String, Object> summary = new LinkedHashMap<String, Object>();
            summary.put("totalBranches", selectedBranchIds.size());
            summary.put("totalStudents", totalStudents);
            summary.put("totalStaff", totalStaff);
            summary.put("totalFeesCollected", totalPaid);
            summary.put("currentMonthCollection", totalCurrentMonth);
            summary.put("pendingFees", totalDue);
            summary.put("totalExamsConducted", examsConducted);
            summary.put("overallAttendancePct", round2(overallAttendancePct));
            summary.put("averageResultPct", round2(averageResultPct));

            Map<String, Object> comparison = new LinkedHashMap<String, Object>();
            comparison.put("collectionCurrentMonth", totalCurrentMonth);
            comparison.put("collectionPreviousMonth", totalPreviousMonth);
            comparison.put("collectionTrendPct", trendPercentage(totalCurrentMonth, totalPreviousMonth));

            List<String> trendMonths = new ArrayList<String>();
            YearMonth cursor = trendStartMonth;
            while (!cursor.isAfter(currentMonth)) {
                trendMonths.add(cursor.format(MONTH_LABEL));
                cursor = cursor.plusMonths(1);
            }

            Map<String, Map<String, Long>> monthlyByBranchName = new LinkedHashMap<String, Map<String, Long>>();
            Map<String, Long> consolidatedMonthly = new LinkedHashMap<String, Long>();
            for (String month : trendMonths) {
                consolidatedMonthly.put(month, 0L);
            }

            for (Integer branchId : selectedBranchIds) {
                String branchName = branchNames.getOrDefault(branchId, "Branch " + branchId);
                Map<String, Long> bucket = new LinkedHashMap<String, Long>();
                for (String month : trendMonths) {
                    bucket.put(month, 0L);
                }
                monthlyByBranchName.put(branchName, bucket);
            }

            for (Object[] row : monthlyReceiptRows) {
                Integer branchId = toInt(row[0]);
                if (branchId == null || !selectedBranchIds.contains(branchId)) {
                    continue;
                }
                LocalDate receiptDate = toLocalDateValue(row[1]);
                if (receiptDate == null) {
                    continue;
                }
                YearMonth ym = YearMonth.from(receiptDate);
                String month = ym.format(MONTH_LABEL);
                if (!consolidatedMonthly.containsKey(month)) {
                    continue;
                }
                long amount = toLong(row[2]);
                consolidatedMonthly.put(month, consolidatedMonthly.get(month) + amount);

                String branchName = branchNames.getOrDefault(branchId, "Branch " + branchId);
                Map<String, Long> branchMonthMap = monthlyByBranchName.get(branchName);
                if (branchMonthMap != null) {
                    branchMonthMap.put(month, branchMonthMap.get(month) + amount);
                }
            }

            List<Map<String, Object>> monthlyTrendSeries = new ArrayList<Map<String, Object>>();
            for (Map.Entry<String, Map<String, Long>> entry : monthlyByBranchName.entrySet()) {
                Map<String, Object> series = new LinkedHashMap<String, Object>();
                series.put("name", entry.getKey());
                series.put("data", new ArrayList<Long>(entry.getValue().values()));
                monthlyTrendSeries.add(series);
            }

            List<Map<String, Object>> feeStructureSeries = new ArrayList<Map<String, Object>>();
            for (Object[] row : feeCategoryRows) {
                Map<String, Object> item = new LinkedHashMap<String, Object>();
                item.put("category", toText(row[0], "Others"));
                item.put("total", toLong(row[1]));
                item.put("paid", toLong(row[2]));
                feeStructureSeries.add(item);
            }

            List<Map<String, Object>> examPerformanceSeries = new ArrayList<Map<String, Object>>();
            for (Object[] row : examPerformanceRows) {
                Map<String, Object> item = new LinkedHashMap<String, Object>();
                item.put("exam", toText(row[0], "Exam"));
                item.put("average", round2(toDouble(row[1])));
                examPerformanceSeries.add(item);
            }

            List<Map<String, Object>> branchSeries = new ArrayList<Map<String, Object>>();
            for (Integer id : selectedBranchIds) {
                Map<String, Object> item = new LinkedHashMap<String, Object>();
                String branchName = branchNames.getOrDefault(id, "Branch " + id);
                long[] attendanceMetrics = attendanceByBranch.getOrDefault(id, new long[]{0L, 0L});
                double attendancePctValue = attendanceMetrics[1] == 0 ? 0.0 : (attendanceMetrics[0] * 100.0 / attendanceMetrics[1]);
                item.put("branch", branchName);
                item.put("students", studentsByBranch.getOrDefault(id, 0L));
                item.put("collection", feesByBranch.getOrDefault(id, Arrays.asList(0L, 0L, 0L)).get(1));
                item.put("pending", feesByBranch.getOrDefault(id, Arrays.asList(0L, 0L, 0L)).get(2));
                item.put("attendance", round2(attendancePctValue));
                item.put("result", round2(resultsByBranch.getOrDefault(id, 0.0)));
                branchSeries.add(item);
            }

            Map<String, Object> charts = new LinkedHashMap<String, Object>();
            charts.put("studentStrengthComparison", branchSeries);
            charts.put("feeCollectionComparison", branchSeries);
            charts.put("attendanceAnalysis", branchSeries);
            charts.put("resultAnalysis", branchSeries);
            charts.put("monthlyCollectionMonths", trendMonths);
            charts.put("monthlyCollectionConsolidated", new ArrayList<Long>(consolidatedMonthly.values()));
            charts.put("monthlyCollectionSeries", monthlyTrendSeries);
            charts.put("classWiseAnalysis", toTopItems(classWise, 20));
            charts.put("genderDistribution", toPieItems(genderWise));
            charts.put("categoryDistribution", toPieItems(categoryWise));
            charts.put("feeStructureAnalysis", feeStructureSeries);
            charts.put("examinationPerformance", examPerformanceSeries);

            Map<String, Object> filterOptions = new LinkedHashMap<String, Object>();
            List<Map<String, Object>> branchOptions = new ArrayList<Map<String, Object>>();
            for (Map.Entry<Integer, String> entry : branchNames.entrySet()) {
                Map<String, Object> item = new LinkedHashMap<String, Object>();
                item.put("id", entry.getKey());
                item.put("name", entry.getValue());
                branchOptions.add(item);
            }
            filterOptions.put("branches", branchOptions);
            log.debug("SuperDashboard filter option branches size: {}", branchOptions.size());

            Set<String> classOptions = new TreeSet<String>();
            for (Object[] row : studentDimensions) {
                String classStudying = toText(row[1], "");
                if (!classStudying.isEmpty()) {
                    classOptions.add(classStudying.split("--")[0]);
                }
            }
            filterOptions.put("classes", new ArrayList<String>(classOptions));

            Set<String> sectionOptions = new TreeSet<String>();
            for (Object[] row : studentDimensions) {
                String classStudying = toText(row[1], "");
                String[] split = classStudying.split("--");
                if (split.length > 1 && !split[1].trim().isEmpty()) {
                    sectionOptions.add(split[1].trim());
                }
            }
            filterOptions.put("sections", new ArrayList<String>(sectionOptions));

            List<Map<String, Object>> examOptions = new ArrayList<Map<String, Object>>();
            for (Object[] row : examOptionRows) {
                Map<String, Object> examOption = new LinkedHashMap<String, Object>();
                examOption.put("id", toInt(row[0]));
                examOption.put("name", toText(row[1], "Exam"));
                examOptions.add(examOption);
            }
            filterOptions.put("examinations", examOptions);

            List<Map<String, Object>> feeCategoryOptions = new ArrayList<Map<String, Object>>();
            for (Object[] row : feeCategoryOptionRows) {
                Map<String, Object> feeOption = new LinkedHashMap<String, Object>();
                feeOption.put("id", toInt(row[0]));
                feeOption.put("name", toText(row[1], "Category"));
                feeCategoryOptions.add(feeOption);
            }
            filterOptions.put("feeCategories", feeCategoryOptions);

            result.setSummary(summary);
            result.setComparison(comparison);
            result.setBranches(branchCards);
            result.setRanking(rankingSorted);
            result.setCharts(charts);
            result.setFilterOptions(filterOptions);
            result.setGeneratedAt(LocalDate.now().toString());
            result.setSuccess(true);

            log.debug("SuperDashboard response ready - branches={}, ranking={}, chartSeries={}",
                    branchCards.size(), rankingSorted.size(), branchSeries.size());

            // Cache the successful result for 3 minutes (configurable in DashboardCacheManager)
            DashboardCacheManager.cache(filter, sessionAcademicYear, result);
        } catch (Exception ex) {
            log.error("Error while building super dashboard data", ex);
            result.setSuccess(false);
            result.setMessage("Unable to load dashboard data");
        }

        return result;
    }

    private List<Map<String, Object>> toTopItems(Map<String, Long> source, int limit) {
        return source.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(limit)
                .map(entry -> {
                    Map<String, Object> item = new LinkedHashMap<String, Object>();
                    item.put("name", entry.getKey());
                    item.put("value", entry.getValue());
                    return item;
                }).collect(Collectors.toList());
    }

    private List<Map<String, Object>> toPieItems(Map<String, Long> source) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Map.Entry<String, Long> entry : source.entrySet()) {
            Map<String, Object> item = new LinkedHashMap<String, Object>();
            item.put("name", entry.getKey());
            item.put("value", entry.getValue());
            list.add(item);
        }
        return list;
    }

    private long toLong(Object value) {
        if (value == null) {
            return 0L;
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).longValue();
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return Long.parseLong(String.valueOf(value));
    }

    private double toDouble(Object value) {
        if (value == null) {
            return 0.0;
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return Double.parseDouble(String.valueOf(value));
    }

    private Integer toInt(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return Integer.valueOf(String.valueOf(value));
    }

    private String toText(Object value, String fallback) {
        if (value == null) {
            return fallback;
        }
        String text = String.valueOf(value);
        return text.trim().isEmpty() ? fallback : text;
    }

    private Map<Integer, Long> toLongMap(List<Object[]> rows) {
        Map<Integer, Long> map = new LinkedHashMap<Integer, Long>();
        for (Object[] row : rows) {
            Integer key = toInt(row[0]);
            if (key != null) {
                map.put(key, toLong(row[1]));
            }
        }
        return map;
    }

    private Map<Integer, Double> toDoubleMap(List<Object[]> rows) {
        Map<Integer, Double> map = new LinkedHashMap<Integer, Double>();
        for (Object[] row : rows) {
            Integer key = toInt(row[0]);
            if (key != null) {
                map.put(key, toDouble(row[1]));
            }
        }
        return map;
    }

    private Map<Integer, List<Long>> toTripleLongMap(List<Object[]> rows) {
        Map<Integer, List<Long>> map = new LinkedHashMap<Integer, List<Long>>();
        for (Object[] row : rows) {
            Integer key = toInt(row[0]);
            if (key != null) {
                map.put(key, Arrays.asList(toLong(row[1]), toLong(row[2]), toLong(row[3])));
            }
        }
        return map;
    }

    private List<Integer> parseBranchIds(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            return new LinkedList<Integer>();
        }
        List<Integer> ids = new ArrayList<Integer>();
        for (String token : csv.split(",")) {
            String trimmed = token.trim();
            if (!trimmed.isEmpty() && isInteger(trimmed)) {
                ids.add(Integer.parseInt(trimmed));
            }
        }
        return ids;
    }

    private String emptyIfNull(String value) {
        return value == null ? "" : value.trim();
    }

    private LocalDate parseDateOrDefault(String dateString, LocalDate fallback) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return fallback;
        }
        try {
            return LocalDate.parse(dateString.trim());
        } catch (Exception ex) {
            return fallback;
        }
    }

    private LocalDate toLocalDateValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof LocalDate) {
            return (LocalDate) value;
        }
        if (value instanceof java.sql.Date) {
            return ((java.sql.Date) value).toLocalDate();
        }
        if (value instanceof java.sql.Timestamp) {
            return ((java.sql.Timestamp) value).toLocalDateTime().toLocalDate();
        }
        if (value instanceof java.time.LocalDateTime) {
            return ((java.time.LocalDateTime) value).toLocalDate();
        }
        if (value instanceof java.time.Instant) {
            return ((java.time.Instant) value).atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        }
        if (value instanceof java.util.Date) {
            return java.time.Instant.ofEpochMilli(((java.util.Date) value).getTime())
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
        }
        log.debug("Unsupported receipt date type in monthly trend: {}", value.getClass().getName());
        return null;
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private double trendPercentage(long current, long previous) {
        if (previous == 0L) {
            return current > 0L ? 100.0 : 0.0;
        }
        return round2(((current - previous) * 100.0) / previous);
    }

    private double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private int parseSortMode(String sortBy) {
        String mode = emptyIfNull(sortBy).toLowerCase(Locale.ENGLISH);
        if ("lowest_collection".equals(mode)) {
            return 2;
        }
        if ("highest_pending".equals(mode)) {
            return 3;
        }
        if ("student_strength".equals(mode)) {
            return 4;
        }
        if ("performance".equals(mode)) {
            return 5;
        }
        return 1;
    }

    private void sortBranchCards(List<Map<String, Object>> branchCards, int mode) {
        Comparator<Map<String, Object>> comparator;
        if (mode == 2) {
            comparator = Comparator.comparingLong(o -> toLong(o.get("feesCollected")));
        } else if (mode == 3) {
            comparator = (a, b) -> Long.compare(toLong(b.get("pendingFees")), toLong(a.get("pendingFees")));
        } else if (mode == 4) {
            comparator = (a, b) -> Long.compare(toLong(b.get("students")), toLong(a.get("students")));
        } else if (mode == 5) {
            comparator = (a, b) -> Double.compare(toDouble(b.get("resultPct")), toDouble(a.get("resultPct")));
        } else {
            comparator = (a, b) -> Long.compare(toLong(b.get("feesCollected")), toLong(a.get("feesCollected")));
        }
        Collections.sort(branchCards, comparator);
    }
}
