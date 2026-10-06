<%-- 
    Document   : Print Hall Ticket
    Created on : Apr 04 2018, 04:32 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html moznomarginboxes>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
.headerText {
    width: auto;
    font-family: "Times New Roman", Times, Tahoma;
    font-size: 12px;
    color: #FFFFFF;
    font-weight: normal;
    height: 22px;
    vertical-align: middle;
    text-align: center;
}

.headerTextLeft {
    width: auto;
    font-family: "Times New Roman", Times, Tahoma;
    font-size: 12px;
    color: #FFFFFF;
    font-weight: normal;
    height: 22px;
    vertical-align: middle;
    text-align: left;
}

.dataTextBold {
    font-weight: bold;
    font-family: "Times New Roman", Times, Tahoma;
    color: black;
    font-size: 12px;
    letter-spacing: normal;
    text-align: center;
}

.dataTextBoldLeft {
    font-weight: normal;
    font-family: "Times New Roman", Times, Tahoma;
    color: black;
    font-size: 12px;
    letter-spacing: normal;
    text-align: left;
}

.dataTextBoldCenter {
    font-weight: bold;
    font-family: "Times New Roman", Times, Tahoma;
    color: black;
    font-size: 12px;
    letter-spacing: normal;
    text-align: center;
}

.addressLine {
    font-weight: normal;
    font-family: "Times New Roman", Times;
    color: black;
    font-size: 7px;
    letter-spacing: normal;
    text-align: center;
}

.dataText {
    font-family: "Times New Roman", Times, Tahoma;
    color: black;
    font-size: 12px;
    letter-spacing: normal;
    text-align: center;
}

span {
    display: inline-block;
    border-bottom: 2px solid black;
    padding-bottom: 1px;
    width: 300px;
    font-weight: normal;
}

@page {
    size: A4;
    margin: 10mm;
}

.subjectdetails {
    border: 1px solid black;
    text-align: center;
    padding: 8px;
    font-size: 12px;
}

.nosubjectdetails {
    border: 0px;
    text-align: left;
    padding: 8px;
    font-weight: normal;
}

.namedetails {
    border: 0px solid #dddddd;
    text-align: left;
    padding: 4px;
}

.namedetailscenter {
    border: 0px solid #dddddd;
    text-align: right;
    padding: 8px;
}

.datatable {
    font-family: "Times New Roman", Times, sans-serif;
    border-collapse: collapse;
    width: 100%;
    font-size: 8px;
}

.datatd, .datath {
    border: 1px solid #000000;
    text-align: center;
    padding: 8px;
}

.ticket-container {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
    position: relative;
}

.ticket {
    width: 50%;
    height: 50vh;
    box-sizing: border-box;
    padding: 12px;
}

.ticket-inner {
    border: 1px solid black;
    height: 100%;
    padding: 10px;
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
}

.signature-table {
    margin-top: auto;
    width: 100%;
    border-collapse: collapse;
}

.signature-col-left {
    font-weight: bold;
    font-size: 11px;
    padding-top: 35px;
    text-align: left;
    width: 33.33%;
}

.signature-col-center {
    font-weight: bold;
    font-size: 11px;
    padding-top: 35px;
    text-align: center;
    width: 33.33%;
}

.signature-col-right {
    font-weight: bold;
    font-size: 11px;
    padding-top: 35px;
    text-align: right;
    width: 33.33%;
}

@media print {
    body {
        margin: 0;
        padding: 0;
    }

    .fontsize {
        font-size: 15px;
        font-weight: bold;
        font-family: 'Times New Roman';
    }

    .header, .hide {
        visibility: hidden;
    }

    .bodymargin {
        margin: 0px;
    }

    .ticket {
        width: 50%;
        height: 50vh;
        padding: 10px;
    }

    .page-break {
        page-break-after: always;
    }
}

@media screen {
    .fontsize {
        font-size: 15px;
        font-weight: bold;
        font-family: 'Times New Roman';
    }

    .bodymargin {
        margin: 0px;
    }
}
</style>

<script type="text/javascript">
    window.onload = function(){
        window.print();
    }
</script>
<title>Print Hall Ticket</title>
</head>

<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
    response.sendRedirect("/salihath/UserProcess/sessionTimeOut");
} else {
    user = (String) session.getAttribute("userAuth");
}

String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies != null){
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("user")) userName = cookie.getValue();
        if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
    }
}
%>

<body style="text-align: center" class="bodymargin">
<jsp:useBean id="now" class="java.util.Date" scope="page" />
    <form method="post" class="bodymargin">
        <div class="ticket-container">

        <c:forEach items="${studentList}" var="Parents" varStatus="status">
            <div class="ticket">
                <div class="ticket-inner">

                    <!-- ===== HEADER ===== -->
                    <table width="100%" style="border-collapse: collapse;">
                        <tr>
                            <td width="35"><img src="/salihath/images/salihath.jpg" width="30" height="30"/></td>
                            <td align="center">
                                <label class="dataTextBoldCenter" style="text-transform: uppercase;">${branchname}</label><br>
				<label class="addressLine">${branchaddress}</label><br>
				<label class="addressLine">${branchcontact}</label>
                            </td>
                        </tr>
                    </table>

                    <hr style="margin-top: 2px; margin-bottom: 2px;">

                    <!-- ===== TITLE ===== -->
                    <table width="100%">
                        <tr>
                            <td width="30%"></td>
                            <td class="dataTextBoldCenter">
                                Hall Ticket<br><label style="text-transform: capitalize;">${examname}</label><br><label>${currentAcademicYear}</label>
                            </td>
                            <td align="right" width="30%">
                                <c:choose>
                                    <c:when test="${not empty Parents.student.studentpic}">
                                        <c:choose>
                                            <%-- Data URI --%>
                                            <c:when test="${fn:startsWith(Parents.student.studentpic, 'data:')}">
                                                <c:choose>
                                                    <%-- PDF Link --%>
                                                    <c:when test="${fn:startsWith(Parents.student.studentpic, 'data:application/pdf')}">
                                                        <a href="${Parents.student.studentpic}" target="_blank" rel="noopener noreferrer"> PDF </a>
                                                    </c:when>
                                                    <%-- Image --%>
                                                    <c:otherwise>
                                                        <img src="${Parents.student.studentpic}" alt="Student's Pic" style="width: 30px; height: 30px;">
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                            <%-- Raw Base64 string fallback --%>
                                            <c:otherwise>
                                                <img src="data:image/jpeg;base64,${Parents.student.studentpic}" alt="Student's Pic" style="width: 30px; height: 30px;">
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
					<!--  <div style="width: 100px; height: 120px; border: 1px dashed #888; display: flex; align-items: center; justify-content: center; font-size: 11px; margin: 0 auto;">
									Affix Photo
								</div> -->
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </table>

                    <!-- ===== STUDENT DETAILS ===== -->
                    <table width="100%">
                        <tr>
                            <td style="text-align: left; font-size: 10px;">Student Name: <b>${Parents.student.name}</b></td>
                            <td style="text-align: left; font-size: 10px;">
                                Class:
                                <c:forEach var="splt" items="${fn:split(Parents.student.classstudying,'--')}">
                                    ${splt}
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: left; font-size: 10px;">Father's Name: ${Parents.fathersname}</td>
                            <td style="text-align: left; font-size: 10px;">Admission No: ${Parents.student.admissionnumber}</td>
                        </tr>
                    </table>

                    <!-- ===== EXAM TABLE ===== -->
                    <table width="100%" class="datatable">
                        <thead>
                            <tr>
                                <th class="datath">Date</th>
                                <th class="datath">Day</th>
                                <th class="datath">Subject</th>
                                <th class="datath">Time</th>
                                <th class="datath">Sign</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${examschedulelist}" var="schedule">
                                <tr>
                                    <td class="datatd">
                                        <fmt:formatDate value="${schedule.date}" pattern="dd/MM/yyyy"/>
                                    </td>
                                    <td class="datatd">
                                        <fmt:formatDate value="${schedule.date}" pattern="E"/>
                                    </td>
                                    <td class="datatd">${schedule.subject}</td>
                                    <td class="datatd">${schedule.starttime} - ${schedule.endtime}</td>
                                    <td class="datatd"></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <!-- ===== SIGNATURES (STICK TO BOTTOM) ===== -->
                    <table class="signature-table">
                        <tr>
                            <td class="signature-col-left"></td>
                            <td class="signature-col-center"></td>
                            <td class="signature-col-right">Principal</td>
                        </tr>
                    </table>

                </div>
            </div>

            <!-- Page break after every 4 tickets -->
            <c:if test="${(status.index + 1) % 4 == 0}">
                <div class="page-break"></div>
            </c:if>
        </c:forEach>

        </div>
    </form>
</body>
</html>
