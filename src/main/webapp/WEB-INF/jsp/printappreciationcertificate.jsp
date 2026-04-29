<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Certificates</title>
    <link href="https://fonts.googleapis.com/css2?family=Great+Vibes&family=Cinzel:wght@700&family=Pinyon+Script&display=swap" rel="stylesheet">

    <style>
        @page {
            size: A4 landscape;
            margin: 0;
        }

        body {
            margin: 0;
            padding: 0;
            background: #eee;
            font-family: "Times New Roman", serif;
            /* Forces background colors and images to print in most browsers */
            -webkit-print-color-adjust: exact !important;
            print-color-adjust: exact !important;
        }

        .page-break {
            page-break-after: always;
        }

        .certificate {
            width: 297mm;
            height: 210mm;
            margin: 10px auto;
            background-color: white;
            /* Ensure the path to background.png is correct for your server */
            background-image: url('/readmodelschool/images/background.png');
            background-size: contain;
            background-position: center;
            background-repeat: no-repeat;
            position: relative;
            box-sizing: border-box;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
        }

        /* This wrapper shrinks all text and logos to 65-70% 
           to ensure they stay inside the white oval 
        */
        .sign-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 250px;
            margin: 0 auto;
        }
        
        .content-scale-wrapper {
            width: 100%;
            height: 100%;
            transform: scale(0.65); 
            transform-origin: top center;
            margin-top: 200px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            box-sizing: border-box;
            padding: 20px;
        }

        .header-section {
            text-align: center;
            margin-bottom: 1px;
        }

        .logo-img {
            height: 120px;
            width: 120px;
            margin-bottom: 5px;
        }

        .school-name {
            font-size: 55px;
            font-weight: bold;
            color: #b30000;
            font-family: 'Cinzel', serif;
        }

        .years-badge {
            font-size: 22px;
            color: #b30000;
            font-weight: bold;
        }

        .title-section {
            text-align: center;
            margin: 10px 0;
        }

        .movement-text {
            font-size: 24px;
            color: #2f6f2f;
            letter-spacing: 3px;
            text-transform: uppercase;
        }

        .main-title {
            font-family: 'Pinyon Script', cursive;
            font-size: 90px;
            color: #9b1c1c;
            margin: 10px 0;
        }

        .main-content {
            font-size: 28px;
            line-height: 1.5;
            text-align: center;
            padding: 0 40px;
        }

        .line {
            display: inline-block;
            min-width: 350px;
            border-bottom: 2px solid #000;
            font-family: 'Great Vibes', cursive;
            font-size: 45px;
            color: #333;
            padding: 0 15px;
        }

        .lineclass {
            display: inline-block;
            min-width: 150px;
            border-bottom: 2px solid #000;
            font-weight: normal;
            font-size: 30px;
            padding: 0 10px;
        }

        .footer-table {
            width: 100%;
            margin-top: 80px;
        }

        .sign-box {
            border-top: 2px solid #000;
            width: 250px;
            margin: 0 auto;
            padding-top: 5px;
            font-weight: bold;
            text-align: center;
            font-size: 24px;
        }

        .print-btn {
            text-align: center;
            padding: 20px;
        }

        @media print {
            body { background: none; }
            .print-btn { display: none; }
            .certificate { 
                margin: 0; 
                box-shadow: none;
                -webkit-print-color-adjust: exact;
            }
        }
    </style>
</head>
<body>

    <div class="print-btn">
        <button onclick="window.print()">Print All Certificates</button>
    </div>

    <c:forEach var="parent" items="${parentlist}">
        <div class="certificate page-break">
            <div class="content-scale-wrapper">

                <div class="header-section">
                    <img class="logo-img" src="/readmodelschool/images/readmodelschool.png" alt="Logo">
                    <div class="school-name">Read Model School</div>
                    <div class="years-badge">12 YEARS CELEBRATIONS <br> <small>Since 2014</small></div>
                </div>

                <div class="title-section">
                    <div class="movement-text">Movement for Real Education</div>
                    <h1 class="main-title">Certificate of Appreciation</h1>
                </div>

                <div class="main-content">
                    Awarded to <span class="line">${parent.student.name}</span><br>
                    S/O / D/O <span class="line">${parent.fathersname}</span>
                    &nbsp;&nbsp; Student of <span class="lineclass">
                        <c:forEach var="splts" items="${fn:split(parent.student.classstudying,'--')}">
                            ${splts} 
                        </c:forEach>
                    </span><br><br>

                    Has presented his / her excellent performance in<br>
                    <span style="font-size:35px; font-weight:bold; color: #b30000;">
                        Annual Day
                    </span><br>
                    May Almighty Allah increase his/her talents and skills for beneficial career.
                </div>

                <table class="footer-table">
                    <tr>
                        <td width="33%">
                            <div class="sign-container">
                                <img src="/readmodelschool/images/president.png" width="80" height="45" class="sign-img"/>
                                <div class="sign-box">President</div>
                            </div>
                        </td>
                        <td width="33%" style="text-align: center; font-size: 24px; vertical-align: bottom; padding-bottom: 5px;">
                            <b>Date:</b> 15-02-2026
                        </td>
                        <td width="33%">
                            <div class="sign-container">
                                <img src="/readmodelschool/images/hm.png" width="80" height="45" class="sign-img"/>
                                <div class="sign-box">H.M</div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </c:forEach>

</body>
</html>
