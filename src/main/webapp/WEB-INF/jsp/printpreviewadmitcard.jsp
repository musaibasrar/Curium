<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PT-I Admit Card (2025-26)</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            margin: 10px;
            padding: 0;
        }

        .page {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            page-break-after: always;
        }

        .admit-card {
            border: 2px solid black;
            width: 48%;
            margin-bottom: 10px;
            padding: 10px;
            height: 480px;
            position: relative;
        }

        .header {
            background-color: #007c8c;
            color: white;
            padding: 10px;
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .header img {
            width: 60px;
            height: auto;
        }

        .header-text {
            flex-grow: 1;
        }

        .header-text h1 {
            margin: 0;
            font-size: 18px;
            text-transform: uppercase;
        }

        .header-text p {
            margin: 5px 0 0;
            font-size: 12px;
        }

        .sub-header {
            background-color: #004a5e;
            color: white;
            text-align: center;
            padding: 6px;
            font-size: 10px;
            font-weight: bold;
        }

        .section {
            margin-top: 15px;
            font-size: 13px;
            display: grid;
            grid-template-columns: 110px auto;
            row-gap: 10px;
            column-gap: 10px;
        }

        .section label {
            font-weight: bold;
            text-align: left;
        }

        .value {
            border-bottom: 1px dashed #000;
            padding: 2px;
        }

        .instructions {
            margin-top: 15px;
            font-size: 12px;
        }

        .instructions h3 {
            text-align: center;
            text-decoration: underline;
            font-size: 14px;
        }

        .instructions ol {
            margin-left: 18px;
        }

        .signatures {
            margin-top: 25px;
            display: flex;
            justify-content: space-between;
            text-align: center;
        }

        .signatures div {
            width: 30%;
        }

        .footer-label {
            margin-top: 5px;
            font-weight: bold;
            font-size: 12px;
        }

        @media print {
            .page {
                page-break-after: always;
            }
        }
    </style>
    <script type="text/javascript">
        window.onload = function () {
            window.print();
        }
    </script>
</head>
<body>

<div class="page">
    <c:forEach items="${studentList}" var="Parents" varStatus="status">
        <div class="admit-card">
            <!-- Header -->
            <div class="header">
                <img src="/meps/images/meps.jpg" alt="School Logo">
                <div class="header-text">
                    <label style="font-size: 14px;">MT. EVEREST PUBLIC SCHOOL<label>
                    <p style="font-size: 9px;">Jawahar Road, Chitarpur &nbsp;&nbsp; <span style="font-size: 9px;">UDISE ID - 20241514802</span></p>
                </div>
            </div>

            <!-- Sub-header -->
            <div class="sub-header">${examname} ADMIT CARD (2025-26)</div>

            <!-- Student Info -->
            <div class="section">
                <label>NAME:</label>
                <span class="value">${Parents.student.name}</span>

                <label>FATHER'S NAME:</label>
                <span class="value">${Parents.fathersname}</span>

                <label>CLASS:</label>
                <span class="value">${Parents.student.classstudying}</span>

                <label>ROLL NO:</label>
                <span class="value">${Parents.student.bhagyalakshmibondnumber}</span>

                <label>ADM NO:</label>
                <span class="value">${Parents.student.admissionnumber}</span>
            </div>

            <!-- Instructions -->
            <div class="instructions">
                <h3>INSTRUCTIONS</h3>
                <ol>
                    <li><em>The exam will commence at 8:00 AM sharp.</em></li>
                    <li><em>The candidate will not be allowed to enter the examination hall without admit card.</em></li>
                </ol>
            </div>
			<br>
            <!-- Signatures -->
            <div class="signatures" style="padding-left: 40px;">
                <div>
                    <!-- <img src="/meps/images/clerk-sign.png" alt="Clerk Sign"> -->
                    <div class="footer-label">Clerk Sign</div>
                </div>
                <div>
                    <!-- <img src="/meps/images/exam-controller-sign.png" alt="Exam Controller Sign"> -->
                    <div class="footer-label">Exam Controller</div>
                </div>
                <div>
                    <!-- <img src="/meps/images/principal-sign.png" alt="Principal Sign"> -->
                    <div class="footer-label">Principal</div>
                </div>
            </div>
        </div>

        <!-- Start new page row every 4 cards -->
        <c:if test="${(status.index + 1) % 4 == 0}">
            </div><div class="page">
        </c:if>
    </c:forEach>
</div>

</body>
</html>
