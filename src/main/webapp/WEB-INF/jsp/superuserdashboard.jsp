<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Super User Dashboard</title>
    <link rel="stylesheet" href="/patriswamy/css/bootstrap.min.css">
    <script src="/patriswamy/js/jquery.min.js"></script>
    <script src="/patriswamy/js/echarts/echarts.min.js"></script>
    <style>
        @font-face {
            font-family: "IBMPlexSans";
            src: url("fonts/IBMPlexSans-Regular.ttf");
        }

        :root {
            --curium-navy: #12324a;
            --curium-blue: #2563eb;
            --curium-cyan: #14b8a6;
            --curium-gold: #f59e0b;
            --curium-rose: #ef4444;
            --curium-surface: #ffffff;
            --curium-border: rgba(15, 23, 42, 0.08);
            --curium-shadow: 0 18px 45px rgba(15, 23, 42, 0.10);
        }

        html, body {
            margin: 0;
            padding: 0;
            min-height: 100%;
            background: linear-gradient(180deg, #f5f8fc 0%, #eef4fb 100%);
            font-family: IBMPlexSans, "Segoe UI", Tahoma, Arial, sans-serif;
            color: #1f2937;
        }

        .dashboard-wrap {
            padding: 24px 18px 36px;
        }

        .panel {
            background: var(--curium-surface);
            border-radius: 24px;
            border: 1px solid var(--curium-border);
            box-shadow: var(--curium-shadow);
            padding: 18px;
            margin-bottom: 18px;
        }

        .filter-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(160px, 1fr));
            gap: 10px;
            align-items: end;
        }

        .kpi-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(210px, 1fr));
            gap: 14px;
        }

        .kpi-card {
            position: relative;
            overflow: hidden;
            border-radius: 12px;
            border: 1px solid rgba(15, 23, 42, 0.08);
            background: linear-gradient(180deg, var(--kpi-soft, #ffffff) 0%, #ffffff 40%, #ffffff 100%);
            min-height: 122px;
            padding: 14px 16px;
            box-shadow: 0 8px 24px rgba(15, 23, 42, 0.08);
            transition: transform 0.18s ease, box-shadow 0.18s ease, border-color 0.18s ease;
        }

        .kpi-card::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            height: 4px;
            background: var(--kpi-accent, #2563eb);
        }

        .kpi-card:hover {
            transform: translateY(-3px);
            box-shadow: 0 14px 30px rgba(15, 23, 42, 0.14);
            border-color: rgba(15, 23, 42, 0.12);
        }

        .kpi-card__body {
            display: flex;
            align-items: flex-start;
            gap: 14px;
            position: relative;
            z-index: 1;
        }

        .kpi-card__icon {
            width: 44px;
            height: 44px;
            flex: 0 0 auto;
            display: grid;
            place-items: center;
            border-radius: 14px;
            background: var(--kpi-icon-bg, rgba(37, 99, 235, 0.10));
            color: var(--kpi-accent, #2563eb);
            box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.35);
        }

        .kpi-card__icon svg {
            width: 22px;
            height: 22px;
            display: block;
            stroke: currentColor;
            fill: none;
            stroke-width: 1.9;
            stroke-linecap: round;
            stroke-linejoin: round;
        }

        .kpi-card__content {
            min-width: 0;
            flex: 1 1 auto;
            display: flex;
            flex-direction: column;
            gap: 6px;
        }

        .kpi-card__label {
            font-size: 12px;
            font-weight: 700;
            letter-spacing: 0.02em;
            color: #475569;
            margin: 0;
        }

        .kpi-card__value {
            font-size: 26px;
            line-height: 1.1;
            font-weight: 800;
            letter-spacing: 0.01em;
            color: #0f172a;
            margin: 0;
        }

        .kpi-card__trend {
            margin: 0;
            font-size: 12px;
            color: #64748b;
            line-height: 1.35;
        }

        .kpi-card--blue {
            --kpi-accent: #2563eb;
            --kpi-soft: #eff6ff;
            --kpi-icon-bg: rgba(37, 99, 235, 0.10);
        }

        .kpi-card--indigo {
            --kpi-accent: #4f46e5;
            --kpi-soft: #eef2ff;
            --kpi-icon-bg: rgba(79, 70, 229, 0.10);
        }

        .kpi-card--purple {
            --kpi-accent: #7c3aed;
            --kpi-soft: #f5f3ff;
            --kpi-icon-bg: rgba(124, 58, 237, 0.10);
        }

        .kpi-card--green {
            --kpi-accent: #16a34a;
            --kpi-soft: #f0fdf4;
            --kpi-icon-bg: rgba(22, 163, 74, 0.10);
        }

        .kpi-card--teal {
            --kpi-accent: #0f766e;
            --kpi-soft: #f0fdfa;
            --kpi-icon-bg: rgba(15, 118, 110, 0.10);
        }

        .kpi-card--red {
            --kpi-accent: #dc2626;
            --kpi-soft: #fef2f2;
            --kpi-icon-bg: rgba(220, 38, 38, 0.10);
        }

        .kpi-card--orange {
            --kpi-accent: #ea580c;
            --kpi-soft: #fff7ed;
            --kpi-icon-bg: rgba(234, 88, 12, 0.10);
        }

        .kpi-card--cyan {
            --kpi-accent: #0891b2;
            --kpi-soft: #ecfeff;
            --kpi-icon-bg: rgba(8, 145, 178, 0.10);
        }

        .kpi-card--emerald {
            --kpi-accent: #059669;
            --kpi-soft: #ecfdf5;
            --kpi-icon-bg: rgba(5, 150, 105, 0.10);
        }

        .kpi-card--violet {
            --kpi-accent: #8b5cf6;
            --kpi-soft: #f5f3ff;
            --kpi-icon-bg: rgba(139, 92, 246, 0.10);
        }

        .trend-up { color: #16a34a; }
        .trend-down { color: #dc2626; }

        .branch-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
            gap: 12px;
        }

        .branch-card {
            border-radius: 22px;
            border: 1px solid var(--curium-border);
            background: #fff;
            padding: 12px;
        }

        .branch-name { font-size: 15px; font-weight: 700; margin-bottom: 6px; }
        .branch-meta { font-size: 12px; color: #64748b; margin-bottom: 6px; }

        .metric-row {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 6px;
            font-size: 12px;
        }

        .metric-cell {
            padding: 6px;
            border-radius: 8px;
            background: #f8fafc;
        }

        .chart-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(360px, 1fr));
            gap: 12px;
        }

        .chart-card {
            height: 100%;
            padding: 18px 18px 10px;
            background: var(--curium-surface);
            border: 1px solid var(--curium-border);
            border-radius: 24px;
            box-shadow: var(--curium-shadow);
            transition: transform 0.18s ease, box-shadow 0.18s ease;
        }

        .chart-card:hover {
            transform: translateY(-2px);
            box-shadow: 0 22px 50px rgba(15, 23, 42, 0.14);
        }

        .chart-header {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            gap: 14px;
            margin-bottom: 12px;
        }

        .chart-title {
            margin: 0;
            font-size: 18px;
            font-weight: 700;
            color: var(--curium-navy);
        }

        .chart-actions button {
            border: 0;
            background: rgba(37, 99, 235, 0.08);
            color: var(--curium-blue);
            border-radius: 999px;
            padding: 6px 10px;
            font-size: 11px;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 0.08em;
            white-space: nowrap;
            cursor: pointer;
            transition: background 0.18s ease, color 0.18s ease, transform 0.18s ease;
        }

        .chart-actions button:hover {
            background: rgba(37, 99, 235, 0.14);
            transform: translateY(-1px);
        }

        .chart-stage {
            width: 100%;
            min-height: 360px;
            height: 390px;
        }

        table { width: 100%; border-collapse: collapse; }
        th, td { border-bottom: 1px solid #e2e8f0; padding: 8px; font-size: 12px; }
        th { cursor: pointer; background: #f8fafc; }

        .toolbar {
            display: flex;
            gap: 8px;
            justify-content: flex-end;
        }

        .muted { color: #64748b; font-size: 12px; }

        @media (max-width: 767.98px) {
            .dashboard-wrap {
                padding: 14px 12px 26px;
            }

            .kpi-grid {
                grid-template-columns: repeat(auto-fit, minmax(160px, 1fr));
                gap: 12px;
            }

            .panel {
                border-radius: 20px;
                padding: 14px;
            }

            .kpi-card {
                padding: 12px 12px 13px;
                min-height: 110px;
            }

            .kpi-card__icon {
                width: 40px;
                height: 40px;
                border-radius: 12px;
            }

            .kpi-card__value {
                font-size: 22px;
            }

            .chart-stage {
                min-height: 320px;
                height: 340px;
            }

            .chart-header {
                flex-direction: column;
            }
        }
    </style>
</head>
<%
String user = null;
if (session.getAttribute("userAuth") == null) {
    response.sendRedirect("/patriswamy/UserProcess/sessionTimeOut");
} else {
    user = (String) session.getAttribute("userAuth");
}
%>
<body>
<div class="dashboard-wrap">
    <div class="panel">
        <div class="filter-grid">
            <div>
                <label>Academic Year</label>
                <select id="fAcademicYear" class="form-control form-control-sm"></select>
            </div>
            <div>
                <label>Branch (multi-select)</label>
                <select id="fBranches" class="form-control form-control-sm" multiple></select>
            </div>
            <div>
                <label>From</label>
                <input id="fFromDate" type="date" class="form-control form-control-sm">
            </div>
            <div>
                <label>To</label>
                <input id="fToDate" type="date" class="form-control form-control-sm">
            </div>
            <div>
                <label>Class</label>
                <select id="fClass" class="form-control form-control-sm"><option value="">All</option></select>
            </div>
            <div>
                <label>Section</label>
                <select id="fSection" class="form-control form-control-sm"><option value="">All</option></select>
            </div>
            <div>
                <label>Examination</label>
                <select id="fExam" class="form-control form-control-sm"><option value="">All</option></select>
            </div>
            <div>
                <label>Fee Category</label>
                <select id="fFeeCategory" class="form-control form-control-sm"><option value="">All</option></select>
            </div>
            <div>
                <label>Sort Branch Cards</label>
                <select id="fSortBy" class="form-control form-control-sm">
                    <option value="highest_collection">Highest Collection</option>
                    <option value="lowest_collection">Lowest Collection</option>
                    <option value="highest_pending">Highest Pending Fees</option>
                    <option value="student_strength">Student Strength</option>
                    <option value="performance">Performance</option>
                </select>
            </div>
            <div class="toolbar">
                <button id="applyFilters" class="btn btn-primary btn-sm">Apply</button>
                <button id="resetFilters" class="btn btn-outline-secondary btn-sm">Reset</button>
            </div>
        </div>
        <div id="lastUpdated" class="muted mt-2"></div>
    </div>

    <div class="panel">
        <div id="kpiGrid" class="kpi-grid"></div>
    </div>

    <div class="panel">
        <div class="d-flex justify-content-between align-items-center mb-2">
            <h5 class="m-0">Branch Comparison</h5>
            <span class="muted">Compact view by branch</span>
        </div>
        <div id="branchGrid" class="branch-grid"></div>
    </div>

    <div class="panel">
        <h5>Charts</h5>
        <div class="chart-grid">
            <div class="chart-card"><div class="chart-header"><h6 class="chart-title">Student Strength Comparison</h6><div class="chart-actions"><button data-print="chartStudents">Print</button><button data-fullscreen="chartStudents">Fullscreen</button></div></div><div id="chartStudents" class="chart-stage"></div></div>
            <div class="chart-card"><div class="chart-header"><h6 class="chart-title">Fee Collection Comparison</h6><div class="chart-actions"><button data-print="chartFees">Print</button><button data-fullscreen="chartFees">Fullscreen</button></div></div><div id="chartFees" class="chart-stage"></div></div>
            <div class="chart-card"><div class="chart-header"><h6 class="chart-title">Monthly Collection Trend</h6><div class="chart-actions"><button data-print="chartMonthly">Print</button><button data-fullscreen="chartMonthly">Fullscreen</button></div></div><div id="chartMonthly" class="chart-stage"></div></div>
            <div class="chart-card"><div class="chart-header"><h6 class="chart-title">Attendance Analysis</h6><div class="chart-actions"><button data-print="chartAttendance">Print</button><button data-fullscreen="chartAttendance">Fullscreen</button></div></div><div id="chartAttendance" class="chart-stage"></div></div>
            <div class="chart-card"><div class="chart-header"><h6 class="chart-title">Result Analysis</h6><div class="chart-actions"><button data-print="chartResult">Print</button><button data-fullscreen="chartResult">Fullscreen</button></div></div><div id="chartResult" class="chart-stage"></div></div>
            <div class="chart-card"><div class="chart-header"><h6 class="chart-title">Class-wise Analysis</h6><div class="chart-actions"><button data-print="chartClassWise">Print</button><button data-fullscreen="chartClassWise">Fullscreen</button></div></div><div id="chartClassWise" class="chart-stage"></div></div>
            <div class="chart-card"><div class="chart-header"><h6 class="chart-title">Gender Distribution</h6><div class="chart-actions"><button data-print="chartGender">Print</button><button data-fullscreen="chartGender">Fullscreen</button></div></div><div id="chartGender" class="chart-stage"></div></div>
            <div class="chart-card"><div class="chart-header"><h6 class="chart-title">Category-wise Distribution</h6><div class="chart-actions"><button data-print="chartCategory">Print</button><button data-fullscreen="chartCategory">Fullscreen</button></div></div><div id="chartCategory" class="chart-stage"></div></div>
            <div class="chart-card"><div class="chart-header"><h6 class="chart-title">Fee Structure Analysis</h6><div class="chart-actions"><button data-print="chartStructure">Print</button><button data-fullscreen="chartStructure">Fullscreen</button></div></div><div id="chartStructure" class="chart-stage"></div></div>
            <div class="chart-card"><div class="chart-header"><h6 class="chart-title">Examination Performance</h6><div class="chart-actions"><button data-print="chartExam">Print</button><button data-fullscreen="chartExam">Fullscreen</button></div></div><div id="chartExam" class="chart-stage"></div></div>
        </div>
    </div>

    <div class="panel">
        <h5>Branch Ranking</h5>
        <div class="table-responsive">
            <table id="rankingTable">
                <thead>
                <tr>
                    <th data-key="rank">Rank</th>
                    <th data-key="branchName">Branch</th>
                    <th data-key="students">Students</th>
                    <th data-key="feesCollected">Fee Collection</th>
                    <th data-key="pendingFees">Pending Fees</th>
                    <th data-key="attendancePct">Attendance %</th>
                    <th data-key="resultPct">Result %</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>
</div>

<script>
    // Legacy jQuery-centric dashboard script
    var dashboardData = null;
    var chartMap = {};
    var chartInstances = [];
    var rankingSort = { key: "rank", asc: true };

    // Formatting utilities
    function rupee(value) {
        var num = Number(value || 0);
        return "\u20B9" + new Intl.NumberFormat("en-IN", { maximumFractionDigits: 0 }).format(num);
    }

    function toNumber(value) {
        var numberValue = Number(value);
        return isNaN(numberValue) ? 0 : numberValue;
    }

    function pct(value) {
        return Number(value || 0).toFixed(2) + "%";
    }

    function num(value) {
        return new Intl.NumberFormat("en-IN", { maximumFractionDigits: 0 }).format(Number(value || 0));
    }

    // ECharts gradient utility
    function gradientTopBottom(topColor, bottomColor) {
        return new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: topColor },
            { offset: 1, color: bottomColor }
        ]);
    }

    function buildGradientBarData(values, palettePairs) {
        return (values || []).map(function (value, index) {
            var palette = palettePairs[index % palettePairs.length];
            return {
                value: toNumber(value),
                itemStyle: {
                    color: gradientTopBottom(palette[0], palette[1]),
                    shadowBlur: 12,
                    shadowColor: "rgba(15, 23, 42, 0.16)",
                    shadowOffsetY: 6,
                    borderRadius: [8, 8, 0, 0]
                }
            };
        });
    }

    function readChartValue(param) {
        if (!param) {
            return 0;
        }
        if (param.value && typeof param.value === "object" && param.value.value !== undefined) {
            return toNumber(param.value.value);
        }
        return toNumber(param.value);
    }

    function trendText(trendPct) {
        var value = Number(trendPct || 0);
        if (!isFinite(value) || value === 0) {
            return "-";
        }
        var marker = value > 0 ? "\u2191" : "\u2193";
        var cls = value > 0 ? "trend-up" : "trend-down";
        return '<span class="' + cls + '">' + marker + ' ' + Math.abs(value).toFixed(1) + '% vs previous month</span>';
    }

    // Filter utilities
    function selectedBranchCsv() {
        var ids = [];
        $("#fBranches option:selected").each(function() {
            ids.push($(this).val());
        });
        return ids.join(",");
    }

    function currentFilters() {
        return {
            academicYear: $("#fAcademicYear").val(),
            branchIds: selectedBranchCsv(),
            fromDate: $("#fFromDate").val(),
            toDate: $("#fToDate").val(),
            selectedClass: $("#fClass").val(),
            section: $("#fSection").val(),
            examination: $("#fExam").val(),
            feeCategory: $("#fFeeCategory").val(),
            sortBy: $("#fSortBy").val()
        };
    }

    // jQuery AJAX wrapper (legacy style)
    function postFormEncoded(url, data) {
        return $.ajax({
            type: "POST",
            url: url,
            data: data,
            dataType: "json",
            contentType: "application/x-www-form-urlencoded",
            async: true
        });
    }

    function loadDashboard() {
        postFormEncoded("/patriswamy/UserProcess/superDashboardData", currentFilters())
            .done(function(data) {
                if (window.console && console.debug) {
                    console.debug("[SuperDashboard] AJAX response", data);
                }
                dashboardData = data;
                if (!data || !data.success) {
                    if (window.console && console.warn) {
                        console.warn("[SuperDashboard] Data load failed", data && data.message ? data.message : "Unknown error");
                    }
                    return;
                }
                renderAll();
            })
            .fail(function() {
                console.error("Failed to load dashboard data");
            });
    }

    // Rendering pipeline
    function renderAll() {
        renderKpis();
        renderBranchCards();
        renderRanking();
        renderCharts();
        populateFilterOptions();
        $("#lastUpdated").text("Last refreshed: " + (dashboardData.generatedAt || "-"));
    }

    // KPI card builder
    function createKpiCard(themeClass, label, valueText, trendHtml, iconSvg) {
        return '<div class="kpi-card ' + themeClass + '"><div class="kpi-card__body"><div class="kpi-card__icon" aria-hidden="true">' + iconSvg + '</div><div class="kpi-card__content"><p class="kpi-card__label">' + label + '</p><div class="kpi-card__value">' + valueText + '</div><div class="kpi-card__trend">' + trendHtml + '</div></div></div></div>';
    }

    // KPI SVG icons (legacy approach - inline SVG strings)
    function kpiIconBranch() {
        return '<svg viewBox="0 0 24 24"><path d="M4 20V8l8-4 8 4v12"/><path d="M8 20v-5h8v5"/><path d="M12 4v16"/></svg>';
    }

    function kpiIconStudents() {
        return '<svg viewBox="0 0 24 24"><path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2"/><circle cx="9" cy="7" r="3"/><path d="M22 21v-2a4 4 0 0 0-3-3.87"/><path d="M16 3.13a3 3 0 0 1 0 5.74"/></svg>';
    }

    function kpiIconStaff() {
        return '<svg viewBox="0 0 24 24"><path d="M20 21v-2a4 4 0 0 0-4-4h-8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>';
    }

    function kpiIconFees() {
        return '<svg viewBox="0 0 24 24"><path d="M12 1v22"/><path d="M17 5H9.5a3.5 3.5 0 0 0 0 7H14.5a3.5 3.5 0 0 1 0 7H6"/><path d="M7 5h10"/></svg>';
    }

    function kpiIconCollection() {
        return '<svg viewBox="0 0 24 24"><path d="M3 17l5-5 4 4 8-8"/><path d="M14 8h6v6"/></svg>';
    }

    function kpiIconPending() {
        return '<svg viewBox="0 0 24 24"><path d="M12 8v4l3 2"/><circle cx="12" cy="12" r="9"/></svg>';
    }

    function kpiIconExam() {
        return '<svg viewBox="0 0 24 24"><path d="M8 2h8l4 4v16H4V2h4"/><path d="M8 2v4h4"/><path d="M8 12h8"/><path d="M8 16h5"/></svg>';
    }

    function kpiIconAttendance() {
        return '<svg viewBox="0 0 24 24"><path d="M20 6L9 17l-5-5"/><path d="M4 12a8 8 0 1 1 3 6.3"/></svg>';
    }

    function kpiIconResult() {
        return '<svg viewBox="0 0 24 24"><path d="M4 19V5"/><path d="M4 19h16"/><path d="M8 15l3-3 3 2 5-7"/></svg>';
    }

    function kpiIconUsers() {
        return '<svg viewBox="0 0 24 24"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/><circle cx="9" cy="7" r="4"/><path d="M23 21v-2a4 4 0 0 0-3-3.87"/><path d="M16 3.13a4 4 0 0 1 0 7.74"/></svg>';
    }

    // Render KPI cards
    function renderKpis() {
        var s = dashboardData.summary || {};
        var cmp = dashboardData.comparison || {};
        var html = "";
        html += createKpiCard("kpi-card--blue", "Total Branches", num(s.totalBranches), "-", kpiIconBranch());
        html += createKpiCard("kpi-card--indigo", "Total Students", num(s.totalStudents), "-", kpiIconStudents());
        html += createKpiCard("kpi-card--purple", "Total Staff", num(s.totalStaff), "-", kpiIconStaff());
        html += createKpiCard("kpi-card--green", "Total Fees Collected", rupee(s.totalFeesCollected), trendText(cmp.collectionTrendPct), kpiIconFees());
        html += createKpiCard("kpi-card--teal", "Current Month Collection", rupee(s.currentMonthCollection), trendText(cmp.collectionTrendPct), kpiIconCollection());
        html += createKpiCard("kpi-card--red", "Pending Fees", rupee(s.pendingFees), "-", kpiIconPending());
        html += createKpiCard("kpi-card--orange", "Total Exams Conducted", num(s.totalExamsConducted), "-", kpiIconExam());
        html += createKpiCard("kpi-card--cyan", "Overall Attendance %", pct(s.overallAttendancePct), "-", kpiIconAttendance());
        html += createKpiCard("kpi-card--emerald", "Average Result %", pct(s.averageResultPct), "-", kpiIconResult());
        if (s.totalActiveUsers !== undefined && s.totalActiveUsers !== null) {
            html += createKpiCard("kpi-card--violet", "Total Active Users", num(s.totalActiveUsers), "-", kpiIconUsers());
        }
        $("#kpiGrid").html(html);
    }

    // Render branch comparison cards
    function renderBranchCards() {
        var branches = dashboardData.branches || [];
        var html = "";
        $.each(branches, function(idx, b) {
            html += '<div class="branch-card">';
            html += '<div class="branch-name">' + b.branchName + '</div>';
            html += '<div class="branch-meta">Last Updated: ' + (b.lastUpdated || "-") + '</div>';
            html += '<div class="metric-row">';
            html += '<div class="metric-cell">Students<br><b>' + num(b.students) + '</b></div>';
            html += '<div class="metric-cell">Fees Collected<br><b>' + rupee(b.feesCollected) + '</b></div>';
            html += '<div class="metric-cell">Pending Fees<br><b>' + rupee(b.pendingFees) + '</b></div>';
            html += '<div class="metric-cell">Attendance<br><b>' + pct(b.attendancePct) + '</b></div>';
            html += '<div class="metric-cell">Result<br><b>' + pct(b.resultPct) + '</b></div>';
            html += '<div class="metric-cell">Collection<br><b>' + pct(b.collectionPct) + '</b></div>';
            html += '</div></div>';
        });
        $("#branchGrid").html(html);
    }

    // Render ranking table
    function renderRanking() {
        var rows = (dashboardData.ranking || []).slice();
        rows.sort(function (a, b) {
            var av = a[rankingSort.key];
            var bv = b[rankingSort.key];
            var result = (typeof av === "string" ? String(av).localeCompare(String(bv)) : Number(av || 0) - Number(bv || 0));
            return rankingSort.asc ? result : -result;
        });

        var $tbody = $("#rankingTable tbody");
        var html = "";
        $.each(rows, function(idx, r) {
            html += "<tr>" +
                "<td>" + r.rank + "</td>" +
                "<td>" + r.branchName + "</td>" +
                "<td>" + num(r.students) + "</td>" +
                "<td>" + rupee(r.feesCollected) + "</td>" +
                "<td>" + rupee(r.pendingFees) + "</td>" +
                "<td>" + pct(r.attendancePct) + "</td>" +
                "<td>" + pct(r.resultPct) + "</td>" +
                "</tr>";
        });
        $tbody.html(html);
    }

    // Chart instance management
    function chartInstance(id) {
        if (!chartMap[id]) {
            var instance = echarts.init(document.getElementById(id));
            chartMap[id] = instance;
            chartInstances.push(instance);
        }
        return chartMap[id];
    }

    // Chart option builders - base configurations
    function baseBarOption(categories, rotateLabels) {
        return {
            animationDuration: 900,
            animationEasing: "cubicOut",
            tooltip: {
                trigger: "axis",
                axisPointer: { type: "shadow" }
            },
            legend: {
                bottom: 0,
                textStyle: {
                    fontFamily: "IBMPlexSans, Segoe UI, Tahoma, Arial, sans-serif"
                }
            },
            grid: {
                left: 44,
                right: 20,
                top: 36,
                bottom: 58,
                containLabel: true
            },
            xAxis: {
                type: "category",
                data: categories,
                axisLabel: {
                    interval: 0,
                    rotate: rotateLabels ? 35 : 0,
                    color: "#334155",
                    fontFamily: "IBMPlexSans, Segoe UI, Tahoma, Arial, sans-serif"
                },
                axisLine: {
                    lineStyle: { color: "rgba(15,23,42,0.20)" }
                }
            },
            yAxis: {
                type: "value",
                min: 0,
                axisLabel: {
                    color: "#334155",
                    fontFamily: "IBMPlexSans, Segoe UI, Tahoma, Arial, sans-serif"
                },
                splitLine: {
                    lineStyle: { color: "rgba(15,23,42,0.08)" }
                }
            }
        };
    }

    function baseLineOption(categories, rotateLabels) {
        var option = baseBarOption(categories, rotateLabels);
        option.tooltip.axisPointer = { type: "line" };
        return option;
    }

    // Tooltip formatter for currency values
    function currencyAxisTooltipFormatter(params) {
        if (!params || !params.length) {
            return "";
        }
        var rows = [params[0].axisValueLabel || params[0].name || ""];
        $.each(params, function(idx, item) {
            rows.push((item.marker || "") + item.seriesName + ": " + rupee(readChartValue(item)));
        });
        return rows.join("<br/>");
    }

    // Render all charts
    function renderCharts() {
        var charts = dashboardData.charts || {};
        var branchSeries = charts.studentStrengthComparison || [];
        var labels = $.map(branchSeries, function(x) { return x.branch; });

        // Student Strength Chart
        var studentOption = baseBarOption(labels, labels.length > 6);
        studentOption.legend.data = ["Students"];
        studentOption.series = [{
            type: "bar",
            name: "Students",
            barMaxWidth: 38,
            data: buildGradientBarData($.map(branchSeries, function(x) { return x.students; }), [["#60a5fa", "#2563eb"], ["#34d399", "#0f766e"], ["#c4b5fd", "#7c3aed"]]),
            label: { show: true, position: "top", color: "#0f172a", fontWeight: "bold" }
        }];
        chartInstance("chartStudents").setOption(studentOption, true);

        // Fee Collection Chart
        var feesOption = baseBarOption(labels, labels.length > 6);
        feesOption.legend.data = ["Collected", "Pending"];
        feesOption.tooltip.formatter = currencyAxisTooltipFormatter;
        feesOption.yAxis.axisLabel.formatter = function (value) { return rupee(value); };
        feesOption.series = [{
            type: "bar",
            name: "Collected",
            barMaxWidth: 28,
            data: $.map(branchSeries || [], function(x) {
                return {
                    value: toNumber(x.collection),
                    itemStyle: {
                        color: gradientTopBottom("#5eead4", "#14b8a6"),
                        shadowBlur: 10,
                        shadowColor: "rgba(20,184,166,0.24)",
                        shadowOffsetY: 6,
                        borderRadius: [8, 8, 0, 0]
                    }
                };
            }),
            label: {
                show: true,
                position: "top",
                color: "#0f172a",
                fontWeight: "bold",
                formatter: function (params) { return rupee(readChartValue(params)); }
            }
        }, {
            type: "bar",
            name: "Pending",
            barMaxWidth: 28,
            data: $.map(branchSeries || [], function(x) {
                return {
                    value: toNumber(x.pending),
                    itemStyle: {
                        color: gradientTopBottom("#fda4af", "#ef4444"),
                        shadowBlur: 10,
                        shadowColor: "rgba(239,68,68,0.24)",
                        shadowOffsetY: 6,
                        borderRadius: [8, 8, 0, 0]
                    }
                };
            }),
            label: {
                show: true,
                position: "top",
                color: "#0f172a",
                fontWeight: "bold",
                formatter: function (params) { return rupee(readChartValue(params)); }
            }
        }];
        chartInstance("chartFees").setOption(feesOption, true);

        // Monthly Collection Trend Chart
        var monthlyMonths = charts.monthlyCollectionMonths || [];
        var monthlyOption = baseLineOption(monthlyMonths, monthlyMonths.length > 6);
        monthlyOption.legend.type = "scroll";
        monthlyOption.tooltip.formatter = currencyAxisTooltipFormatter;
        monthlyOption.yAxis.axisLabel.formatter = function (value) { return rupee(value); };
        monthlyOption.series = $.map(charts.monthlyCollectionSeries || [], function(s, index) {
            var linePalette = [["#5eead4", "#14b8a6"], ["#93c5fd", "#2563eb"], ["#c4b5fd", "#8b5cf6"], ["#fcd34d", "#f59e0b"], ["#f9a8d4", "#ec4899"]];
            var pair = linePalette[index % linePalette.length];
            return {
                type: "line",
                smooth: true,
                name: s.name,
                data: s.data,
                symbol: "circle",
                symbolSize: 7,
                lineStyle: { width: 3, color: pair[1] },
                itemStyle: { color: pair[1], borderColor: "#ffffff", borderWidth: 1 },
                areaStyle: { color: gradientTopBottom(pair[0], "rgba(255,255,255,0.10)") }
            };
        });
        chartInstance("chartMonthly").setOption(monthlyOption, true);

        // Attendance Analysis Chart
        var attendanceOption = baseBarOption(labels, labels.length > 6);
        attendanceOption.legend.data = ["Attendance %"];
        attendanceOption.yAxis.max = 100;
        attendanceOption.yAxis.axisLabel.formatter = function (value) { return toNumber(value).toFixed(0) + "%"; };
        attendanceOption.series = [{
            type: "bar",
            name: "Attendance %",
            barMaxWidth: 38,
            data: buildGradientBarData($.map(branchSeries, function(x) { return x.attendance; }), [["#93c5fd", "#2563eb"], ["#6ee7b7", "#059669"], ["#c4b5fd", "#7c3aed"]]),
            label: { show: true, position: "top", color: "#0f172a", fontWeight: "bold", formatter: function (params) { return readChartValue(params).toFixed(1) + "%"; } }
        }];
        chartInstance("chartAttendance").setOption(attendanceOption, true);

        // Result Analysis Chart
        var resultOption = baseBarOption(labels, labels.length > 6);
        resultOption.legend.data = ["Result %"];
        resultOption.yAxis.max = 100;
        resultOption.yAxis.axisLabel.formatter = function (value) { return toNumber(value).toFixed(0) + "%"; };
        resultOption.series = [{
            type: "bar",
            name: "Result %",
            barMaxWidth: 38,
            data: buildGradientBarData($.map(branchSeries, function(x) { return x.result; }), [["#c4b5fd", "#7c3aed"], ["#93c5fd", "#2563eb"], ["#f9a8d4", "#ec4899"]]),
            label: { show: true, position: "top", color: "#0f172a", fontWeight: "bold", formatter: function (params) { return readChartValue(params).toFixed(1) + "%"; } }
        }];
        chartInstance("chartResult").setOption(resultOption, true);

        // Class-wise Analysis Chart
        var classData = charts.classWiseAnalysis || [];
        var classOption = baseBarOption($.map(classData, function(x) { return x.name; }), classData.length > 6);
        classOption.legend.data = ["Students"];
        classOption.series = [{
            type: "bar",
            name: "Students",
            barMaxWidth: 38,
            data: buildGradientBarData($.map(classData, function(x) { return x.value; }), [["#60a5fa", "#2563eb"], ["#34d399", "#0f766e"], ["#c4b5fd", "#7c3aed"]]),
            label: { show: true, position: "top", color: "#0f172a", fontWeight: "bold" }
        }];
        chartInstance("chartClassWise").setOption(classOption, true);

        // Pie chart builder
        function buildPieOption(dataRows, nameLabel) {
            return {
                animationDuration: 900,
                animationEasing: "cubicOut",
                tooltip: {
                    trigger: "item",
                    formatter: function (params) {
                        return params.name + ": " + num(params.value);
                    }
                },
                legend: {
                    bottom: 0,
                    left: "center",
                    textStyle: {
                        fontFamily: "IBMPlexSans, Segoe UI, Tahoma, Arial, sans-serif"
                    }
                },
                series: [{
                    name: nameLabel,
                    type: "pie",
                    radius: ["38%", "70%"],
                    center: ["50%", "44%"],
                    avoidLabelOverlap: true,
                    label: {
                        formatter: function (params) {
                            return params.name + ": " + num(params.value);
                        },
                        color: "#334155",
                        fontFamily: "IBMPlexSans, Segoe UI, Tahoma, Arial, sans-serif"
                    },
                    itemStyle: {
                        borderColor: "#ffffff",
                        borderWidth: 2,
                        shadowBlur: 14,
                        shadowColor: "rgba(15,23,42,0.14)"
                    },
                    data: dataRows || []
                }]
            };
        }

        // Gender & Category Distribution Charts
        chartInstance("chartGender").setOption(buildPieOption(charts.genderDistribution || [], "Gender Distribution"), true);
        chartInstance("chartCategory").setOption(buildPieOption(charts.categoryDistribution || [], "Category Distribution"), true);

        // Fee Structure Analysis Chart
        var structure = charts.feeStructureAnalysis || [];
        var structureOption = baseBarOption($.map(structure, function(x) { return x.category; }), structure.length > 6);
        structureOption.legend.data = ["Total", "Paid"];
        structureOption.tooltip.formatter = currencyAxisTooltipFormatter;
        structureOption.yAxis.axisLabel.formatter = function (value) { return rupee(value); };
        structureOption.series = [{
            type: "bar",
            name: "Total",
            barMaxWidth: 28,
            data: buildGradientBarData($.map(structure, function(x) { return x.total; }), [["#93c5fd", "#2563eb"], ["#c4b5fd", "#7c3aed"], ["#fcd34d", "#f59e0b"]]),
            label: { show: true, position: "top", color: "#0f172a", fontWeight: "bold", formatter: function (params) { return rupee(readChartValue(params)); } }
        }, {
            type: "bar",
            name: "Paid",
            barMaxWidth: 28,
            data: buildGradientBarData($.map(structure, function(x) { return x.paid; }), [["#5eead4", "#14b8a6"], ["#93c5fd", "#2563eb"], ["#6ee7b7", "#059669"]]),
            label: { show: true, position: "top", color: "#0f172a", fontWeight: "bold", formatter: function (params) { return rupee(readChartValue(params)); } }
        }];
        chartInstance("chartStructure").setOption(structureOption, true);

        // Examination Performance Chart
        var exams = charts.examinationPerformance || [];
        var examOption = baseLineOption($.map(exams, function(x) { return x.exam; }), exams.length > 6);
        examOption.legend.data = ["Average %"];
        examOption.yAxis.max = 100;
        examOption.yAxis.axisLabel.formatter = function (value) { return toNumber(value).toFixed(0) + "%"; };
        examOption.series = [{
            type: "line",
            smooth: true,
            name: "Average %",
            data: $.map(exams, function(x) { return x.average; }),
            symbol: "circle",
            symbolSize: 7,
            lineStyle: { width: 3, color: "#7c3aed" },
            itemStyle: { color: "#7c3aed", borderColor: "#ffffff", borderWidth: 1 },
            areaStyle: { color: gradientTopBottom("#c4b5fd", "rgba(255,255,255,0.10)") }
        }];
        chartInstance("chartExam").setOption(examOption, true);
    }

    // Filter population utilities
    function populateSelect(id, rows, valueKey, labelKey, includeAll) {
        var $el = $("#" + id);
        if ($el.length === 0) {
            return;
        }
        var current = $el.val();
        var html = includeAll ? '<option value="">All</option>' : "";
        $.each(rows || [], function(idx, row) {
            html += '<option value="' + row[valueKey] + '">' + row[labelKey] + '</option>';
        });
        $el.html(html);
        $el.val(current);
    }

    function populateFilterOptions() {
        if (!dashboardData || !dashboardData.filterOptions) {
            return;
        }

        var f = dashboardData.filterOptions;
        populateSelect("fExam", f.examinations, "id", "name", true);
        populateSelect("fFeeCategory", f.feeCategories, "id", "name", true);

        var classRows = $.map(f.classes || [], function(x) { return { id: x, name: x }; });
        var sectionRows = $.map(f.sections || [], function(x) { return { id: x, name: x }; });

        populateSelect("fClass", classRows, "id", "name", true);
        populateSelect("fSection", sectionRows, "id", "name", true);

        var $branchEl = $("#fBranches");
        if ($branchEl.find("option").length === 0) {
            var branchHtml = "";
            $.each(f.branches || [], function(idx, b) {
                branchHtml += '<option value="' + b.id + '" selected>' + b.name + '</option>';
            });
            $branchEl.html(branchHtml);
            if (window.console && console.debug) {
                console.debug("[SuperDashboard] Branch options added", $branchEl.find("option").length);
            }
        }
    }

    // Initialize static filter dropdown values
    function initStaticFilters() {
        var $ayEl = $("#fAcademicYear");
        var selectedAy = "${selectedAcademicYear}";
        var years = [];
        <% if (session.getAttribute("previousAcademicYears") != null) { %>
        years = [
            <% java.util.List<String> ys = (java.util.List<String>)session.getAttribute("previousAcademicYears");
               for (int i = 0; i < ys.size(); i++) { %>
            "<%= ys.get(i) %>"<%= (i < ys.size() - 1) ? "," : "" %>
            <% } %>
        ];
        <% } %>

        if (years.length === 0 && selectedAy) {
            years.push(selectedAy);
        }

        var html = "";
        $.each(years, function(idx, y) {
            html += '<option value="' + y + '">' + y + '</option>';
        });
        $ayEl.html(html);
        $ayEl.val(selectedAy || (years[0] || ""));

        var now = new Date();
        var monthStart = new Date(now.getFullYear(), now.getMonth(), 1);
        $("#fToDate").val(now.toISOString().split('T')[0]);
        $("#fFromDate").val(monthStart.toISOString().split('T')[0]);
    }

    // Legacy jQuery event binding
    function bindEvents() {
        // Apply and Reset button handlers
        $("#applyFilters").on("click", function() {
            loadDashboard();
        });

        $("#resetFilters").on("click", function() {
            initStaticFilters();
            $("#fClass").val("");
            $("#fSection").val("");
            $("#fExam").val("");
            $("#fFeeCategory").val("");
            $("#fSortBy").val("highest_collection");
            var $branchEl = $("#fBranches");
            $branchEl.find("option").prop("selected", true);
            loadDashboard();
        });

        // Table header click for sorting
        $("#rankingTable").on("click", "th", function() {
            var key = $(this).attr("data-key");
            if (rankingSort.key === key) {
                rankingSort.asc = !rankingSort.asc;
            } else {
                rankingSort.key = key;
                rankingSort.asc = true;
            }
            renderRanking();
        });

        // Fullscreen button handlers
        $(document).on("click", "[data-fullscreen]", function() {
            var id = $(this).attr("data-fullscreen");
            var node = document.getElementById(id);
            if (node && node.requestFullscreen) {
                node.requestFullscreen();
            }
        });

        // Print button handlers
        $(document).on("click", "[data-print]", function() {
            var id = $(this).attr("data-print");
            var chart = chartMap[id];
            if (!chart) {
                return;
            }
            var image = chart.getDataURL({ pixelRatio: 2, backgroundColor: "#fff" });
            var printWindow = window.open("", "_blank");
            printWindow.document.write("<img src='" + image + "' style='width:100%;max-width:1000px;'>");
            printWindow.document.close();
            printWindow.focus();
            printWindow.print();
        });

        // Window resize handler for chart resizing
        $(window).on("resize", function() {
            $.each(chartInstances, function(idx, instance) {
                instance.resize();
            });
        });
    }

    // Initialize dashboard on document ready (legacy jQuery pattern)
    $(document).ready(function() {
        initStaticFilters();
        bindEvents();
        loadDashboard();
    });

</script>
</body>
</html>
