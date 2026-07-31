<%--
  Document   : Dash Board
  Created on : Jan 13, 2012, 12:21:03 PM
  Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Dash Board</title>
        <link rel="stylesheet" href="/patriswamy/css/bootstrap.min.css">
        <script src="/patriswamy/js/echarts/echarts.min.js"></script>
    </head>

    <style type="text/css">
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

        .dashboard-shell {
            padding: 24px 18px 36px;
        }

        .dashboard-header {
            margin-bottom: 20px;
            padding: 20px 24px;
            background: linear-gradient(135deg, rgba(18, 50, 74, 0.96), rgba(37, 99, 235, 0.92));
            border-radius: 24px;
            color: #ffffff;
            box-shadow: var(--curium-shadow);
        }

        .dashboard-header__eyebrow {
            display: inline-block;
            font-size: 12px;
            text-transform: uppercase;
            letter-spacing: 0.14em;
            opacity: 0.82;
            margin-bottom: 6px;
        }

        .dashboard-header__title {
            margin: 0;
            font-size: 30px;
            font-weight: 700;
            line-height: 1.15;
        }

        .dashboard-header__subtitle {
            margin: 8px 0 0;
            max-width: 900px;
            font-size: 14px;
            opacity: 0.88;
        }

        .summary-card {
            position: relative;
            overflow: hidden;
            height: 100%;
            padding: 18px 20px;
            border-radius: 22px;
            color: #ffffff;
            box-shadow: var(--curium-shadow);
            border: 1px solid rgba(255, 255, 255, 0.16);
        }

        .summary-card::after {
            content: "";
            position: absolute;
            inset: auto -32px -32px auto;
            width: 130px;
            height: 130px;
            border-radius: 50%;
            background: rgba(255, 255, 255, 0.12);
            filter: blur(6px);
        }

        .summary-card--students {
            background: linear-gradient(135deg, #2563eb, #1d4ed8);
        }

        .summary-card--fees {
            background: linear-gradient(135deg, #0f766e, #14b8a6);
        }

        .summary-card--revenue {
            background: linear-gradient(135deg, #7c3aed, #a855f7);
        }

        .summary-card--today {
            background: linear-gradient(135deg, #ea580c, #f97316);
        }

        .summary-card__row {
            display: flex;
            align-items: flex-start;
            gap: 14px;
            position: relative;
            z-index: 1;
        }

        .summary-card__icon {
            flex: 0 0 auto;
            width: 46px;
            height: 46px;
            display: grid;
            place-items: center;
            border-radius: 16px;
            background: rgba(255, 255, 255, 0.18);
            box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.20);
        }

        .summary-card__icon img {
            width: 24px;
            height: 24px;
            object-fit: contain;
            filter: brightness(0) invert(1);
        }

        .summary-card__content {
            flex: 1 1 auto;
            min-width: 0;
        }

        .summary-card__label {
            display: block;
            margin: 0;
            font-size: 13px;
            font-weight: 600;
            opacity: 0.92;
            line-height: 1.35;
        }

        .summary-card__value {
            display: block;
            margin-top: 8px;
            font-size: 26px;
            font-weight: 700;
            line-height: 1.15;
            letter-spacing: 0.01em;
        }

        .summary-card__secondary {
            display: block;
            margin-top: 8px;
            font-size: 13px;
            opacity: 0.88;
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

        .chart-card__header {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            gap: 14px;
            margin-bottom: 12px;
        }

        .chart-card__title {
            margin: 0;
            font-size: 18px;
            font-weight: 700;
            color: var(--curium-navy);
        }

        .chart-card__subtitle {
            margin: 5px 0 0;
            font-size: 13px;
            color: #64748b;
            line-height: 1.45;
        }

        .chart-card__badge {
            flex: 0 0 auto;
            padding: 6px 10px;
            border-radius: 999px;
            background: rgba(37, 99, 235, 0.08);
            color: var(--curium-blue);
            font-size: 11px;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 0.08em;
            white-space: nowrap;
        }

        .chart-stage {
            width: 100%;
            min-height: 360px;
            height: 390px;
        }

        .chart-stage--short {
            height: 360px;
        }

        .dashboard-grid {
            margin-top: 6px;
        }

        .dashboard-row-gap {
            margin-top: 22px;
        }

        @media (max-width: 767.98px) {
            .dashboard-shell {
                padding: 14px 12px 26px;
            }

            .dashboard-header {
                padding: 18px 16px;
                border-radius: 20px;
            }

            .dashboard-header__title {
                font-size: 24px;
            }

            .summary-card__value {
                font-size: 22px;
            }

            .chart-stage {
                min-height: 320px;
                height: 340px;
            }

            .chart-card__header {
                flex-direction: column;
            }
        }
    </style>

    <%
        //allow access only if session exists
        String user = null;
        if (session.getAttribute("userAuth") == null) {
            response.sendRedirect("/patriswamy/UserProcess/sessionTimeOut");
        } else {
            user = (String) session.getAttribute("userAuth");
        }
        String userName = null;
        String sessionID = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    userName = cookie.getValue();
                }
                if (cookie.getName().equals("JSESSIONID")) {
                    sessionID = cookie.getValue();
                }
            }
        }
    %>

    <body>
        <div class="dashboard-shell">
            <!-- <div class="dashboard-header">
                <span class="dashboard-header__eyebrow">Curium ERP Dashboard</span>
                <h1 class="dashboard-header__title">School Analytics Overview</h1>
                <p class="dashboard-header__subtitle">
                    A modern 3D chart dashboard for student strength, fees, expenses, and gender distribution.
                    The page keeps the existing backend bindings and renders the same values in a polished, responsive UI.
                </p>
            </div> -->

            <div class="row dashboard-grid">
                <div class="col-xl-3 col-lg-6 col-md-6 col-12 mb-4">
                    <div class="summary-card summary-card--students">
                        <div class="summary-card__row">
                            <div class="summary-card__icon">
                                <img src="/patriswamy/images/student.svg" alt="Students" />
                            </div>
                            <div class="summary-card__content">
                                <span class="summary-card__label">Students / Teachers</span>
                                <span class="summary-card__value">${totalstudents}</span>
                                <span class="summary-card__secondary">Teachers: ${totalteachers}</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-lg-6 col-md-6 col-12 mb-4">
                    <div class="summary-card summary-card--fees">
                        <div class="summary-card__row">
                            <div class="summary-card__icon">
                                <img src="/patriswamy/images/totalfees.svg" alt="Fees" />
                            </div>
                            <div class="summary-card__content">
                                <span class="summary-card__label">Total Fees / This Month</span>
                                <span class="summary-card__value rupee-display" data-amount="${totalFeesAmountDashBoard}">${totalFeesAmountDashBoard}</span>
                                <span class="summary-card__secondary">${Currentmonth} Fees: <span class="rupee-display" data-amount="${sumOfFeesMonthly}">${sumOfFeesMonthly}</span></span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-lg-6 col-md-6 col-12 mb-4">
                    <div class="summary-card summary-card--revenue">
                        <div class="summary-card__row">
                            <div class="summary-card__icon">
                                <img src="/patriswamy/images/feespaid.svg" alt="Fees Paid" />
                            </div>
                            <div class="summary-card__content">
                                <span class="summary-card__label">Fees Paid / Due</span>
                                <span class="summary-card__value rupee-display" data-amount="${totalPaidAmountDashBoard}">${totalPaidAmountDashBoard}</span>
                                <span class="summary-card__secondary">Due: <span class="rupee-display" data-amount="${totalDueAmountDashBoard}">${totalDueAmountDashBoard}</span></span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-lg-6 col-md-6 col-12 mb-4">
                    <div class="summary-card summary-card--today">
                        <div class="summary-card__row">
                            <div class="summary-card__icon">
                                <img src="/patriswamy/images/todayscollection.svg" alt="Today" />
                            </div>
                            <div class="summary-card__content">
                                <span class="summary-card__label">Today's Fees / Expenses</span>
                                <span class="summary-card__value rupee-display" data-amount="${sumOfFeesDaily}">${sumOfFeesDaily}</span>
                                <span class="summary-card__secondary">Expenses: ${dailyexpenses}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row dashboard-grid dashboard-row-gap">
            
            	<div class="col-lg-6 col-12 mb-4">
                    <div class="chart-card">
                        <div class="chart-card__header">
                            <div>
                                <h2 class="chart-card__title">Monthly Fees Collection</h2>
                                <!-- <p class="chart-card__subtitle">3D performance trend for fee collection across the monthly series already supplied by the backend.</p> -->
                            </div>
                           <!--  <div class="chart-card__badge">3D Column</div> -->
                        </div>
                        <div id="student-monthlyfeeschart" class="chart-stage"></div>
                    </div>
                </div>
                
                <div class="col-lg-6 col-12 mb-4">
                    <div class="chart-card">
                        <div class="chart-card__header">
                            <div>
                                <h2 class="chart-card__title">Monthly Expenses</h2>
                                <!-- <p class="chart-card__subtitle">A streamlined 3D column chart for month-by-month operational spending.</p> -->
                            </div>
                            <!-- <div class="chart-card__badge">3D Column</div> -->
                        </div>
                        <div id="student-expensechart" class="chart-stage"></div>
                    </div>
                </div>
            </div>

            <div class="row dashboard-grid dashboard-row-gap">
				
				<div class="col-lg-6 col-12 mb-4">
                    <div class="chart-card">
                        <div class="chart-card__header">
                            <div>
                                <h2 class="chart-card__title">Number of Students per Class</h2>
                                <!-- <p class="chart-card__subtitle">3D column view with inline values, hover tooltips, and label rotation for dense categories.</p> -->
                            </div>
                            <!-- <div class="chart-card__badge">3D Column</div> -->
                        </div>
                        <div id="student-chart" class="chart-stage"></div>
                    </div>
                </div>                

                <div class="col-lg-6 col-12 mb-4">
                    <div class="chart-card">
                        <div class="chart-card__header">
                            <div>
                                <h2 class="chart-card__title">Boys vs Girls by Class</h2>
                                <!-- <p class="chart-card__subtitle">A grouped 3D comparison that keeps class-wise gender distribution easy to read on every screen size.</p> -->
                            </div>
                            <!-- <div class="chart-card__badge">3D Column</div> -->
                        </div>
                        <div id="student-male-female" class="chart-stage chart-stage--short"></div>
                    </div>
                </div>
            </div>

            <div class="row dashboard-grid dashboard-row-gap">
                <div class="col-lg-12 col-12 mb-4">
                    <div class="chart-card">
                        <div class="chart-card__header">
                            <div>
                                <h2 class="chart-card__title">Total Fees Paid vs Due</h2>
                                <!-- <p class="chart-card__subtitle">3D pie overview of the current collection position using the same dashboard totals already shown above.</p> -->
                            </div>
                            <!-- <div class="chart-card__badge">3D Pie</div> -->
                        </div>
                        <div id="fees-paid-due-chart" class="chart-stage chart-stage--short"></div>
                    </div>
                </div>
            </div>

            <form id="form1" method="post" style="display:none;">
                <label id="classesstudying">${studentxaxis}</label>
                <label id="students">${studentyaxis}</label>
                <label id="studentsfees">${studenttotalfees}</label>
                <label id="currentdate">${currentdate}</label>
                <label id="monthlystudentsfees">${monthlystudentsfees}</label>
                <label id="monthlist">${monthlist}</label>
                <label id="monthlytotalexpenses">${monthlyexpenses}</label>
                <label id="monthlisttotalexpenses">${monthlistexpenses}</label>
                <label id="totalboysgirls">${totalboysgirls}</label>
                <label id="totalPaidAmountDashBoard">${totalPaidAmountDashBoard}</label>
                <label id="totalDueAmountDashBoard">${totalDueAmountDashBoard}</label>
                <label id="classNames">${classNames}</label>
                <label id="boysData">${boysData}</label>
                <label id="girlsData">${girlsData}</label>
            </form>
        </div>

        <script type="text/javascript">
            function parseJsonLabel(id) {
                var element = document.getElementById(id);
                var rawValue = element ? (element.textContent || element.innerText || "") : "";
                rawValue = rawValue.replace(/^\s+|\s+$/g, "");
                if (!rawValue) {
                    return [];
                }
                try {
                    return JSON.parse(rawValue);
                } catch (error) {
                    return [];
                }
            }

            function toNumber(value) {
                var numberValue = Number(value);
                return isNaN(numberValue) ? 0 : numberValue;
            }

            function readNumericText(id) {
                var element = document.getElementById(id);
                var rawValue = element ? (element.textContent || element.innerText || "") : "";
                return toNumber(rawValue.replace(/,/g, ""));
            }

            var inrNumberFormatter = new Intl.NumberFormat("en-IN", { maximumFractionDigits: 0 });

            function formatAsRupee(value) {
                if (value === null || value === undefined) {
                    return "\u20B90";
                }

                var rawValue = String(value).replace(/\u20B9/g, "").replace(/,/g, "").trim();
                if (!rawValue) {
                    return "\u20B90";
                }

                var numericValue = Number(rawValue);
                if (!isFinite(numericValue) || numericValue === 0) {
                    return "\u20B90";
                }

                return "\u20B9" + inrNumberFormatter.format(numericValue);
            }

            function applyRupeeFormatting() {
                var fields = document.querySelectorAll(".rupee-display");
                fields.forEach(function (field) {
                    var rawValue = field.getAttribute("data-amount");
                    if (rawValue === null || rawValue === undefined) {
                        rawValue = field.textContent || field.innerText || "";
                    }

                    var formattedValue = formatAsRupee(rawValue);
                    field.textContent = formattedValue;
                    field.setAttribute("title", formattedValue);
                });
            }

            function buildSeriesData(categories, values) {
                var sourceCategories = Array.isArray(categories) ? categories : [];
                var sourceValues = Array.isArray(values) ? values : [];
                var length = Math.max(sourceCategories.length, sourceValues.length);
                var data = [];

                for (var index = 0; index < length; index++) {
                    data.push({
                        category: sourceCategories[index] !== undefined ? String(sourceCategories[index]) : "Category " + (index + 1),
                        value: toNumber(sourceValues[index])
                    });
                }

                return data;
            }

            function buildGenderSeriesData(categories, boysValues, girlsValues) {
                var labels = Array.isArray(categories) ? categories : [];
                var boys = Array.isArray(boysValues) ? boysValues : [];
                var girls = Array.isArray(girlsValues) ? girlsValues : [];
                var size = Math.max(labels.length, boys.length, girls.length);
                var data = [];

                for (var i = 0; i < size; i++) {
                    data.push({
                        category: labels[i] !== undefined ? String(labels[i]) : "Class " + (i + 1),
                        boys: toNumber(boys[i]),
                        girls: toNumber(girls[i])
                    });
                }

                return data;
            }

            function gradientTopBottom(topColor, bottomColor) {
                return new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                    { offset: 0, color: topColor },
                    { offset: 1, color: bottomColor }
                ]);
            }

            function buildGradientBarData(values, palettePairs) {
                return values.map(function (value, index) {
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

            var chartInstances = [];

            function registerChart(instance) {
                chartInstances.push(instance);
                return instance;
            }

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

            function renderSingleBarChart(containerId, seriesName, categories, values, palettePairs, formatAsCurrency) {
                var container = document.getElementById(containerId);
                if (!container) {
                    return;
                }

                var rotateLabels = categories.length > 6;
                var chart = registerChart(echarts.init(container));
                var option = baseBarOption(categories, rotateLabels);
                var useCurrencyFormat = !!formatAsCurrency;

                if (useCurrencyFormat) {
                    option.yAxis.axisLabel.formatter = function (value) {
                        return formatAsRupee(value);
                    };
                    option.tooltip.formatter = function (params) {
                        if (!params || !params.length) {
                            return "";
                        }

                        var rows = [params[0].axisValueLabel || params[0].name || ""];
                        params.forEach(function (item) {
                            rows.push((item.marker || "") + item.seriesName + ": " + formatAsRupee(readChartValue(item)));
                        });
                        return rows.join("<br/>");
                    };
                }

                option.legend.data = [seriesName];
                option.series = [
                    {
                        name: seriesName,
                        type: "bar",
                        barMaxWidth: 38,
                        data: buildGradientBarData(values, palettePairs),
                        label: {
                            show: true,
                            position: "top",
                            color: "#0f172a",
                            fontWeight: "bold",
                            formatter: useCurrencyFormat ? function (params) {
                                return formatAsRupee(readChartValue(params));
                            } : undefined
                        }
                    }
                ];

                chart.setOption(option);
            }

            function renderGroupedBarChart(containerId, categories, boysValues, girlsValues) {
                var container = document.getElementById(containerId);
                if (!container) {
                    return;
                }

                var rows = buildGenderSeriesData(categories, boysValues, girlsValues);
                var labels = rows.map(function (row) { return row.category; });
                var boys = rows.map(function (row) { return row.boys; });
                var girls = rows.map(function (row) { return row.girls; });

                var chart = registerChart(echarts.init(container));
                var option = baseBarOption(labels, labels.length > 6);

                option.legend.data = ["Boys", "Girls"];
                option.series = [
                    {
                        name: "Boys",
                        type: "bar",
                        barMaxWidth: 28,
                        data: boys.map(function (value) {
                            return {
                                value: value,
                                itemStyle: {
                                    color: gradientTopBottom("#60a5fa", "#2563eb"),
                                    shadowBlur: 10,
                                    shadowColor: "rgba(37,99,235,0.25)",
                                    shadowOffsetY: 6,
                                    borderRadius: [8, 8, 0, 0]
                                }
                            };
                        }),
                        label: {
                            show: true,
                            position: "top",
                            color: "#0f172a",
                            fontWeight: "bold"
                        }
                    },
                    {
                        name: "Girls",
                        type: "bar",
                        barMaxWidth: 28,
                        data: girls.map(function (value) {
                            return {
                                value: value,
                                itemStyle: {
                                    color: gradientTopBottom("#f9a8d4", "#ec4899"),
                                    shadowBlur: 10,
                                    shadowColor: "rgba(236,72,153,0.22)",
                                    shadowOffsetY: 6,
                                    borderRadius: [8, 8, 0, 0]
                                }
                            };
                        }),
                        label: {
                            show: true,
                            position: "top",
                            color: "#0f172a",
                            fontWeight: "bold"
                        }
                    }
                ];

                chart.setOption(option);
            }

            function renderPieChart(containerId, paidValue, dueValue) {
                var container = document.getElementById(containerId);
                if (!container) {
                    return;
                }

                var chart = registerChart(echarts.init(container));
                chart.setOption({
                    animationDuration: 900,
                    animationEasing: "cubicOut",
                    tooltip: {
                        trigger: "item",
                        formatter: function (params) {
                            return params.name + ": " + formatAsRupee(params.value);
                        }
                    },
                    legend: {
                        bottom: 0,
                        left: "center",
                        textStyle: {
                            fontFamily: "IBMPlexSans, Segoe UI, Tahoma, Arial, sans-serif"
                        }
                    },
                    series: [
                        {
                            name: "Fees Status",
                            type: "pie",
                            radius: ["38%", "70%"],
                            center: ["50%", "44%"],
                            avoidLabelOverlap: true,
                            label: {
                                formatter: function (params) {
                                    return params.name + ": " + formatAsRupee(params.value);
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
                            data: [
                                {
                                    value: paidValue,
                                    name: "Fees Collected",
                                    itemStyle: { color: "#22c55e" }
                                },
                                {
                                    value: dueValue,
                                    name: "Fees Due",
                                    itemStyle: { color: "#ef4444" }
                                }
                            ]
                        }
                    ]
                });
            }

            var studentClasses = parseJsonLabel("classesstudying");
            var studentCounts = parseJsonLabel("students");
            var monthlyExpenses = parseJsonLabel("monthlytotalexpenses");
            var expenseMonths = parseJsonLabel("monthlisttotalexpenses");
            var monthlyFees = parseJsonLabel("monthlystudentsfees");
            var feeMonths = parseJsonLabel("monthlist");
            var classNames = parseJsonLabel("classNames");
            var boysData = parseJsonLabel("boysData");
            var girlsData = parseJsonLabel("girlsData");

            applyRupeeFormatting();

            renderSingleBarChart(
                "student-chart",
                "Total Students",
                studentClasses,
                studentCounts,
                [["#60a5fa", "#2563eb"], ["#34d399", "#0f766e"], ["#c4b5fd", "#7c3aed"]]
            );

            renderSingleBarChart(
                "student-expensechart",
                "Monthly Expenses",
                expenseMonths,
                monthlyExpenses,
                [["#fda4af", "#ef4444"], ["#fdba74", "#ea580c"], ["#fcd34d", "#ca8a04"]]
            );

            renderSingleBarChart(
                "student-monthlyfeeschart",
                "Monthly Fees Collection",
                feeMonths,
                monthlyFees,
                [["#5eead4", "#14b8a6"], ["#93c5fd", "#2563eb"], ["#c4b5fd", "#8b5cf6"]],
                true
            );

            renderPieChart(
                "fees-paid-due-chart",
                readNumericText("totalPaidAmountDashBoard"),
                readNumericText("totalDueAmountDashBoard")
            );

            renderGroupedBarChart(
                "student-male-female",
                classNames,
                boysData,
                girlsData
            );

            window.addEventListener("resize", function () {
                chartInstances.forEach(function (instance) {
                    instance.resize();
                });
            });
        </script>
    </body>
</html>
