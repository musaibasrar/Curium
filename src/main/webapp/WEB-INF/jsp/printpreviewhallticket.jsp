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

    <style type="text/css">

        /* =========================================================
           GENERAL STYLES
           ========================================================= */

        .headerText {
            width: 10px;
            font-family: "Times New Roman", Times, Tahoma;
            font-size: 12px;
            color: #FFFFFF;
            font-weight: normal;
            width: auto;
            height: 22px;
            vertical-align: middle;
            text-align: center;
        }

        .headerTextLeft {
            width: 10px;
            font-family: "Times New Roman", Times, Tahoma;
            font-size: 12px;
            color: #FFFFFF;
            font-weight: normal;
            width: auto;
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


        /* =========================================================
           PAGE
           ========================================================= */

        @page {
            size: A4;
            margin: 10mm;
        }


        /* =========================================================
           SUBJECT / EXAM TABLE
           ========================================================= */

        .subjectdetails {
            border: 1px solid #000;
            text-align: center;
            padding: 5px;
            font-size: 10px;
            vertical-align: middle;
            box-sizing: border-box;
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

        .datatd,
        .datath {
            border: 1px solid #000000;
            text-align: center;
            padding: 8px;
        }


        /* =========================================================
           HALL TICKET CONTAINER
           ========================================================= */

        .ticket-container {
            display: block;
            width: 100%;
            margin: 0;
            padding: 0;
        }

        .ticket {
            width: 100%;
            height: auto;
            box-sizing: border-box;
            padding: 5px;
            margin: 0 0 10px 0;
            page-break-inside: avoid;
        }

        .ticket-inner {
            width: 100%;
            height: auto;
            border: 1px solid black;
            box-sizing: border-box;
            padding: 8px;
            overflow: hidden;
        }


        /* =========================================================
           SCHOOL HEADER
           ========================================================= */

        .school-header {
            width: 100%;
            border-collapse: collapse;
        }

        .school-header-inner {
            border-collapse: collapse;
            margin: 0 auto;
        }

        .school-logo-cell {
            vertical-align: middle;
            text-align: right;
            padding: 2px;
        }

        .school-name-cell {
            vertical-align: middle;
            text-align: left;
            padding-left: 8px;
        }

        .school-logo {
            width: 45px;
            height: 45px;
            display: block;
        }

        .school-name {
            font-family: "Times New Roman", Times, serif;
            font-size: 20px;
            font-weight: bold;
            text-transform: uppercase;
            white-space: nowrap;
        }


        /* =========================================================
           HALL TICKET + STUDENT DETAILS
           ========================================================= */

        .student-header-table {
            width: 100%;
            border-collapse: collapse;
            font-family: "Times New Roman", Times, serif;
        }

        .hall-title-cell {
            text-align: center;
            vertical-align: middle;
            padding: 5px;
            font-size: 13px;
            font-weight: bold;
        }

        .hall-title-main {
            font-size: 14px;
            font-weight: bold;
            line-height: 17px;
        }

        .hall-title-exam {
            font-size: 13px;
            font-weight: bold;
            line-height: 16px;
        }

        .student-details-cell {
            vertical-align: middle;
            padding: 3px;
        }

        .student-details-inner {
            width: 100%;
            border-collapse: collapse;
            font-family: "Times New Roman", Times, serif;
            font-size: 10px;
        }

        .student-details-inner td {
            padding: 3px;
            text-align: left;
            vertical-align: middle;
        }

        .student-photo-cell {
            width: 75px;
            text-align: center;
            vertical-align: middle;
            padding: 3px;
        }

        .student-photo {
            width: 60px;
            height: 70px;
            object-fit: cover;
            border: 1px solid #000;
            display: block;
            margin: auto;
        }


        /* =========================================================
           EXAM TABLE
           ========================================================= */

        .exam-table {
            border-collapse: collapse;
            width: 100%;
            table-layout: fixed;
            margin-top: 3px;
        }

        .exam-table .subjectdetails {
            border: 1px solid #000;
            padding: 5px;
            text-align: center;
            vertical-align: middle;
            font-size: 10px;
            box-sizing: border-box;
            word-wrap: break-word;
        }

        .exam-table .date-row td {
            height: 45px;
        }

        .exam-table .subject-row td {
            height: 30px;
        }

        .exam-table .sign-row td {
            height: 40px;
        }


        /* =========================================================
           SIGNATURES
           ========================================================= */

        .signature-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        .signature-table td {
            font-family: "Times New Roman", Times, serif;
            font-weight: bold;
            font-size: 11px;
        }

        .signature-left {
            text-align: left;
        }

        .signature-center {
            text-align: center;
        }

        .signature-right {
            text-align: right;
        }


        /* =========================================================
           PRINT SETTINGS
           ========================================================= */

        @media print {

            html,
            body {
                width: 100%;
                margin: 0;
                padding: 0;
            }

            .ticket-container {
                width: 100%;
                display: block;
                margin: 0;
                padding: 0;
            }

            .ticket {
                width: 100%;
                height: auto;
                padding: 5px;
                margin: 0 0 10px 0;
                box-sizing: border-box;
                page-break-inside: avoid;
            }

            .ticket-inner {
                width: 100%;
                height: auto;
                box-sizing: border-box;
                overflow: hidden;
            }

            .page-break {
                page-break-after: always;
            }

        }

    </style>


    <script type="text/javascript">

        window.onload = function () {
            window.print();
        };

    </script>


    <title></title>

</head>


<%
    // =========================================================
    // ALLOW ACCESS ONLY IF SESSION EXISTS
    // =========================================================

    String user = null;

    if (session.getAttribute("userAuth") == null) {

        response.sendRedirect("/iqraschool/UserProcess/sessionTimeOut");

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


<body style="text-align: center" class="bodymargin">

    <jsp:useBean id="now"
                 class="java.util.Date"
                 scope="page" />


    <form method="post"
          class="bodymargin">


        <!-- =====================================================
             HALL TICKET CONTAINER
             ===================================================== -->

        <div class="ticket-container">


            <!-- =================================================
                 LOOP THROUGH STUDENTS
                 ================================================= -->

            <c:forEach items="${studentList}"
                       var="Parents"
                       varStatus="status">


                <!-- =================================================
                     SINGLE HALL TICKET
                     ================================================= -->

                <div class="ticket">


                    <div class="ticket-inner">


                        <!-- =================================================
                             SCHOOL HEADER
                             LOGO + SCHOOL NAME SIDE BY SIDE
                             CENTERED AS A GROUP
                             ================================================= -->

                        <table class="school-header">

                            <tr>

                                <td style="
                                    text-align:center;
                                    vertical-align:middle;
                                ">


                                    <table class="school-header-inner">

                                        <tr>


                                            <!-- SCHOOL LOGO -->

                                            <td class="school-logo-cell">

                                                <img
                                                    src="/iqraschool/images/iqraschool.jpg"
                                                    class="school-logo"
                                                />

                                            </td>


                                            <!-- SCHOOL NAME -->

                                            <td class="school-name-cell">

                                                <div class="school-name">

                                                    ${branchname}

                                                </div>

                                            </td>


                                        </tr>

                                    </table>


                                </td>

                            </tr>

                        </table>


                        <!-- HEADER SEPARATOR -->

                        <hr style="
                            margin:4px 0;
                            border:0;
                            border-top:1px solid #000;
                        ">


                        <!-- =================================================
                             HALL TICKET TITLE + STUDENT DETAILS + PHOTO

                             SAME TABLE IS USED SO THAT THE PHOTO CAN
                             SPAN BOTH ROWS USING rowspan="2"
                             ================================================= -->

                        <table class="student-header-table">


                            <!-- =================================================
                                 ROW 1
                                 HALL TICKET TITLE + PHOTO
                                 ================================================= -->

                            <tr>


                                <!-- HALL TICKET TITLE -->

                                <td class="hall-title-cell">

                                    <div class="hall-title-main">

                                        Hall Ticket

                                    </div>


                                    <div class="hall-title-exam">

                                        ${examname}

                                    </div>

                                </td>


                                <!-- =================================================
                                     STUDENT PHOTO

                                     rowspan="2" means the photo occupies
                                     the Hall Ticket row AND student details row.
                                     ================================================= -->

                                <td rowspan="2"
                                    class="student-photo-cell">


                                    <c:choose>

                                        <c:when test="${not empty Parents.student.studentpic}">

                                            <img
                                                src="data:image;base64,<c:out value='${Parents.student.studentpic}'/>"
                                                class="student-photo"
                                            />

                                        </c:when>


                                        <c:otherwise>

                                            <div style="
                                                width:60px;
                                                height:70px;
                                                border:1px solid #000;
                                                margin:auto;
                                                text-align:center;
                                                font-size:8px;
                                                display:flex;
                                                align-items:center;
                                                justify-content:center;
                                            ">
                                                Photo
                                            </div>

                                        </c:otherwise>

                                    </c:choose>


                                </td>


                            </tr>


                            <!-- =================================================
                                 ROW 2
                                 STUDENT DETAILS
                                 ================================================= -->

                            <tr>


                                <td class="student-details-cell">


                                    <table class="student-details-inner">


                                        <!-- =====================================
                                             STUDENT NAME + CLASS
                                             ===================================== -->

                                        <tr>


                                            <td style="width:60%;">

                                                Student Name:

                                                <b>
                                                    ${Parents.student.name}
                                                </b>

                                            </td>


                                            <td style="width:40%;">

                                                Class:

                                                <b>

                                                    <c:forEach
                                                        var="splt"
                                                        items="${fn:split(Parents.student.classstudying,'--')}">

                                                        ${splt}

                                                    </c:forEach>

                                                </b>

                                            </td>


                                        </tr>


                                        <!-- =====================================
                                             FATHER NAME + ADMISSION NUMBER
                                             ===================================== -->

                                        <tr>


                                            <td>

                                                Father's Name:

                                                <b>
                                                    ${Parents.fathersname}
                                                </b>

                                            </td>


                                            <td>

                                                Admission No:

                                                <b>
                                                    ${Parents.student.admissionnumber}
                                                </b>

                                            </td>


                                        </tr>


                                    </table>


                                </td>


                            </tr>


                        </table>


                        <!-- =================================================
                             EXAM SCHEDULE TABLE
                             ================================================= -->

                        <table class="exam-table"
                               border="1">


                            <tbody>


                                <!-- =========================================
                                     DATE & DAY
                                     ========================================= -->

                                <tr class="date-row">


                                    <td class="subjectdetails"
                                        style="font-weight:bold;">

                                        Date &amp; Day

                                    </td>


                                    <c:forEach
                                        items="${examschedulelist}"
                                        var="exam">


                                        <td class="subjectdetails" style="font-size:8px;">


                                            <fmt:formatDate
                                                value="${exam.date}"
                                                pattern="dd-MM-yyyy"/>


                                            <br/>


                                            <fmt:formatDate
                                                value="${exam.date}"
                                                pattern="EEEE"/>


                                            <br/>


                                            <c:out
                                                value="${exam.starttime}"/>&nbsp;-&nbsp;<c:out value="${exam.endtime}"/>


                                        </td>


                                    </c:forEach>


                                </tr>


                                <!-- =========================================
                                     SUBJECT
                                     ========================================= -->

                                <tr class="subject-row">


                                    <td class="subjectdetails"
                                        style="font-weight:bold;">

                                        Subject

                                    </td>


                                    <c:forEach
                                        items="${examschedulelist}"
                                        var="exam">


                                        <td class="subjectdetails"
                                            style="font-weight:bold;">

                                            <c:out
                                                value="${exam.subject}"/>

                                        </td>


                                    </c:forEach>


                                </tr>


                                <!-- =========================================
                                     INVIGILATOR SIGN
                                     ========================================= -->

                                <tr class="sign-row">


                                    <td class="subjectdetails"
                                        style="font-weight:bold;">

                                        Invigilator Sign

                                    </td>


                                    <c:forEach
                                        items="${examschedulelist}"
                                        var="exam">


                                        <td class="subjectdetails">

                                            &nbsp;

                                        </td>


                                    </c:forEach>


                                </tr>


                            </tbody>


                        </table>


                        <!-- =================================================
                             SIGNATURE SECTION
                             ================================================= -->
			<br><br>
                        <table class="signature-table">


                            <tr>


                                <td class="signature-left">

                                    Accountant

                                </td>


                                <td class="signature-center">

                                    Class Teacher

                                </td>


                                <td class="signature-right">

                                    Principal

                                </td>


                            </tr>


                        </table>


                    </div>


                </div>


                <!-- =================================================
                     PAGE BREAK AFTER EVERY 4 STUDENTS
                     ================================================= -->

                <c:if test="${(status.index + 1) % 4 == 0}">

                    <div class="page-break"></div>

                </c:if>


            </c:forEach>


        </div>


    </form>


</body>

</html>
