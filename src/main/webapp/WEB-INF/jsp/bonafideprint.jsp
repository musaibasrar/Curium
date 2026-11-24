<%-- 
    Document   : bonafide certificate
    Created on : Mar 17 2018, 12:32 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bonafide Certificate</title>

    <style type="text/css">
        /* Base Reset & Styling */
        * {
            box-sizing: border-box;
        }

        body {
            font-family: 'Times New Roman', Times, Georgia, serif;
            color: #111;
            background-color: #fff;
            margin: 0;
            padding: 0;
            line-height: 1.6;
        }

        /* Certificate Container: Leaves balanced margins on left and right */
        .certificate-sheet {
            width: 86%;
            max-width: 800px;
            margin: 30px auto;
            padding: 20px 40px;
            box-sizing: border-box;
        }

        /* Certificate Title */
        .cert-header {
            text-align: center;
            margin-top: 40px;
            margin-bottom: 35px;
        }

        .cert-title {
            font-size: 24px;
            font-weight: bold;
            letter-spacing: 2px;
            text-transform: uppercase;
            text-decoration: underline;
            margin: 0;
        }

        /* Meta Information (Ref No & Date) */
        .meta-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            font-size: 15px;
        }

        /* Certificate Body Paragraphs */
        .cert-body {
            font-size: 17px;
            line-height: 2.6;
            text-align: justify;
            margin-bottom: 40px;
        }

        /* Fill-in-the-blank style underlines */
        .blank-field {
            display: inline-block;
            border-bottom: 1.5px solid #000;
            text-align: center;
            font-weight: bold;
            padding: 0 10px;
            line-height: 1.2;
            vertical-align: baseline;
        }

        .w-name { min-width: 320px; text-transform: capitalize; }
        .w-father { min-width: 280px; text-transform: capitalize; }
        .w-class { min-width: 100px; }
        .w-year { min-width: 120px; }
        .w-dob { min-width: 140px; }
        .w-detail { min-width: 110px; text-transform: capitalize; }

        /* Secondary Details Row (Religion, Caste, GR No.) */
        .details-grid {
            margin-top: 15px;
            display: block;
            line-height: 2.4;
        }

        /* Signature Section */
        .cert-footer {
            margin-top: 80px;
            display: flex;
            justify-content: space-between;
            align-items: flex-end;
            font-size: 16px;
            font-weight: bold;
        }

        .footer-item {
            text-align: center;
            min-width: 160px;
        }

        .signature-line {
            border-top: 1.5px solid #000;
            margin-bottom: 5px;
            padding-top: 4px;
        }

        /* Print Settings */
        @page {
            size: A4 portrait;
            margin: 15mm 20mm 15mm 20mm; /* Symmetrical page padding for print */
        }

        @media print {
            body {
                background: none;
            }
            .certificate-sheet {
                width: 100%;
                max-width: 100%;
                margin: 0 auto;
                padding: 0;
            }
        }
    </style>

    <script type="text/javascript">
        window.onload = function() {
            window.print();
        };
    </script>
</head>

<%
    // Restrict access if session has expired
    if (session.getAttribute("userAuth") == null) {
        response.sendRedirect("/alirfan/UserProcess/sessionTimeOut");
        return;
    }
%>

<body>
<jsp:useBean id="now" class="java.util.Date" scope="page" />
<br><br><br><br><br>
<div class="certificate-sheet">

    <!-- Top Spacing / Optional Reference & Date Row -->
    <div class="meta-row">
        <div>
            Ref. No.: <span class="blank-field" style="min-width: 140px;">&nbsp;</span>
        </div>
        <div>
            Date: 
            <span class="blank-field" style="min-width: 110px;">
                <fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>
            </span>
        </div>
    </div>

    <!-- Certificate Heading -->
    <div class="cert-header">
        <h1 class="cert-title">Bonafide Certificate</h1>
    </div>

    <!-- Main Certificate Content -->
    <div class="cert-body">
        This is to certify that Master/Kumari&nbsp;
        <span class="blank-field w-name">
            <c:out value="${studentdetailsbonafide.student.name}" />
        </span>, 
        Son  of Mr.&nbsp;
        <span class="blank-field w-father">
            <c:out value="${studentdetailsbonafide.fathersname}" />
        </span>, 
        is a bonafide student of this institution, studying in class&nbsp;
        <span class="blank-field w-class">
            <c:forEach var="splt" items="${fn:split(studentdetailsbonafide.student.classstudying,'--')}">
                ${splt}&nbsp;
            </c:forEach>
        </span>
        during the academic year&nbsp;
        <span class="blank-field w-year">
            <c:out value="${currentAcademicYear}" />
        </span>.
        
        <br>

        As per the school general register records, his/her date of birth is&nbsp;
        <span class="blank-field w-dob">
            <fmt:formatDate value="${studentdetailsbonafide.student.dateofbirth}" pattern="dd/MM/yyyy"/>
        </span>.

        <!-- Supplementary Student Details -->
        <div class="details-grid">
            Religion:&nbsp;
            <span class="blank-field w-detail">
                <c:out value="${studentdetailsbonafide.student.religion}" />
            </span>&nbsp;&nbsp;&nbsp;&nbsp;
            
            Caste:&nbsp;
            <span class="blank-field w-detail">
                <c:out value="${studentdetailsbonafide.student.caste}" />
            </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            
            Sub-Caste:&nbsp;
            <span class="blank-field w-detail">&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            
            G.R. No.:&nbsp;
            <span class="blank-field w-detail">
                <c:out value="${studentdetailsbonafide.student.sts}" />
            </span>
        </div>
    </div>

    <!-- Signatures and Stamp Footer 
    <div class="cert-footer">
        <div class="footer-item">
            <div class="signature-line">Prepared By</div>
        </div>
        <div class="footer-item">
            <div class="signature-line">Verified By</div>
        </div>
        <div class="footer-item">
            <div class="signature-line">Principal / Headmaster</div>
            <div>(Seal & Signature)</div>
        </div>
    </div>-->

</div>

</body>
</html>