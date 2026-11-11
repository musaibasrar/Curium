<%-- 
    Document   : Print Fee Dues Payment Slip
    Created on : Nov 11 2026, 7:10 PM
    Author     : Musaib
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Print Fee Dues Reminder</title>
<style type="text/css">
    body {
        font-family: Arial, Helvetica, sans-serif;
        margin: 0;
        padding: 20px;
        background-color: #ffffff;
        color: #111111;
    }

    .reminder-card {
        border: 1.5px solid #4a677d;
        border-radius: 18px;
        padding: 28px 45px;
        max-width: 780px;
        margin: 0 auto 25px auto;
        box-sizing: border-box;
        page-break-inside: avoid;
    }

    .school-title {
        text-align: center;
        color: #1a3a54;
        font-size: 24px;
        font-weight: bold;
        margin: 0 0 6px 0;
        letter-spacing: 0.5px;
        text-transform: uppercase;
    }

    .school-subtitle {
        text-align: center;
        font-size: 14px;
        color: #222222;
        margin: 0 0 24px 0;
        text-transform: uppercase;
    }

    .notice-heading {
        text-align: center;
        font-size: 17px;
        font-weight: bold;
        color: #111111;
        margin: 0 0 26px 0;
        text-transform: uppercase;
    }

    .field-row {
        display: flex;
        justify-content: space-between;
        align-items: baseline;
        margin-bottom: 18px;
        font-size: 15px;
    }

    .field-group {
        display: flex;
        align-items: baseline;
    }

    .field-label {
        font-weight: bold;
        margin-right: 6px;
        white-space: nowrap;
    }

    .underline-value {
        border-bottom: 1px solid #222222;
        display: inline-block;
        padding: 0 8px 2px 8px;
        font-weight: bold;
        text-align: left;
    }

    .w-name {
        min-width: 340px;
    }

    .w-class {
        min-width: 110px;
    }

    .w-fee {
        min-width: 170px;
    }

    .w-date {
        min-width: 120px;
        text-align: center;
    }

    .salutation {
        font-size: 15px;
        margin: 24px 0 12px 0;
    }

    .body-copy {
        font-size: 15px;
        line-height: 1.55;
        margin: 0 0 20px 0;
    }

    .important-warning {
        text-align: center;
        color: #9e1b1b;
        font-weight: bold;
        font-size: 14.5px;
        line-height: 1.5;
        margin: 20px 0 28px 0;
    }

    .card-footer {
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-weight: bold;
        font-size: 14px;
        padding-right: 40px;
    }

    @media print {
        body {
            padding: 0;
            margin: 0;
        }
        @page {
            margin: 1cm;
            size: auto;
        }
        .reminder-card {
            margin-bottom: 18px;
        }
    }
</style>

<script type="text/javascript">
    window.onload = function() {
        window.print();
    };
</script>
</head>

<body>
<fmt:setLocale value="en_IN" scope="session"/>
<%-- Instantiate current date for Notice Date --%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="currentDateStr" value="${now}" pattern="dd / MM / yyyy" />

<%-- Safely handle deadline whether it is a java.util.Date or a String --%>
<c:catch var="dateFormatError">
    <fmt:formatDate var="formattedDeadline" value="${deadline}" pattern="dd / MM / yyyy" />
</c:catch>
<c:if test="${not empty dateFormatError or empty formattedDeadline}">
    <c:set var="formattedDeadline" value="${deadline}" />
</c:if>

<form method="post">

    <c:forEach items="${studentfeesreportlist}" var="studentfeesreportlist" varStatus="status">
        
        <%-- Calculate Student Due Amount from Fees Structure --%>
        <c:set var="DueAmount" value="0" />
        <c:forEach items="${studentfeesreportlist.studentFeesStructure}" var="studentfeescatagorydetails">
            <c:set var="DueAmount" value="${DueAmount + studentfeescatagorydetails.feesamount - studentfeescatagorydetails.feespaid - studentfeescatagorydetails.concession - studentfeescatagorydetails.waiveoff}" />
        </c:forEach>

        <%-- Only print reminder cards for students with pending dues --%>
        <c:if test="${DueAmount > 0}">
            <div class="reminder-card">
                <div class="school-title">
                    <c:out value="${branchname}" />
                </div>
                <div class="school-subtitle">${branchaddress}</div>

                <div class="notice-heading">FEE DUES &ndash; PAYMENT REMINDER</div>

                <!-- Row 1: Student's Name & Class -->
                <div class="field-row">
                    <div class="field-group">
                        <span class="field-label">Student's Name:</span>
                        <span class="underline-value w-name">
                            <c:out value="${studentfeesreportlist.parents.student.name}" />
                        </span>
                    </div>
                    <div class="field-group">
                        <span class="field-label">Class:</span>
                        <span class="underline-value w-class">
                            <c:out value="${fn:replace(studentfeesreportlist.parents.student.classstudying, '--', ' ')}" />
                        </span>
                    </div>
                </div>

                <!-- Row 2: Fee Due & Notice Date (Current Date) -->
                <div class="field-row">
                    <div class="field-group">
                        <span class="field-label">Fee Due:</span>
                        <span>Rs.&nbsp;</span>
                        <span class="underline-value w-fee">
                            <fmt:formatNumber type="number" maxFractionDigits="0" value="${DueAmount}" />
                        </span>
                    </div>
                    <div class="field-group">
                        <span class="field-label">Notice Date:</span>
                        <span class="underline-value w-date">
                            <c:out value="${currentDateStr}" />
                        </span>
                    </div>
                </div>

                <!-- Row 3: Last Date for Payment (Request Attribute: deadline) -->
                <div class="field-row">
                    <div class="field-group">
                        <span class="field-label">Last Date for Payment:</span>
                        <span class="underline-value w-date">
                            <c:out value="${formattedDeadline}" />
                        </span>
                    </div>
                </div>

                <div class="salutation">Dear Parent/Guardian,</div>

                <div class="body-copy">
                    This is to inform you that the above-mentioned fee amount is pending. Kindly clear the dues on or before the payment last date mentioned above to avoid any inconvenience.
                </div>

                <div class="important-warning">
                    IMPORTANT: If the pending fee is not paid by the given date, your child will not be permitted to attend<br>
                    classes until the dues are cleared.
                </div>

                <div class="card-footer">
                    <span>For any clarification, please contact the school office.</span>
                    <span>HEADMASTER</span>
                </div>
            </div>
        </c:if>

    </c:forEach>

</form>
</body>
</html>                