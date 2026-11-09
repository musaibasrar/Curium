<%-- 
Document   : left
Created on : Jan 4, 2012, 3:41:11 PM
Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Left</title>
        <script language="JavaScript" src="/vision/js/motionpack.js"></script>
        <link rel="stylesheet" href="/vision/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/vision/css/datePicker/demos.css">
		
        <script type="text/javascript" src="/vision/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/vision/js/datePicker/ui/jquery.ui.core.js"></script>
        <script src="/vision/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script src="/vision/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script src="/vision/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script src="/vision/js/datePicker/ui/sliderAccess.js"></script>
        <script src="/vision/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <link href="/vision/css/notification/jquery.jnotify.css" rel="stylesheet" type="text/css" />
        <script src="/vision/js/notification/jquery.jnotify.js" type="text/javascript"></script>
		<link rel="stylesheet" href="/vision/css/font-awesome.min.css">
        
        <script type="text/javascript">
            var get;
            function getdata1() {
                var startHour, startMin;
                var tDate = new Date();
                startHour = tDate.getHours();
                startMin = tDate.getMinutes();

                if (typeof XMLHttpRequest != "undefined") {
                    get = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    get = new ActiveXObject("Microsoft.XMLHTTP");
                }

                get.onreadystatechange = processdata;
                get.open("POST", "/VisitProcess/getAJaxNextVisit?startHour=" + startHour + "&startMin=" + startMin, true);
                get.send(null);
            }

            function processdata() {
                var id, hourID, patientID, reminderTime, visitTime, rating, name, complaint, link;
                if (get.readyState == 4)
                {
                    if (get.status == 200) {
                        var visits = get.responseXML.getElementsByTagName("Visits")[0];
                        var visitNodes = visits.getElementsByTagName("Visit");
                        for (var i = 0; i < visitNodes.length; i++) {
                            var visit = visitNodes[i];

                            patientID = visit.getElementsByTagName("PatientID")[0].firstChild.nodeValue;
                            visitTime = visit.getElementsByTagName("VisitTime")[0].firstChild.nodeValue;
                            name = visit.getElementsByTagName("PatientName")[0].firstChild.nodeValue;
                            link = "<a target='mainFrame' href='/PatientProcess/viewDetails?id=" + patientID + "'>" + name + "   " + visitTime + "</a>";

                            $(function() {
                                $('#Notification').jnotifyAddMessage({
                                    text: link,
                                    permanent: false,
                                    disappearTime: 30000
                                });
                            });
                        }
                        setTimeout('getdata1();', 60000);
                    }
                }
            }
            
            var getExpiringStockCount;
            function getExpiringStock() {
                if (typeof XMLHttpRequest != "undefined") {
                    getExpiringStockCount = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    getExpiringStockCount = new ActiveXObject("Microsoft.XMLHTTP");
                }

                getExpiringStockCount.onreadystatechange = processExpiringStockData;
                getExpiringStockCount.open("POST", "/StockProcess/getExpiringStock", true);
                getExpiringStockCount.send(null);
            }
           
            var getDepletingStockCount;
            function getDepletingStock() {
                if (typeof XMLHttpRequest != "undefined") {
                    getDepletingStockCount = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    getDepletingStockCount = new ActiveXObject("Microsoft.XMLHTTP");
                }

                getDepletingStockCount.onreadystatechange = processDepletingStockData;
                getDepletingStockCount.open("POST", "/StockProcess/getDepletingStock", true);
                getDepletingStockCount.send(null);
            }
            
            function processDepletingStockData() {
                if (getDepletingStockCount.readyState == 4)
                {
                    if (getDepletingStockCount.status == 200) {
                        var count = getDepletingStockCount.responseXML.getElementsByTagName("DepletingStockCount")[0];
                        var depletingStockCount = count.childNodes[0].nodeValue;
                        var depletingStock = document.getElementById("depletingStock");
                        depletingStock.innerHTML = " " + depletingStockCount;
                        setTimeout('getDepletingStock();', 60000);
                    }
                }
            }
        </script>
        
        <script type="text/javascript">
            var req;
            function count() {
                var idField = document.getElementById("userid");
                var url = "AppointmentController";
                if (typeof XMLHttpRequest != "undefined") {
                    req = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    req = new ActiveXObject("Microsoft.XMLHTTP");
                }
                reload(req);
            }
            
            function handleRequest() {
                if (req.readyState == 4)
                {
                    if (req.status == 200) {
                        var count = req.responseXML.getElementsByTagName("count")[0];
                        var childCount = count.childNodes[0].nodeValue;
                        var mdiv = document.getElementById("n1");
                        mdiv.innerHTML = childCount;
                        mdiv.style.visible = 'block';
                    }
                }
                setTimeout(function() {
                    reload(req);
                }, 100);
            }
        </script>

        <!-- MODERN UI CSS: CUSTOM COLOR #143160 & COMPACT SIDEBAR -->
        <style>
            * {
                box-sizing: border-box;
            }

            body {
                margin: 0;
                padding: 8px 6px;
                background: #ffffff;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                color: #143160;
                overflow-x: hidden;
            }

            .headerTD {
                background-color: #143160;
                color: #ffffff;
                border-radius: 6px;
                font-size: 13px;
                text-transform: uppercase;
                text-align: center;
                font-weight: 700;
                padding: 9px 8px;
                margin-bottom: 8px;
                letter-spacing: 0.4px;
                box-shadow: 0 2px 4px rgba(20, 49, 96, 0.2);
            }

            /* Strip all default jQuery UI borders, backgrounds, and left icons */
            .ui-accordion .ui-accordion-header .ui-icon {
                display: none !important;
            }

            .ui-widget-content,
            .ui-state-default,
            .ui-widget-content .ui-state-default,
            .ui-widget-header .ui-state-default {
                border: none !important;
                background: transparent !important;
            }

            /* Compact Main Feature Headers */
            .ui-accordion .ui-accordion-header.sideaccordian {
                margin: 0 0 1px 0 !important;
                padding: 0 !important;
                border-radius: 5px !important;
                border-left: 3px solid transparent !important;
                outline: none !important;
                position: relative;
                transition: background-color 0.15s ease, border-color 0.15s ease !important;
            }

            .ui-accordion .ui-accordion-header.sideaccordian a {
                display: block !important;
                padding: 6px 20px 6px 7px !important;
                margin: 0 !important;
                font-size: 13px !important;
                font-weight: 500 !important;
                line-height: 1.3 !important;
                color: #143160 !important;
                text-decoration: none !important;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }

            /* Tight gap between icon and feature name */
            .sideaccordian a i {
                display: inline-block;
                width: 14px;
                margin-right: 5px;
                text-align: center;
                color: #143160;
                font-size: 13px;
                font-weight: normal;
            }

            /* Hover & Active States in #143160 */
            .ui-accordion .ui-accordion-header.sideaccordian:hover,
            .ui-accordion .ui-accordion-header.ui-state-active {
                background-color: #eff6ff !important;
                border-left: 3px solid #143160 !important;
            }

            .ui-accordion .ui-accordion-header.sideaccordian:hover a,
            .ui-accordion .ui-accordion-header.ui-state-active a {
                color: #143160 !important;
                font-weight: 600 !important;
            }

            .ui-accordion .ui-accordion-header.sideaccordian:hover a i,
            .ui-accordion .ui-accordion-header.ui-state-active a i {
                color: #143160 !important;
            }

            /* Right Arrow Default -> Down Arrow on Select */
            .sideaccordian a::after {
                content: "\f105"; /* fa-angle-right */
                font-family: 'FontAwesome';
                position: absolute;
                right: 7px;
                top: 50%;
                transform: translateY(-50%);
                font-size: 13px;
                color: #64748b;
            }

            .sideaccordian:hover a::after {
                color: #143160;
            }

            .ui-state-active a::after {
                content: "\f107"; /* fa-angle-down */
                color: #143160 !important;
            }

            /* Sub-options Container & Single-line Small Font Links */
            .ui-accordion .ui-accordion-content {
                margin: 0 0 2px 13px !important;
                padding: 2px 4px 3px 8px !important;
                border-left: 1.5px solid #bfdbfe !important;
                overflow: hidden !important;
            }

            .menu-link {
                display: block;
                width: 100%;
                padding: 4px 6px;
                color: #334155;
                text-decoration: none !important;
                font-size: 11.5px;
                line-height: 1.3;
                border-radius: 4px;
                margin-bottom: 1px;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                transition: background-color 0.15s ease, color 0.15s ease, padding-left 0.15s ease;
            }

            .menu-link:hover {
                background-color: #dbeafe;
                color: #143160;
                font-weight: 500;
                padding-left: 9px;
                text-decoration: none !important;
            }

            /* Notification bubble styling */
            .noti_Container {
                position: relative;
                width: 16px;
                height: 16px;
                cursor: pointer;
            }
            
            .noti_bubble {
                position: absolute;
                font-size: 12px;
                top: 450px;
                left: 0px;
                padding-right: 2px;
                background-color: transparent;
                color: black;
                font-weight: bold;
                z-index: 2;
                width: 100%;
                height: 16px;
            }
        </style>

        <script>
            function f1() {
                document.getElementById("n1").style.visibility = 'hidden';
            }
        </script>
        
        <script type="text/javascript">
            var req;
            function count() {
                var idField = document.getElementById("userid");
                var url = "AppointmentController";
                if (typeof XMLHttpRequest != "undefined") {
                    req = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    req = new ActiveXObject("Microsoft.XMLHTTP");
                }
                var url = "AppointmentController";
                req.open("POST", url, true);
                req.send();
                req.onreadystatechange = function() {
                    if (req.readyState == 4) {
                        if (req.status == 200) {
                            var count = req.responseXML.getElementsByTagName("count")[0];
                            var childCount = count.childNodes[0].nodeValue;
                            var mdiv = document.getElementById("n1");
                            mdiv.innerHTML = childCount;
                            mdiv.style.visible = 'block';
                        }
                    }
                }
            }

            var timer = null;
            function auto_reload() {
                alert();
                window.location = '/notication';
            }
        </script>
        
        <script language="JavaScript">
            var clockID = 0;
            function UpdateClock() {
                if (clockID) {
                    clearTimeout(clockID);
                    clockID = 0;
                }
                var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
                var tDate = new Date();
                var clockDiv = document.getElementById('clock');
                var hours, min, sec;
                
                if (tDate.getHours() < 10) { hours = "0" + tDate.getHours(); }
                else { hours = tDate.getHours(); }
                
                if (tDate.getMinutes() < 10) { min = "0" + tDate.getMinutes(); }
                else { min = tDate.getMinutes(); }
                
                if (tDate.getSeconds() < 10) { sec = "0" + tDate.getSeconds(); }
                else { sec = tDate.getSeconds(); }
                
                if(clockDiv) {
                    clockDiv.innerHTML = " " + months[tDate.getMonth()] + " " + tDate.getDate() + " " + tDate.getFullYear() + " "
                            + hours + ":" + min + ":" + sec;
                }
                clockID = setTimeout("UpdateClock()", 1000);
            }
            function StartClock() { clockID = setTimeout("UpdateClock()", 1); }
            function KillClock() { if (clockID) { clearTimeout(clockID); clockID = 0; } }
            function change(id, image) {
                var img = document.getElementById(id);
                img.src = "/images/" + image;
            }
        </script>

        <script>
            $(function() {
                $("#container").accordion({
                    collapsible: true,
                    active: false,
                    autoHeight: false,
                    navigation: true,
                    icons: false
                });
            });
        </script>
    </head>
    
    <%
    //allow access only if session exists
    String user = null;
    if(session.getAttribute("userAuth") == null){
        response.sendRedirect("/vision/UserProcess/sessionTimeOut");
    }else user = (String) session.getAttribute("userAuth");
    
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) userName = cookie.getValue();
            if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }
    %>
    
    <body onload="StartClock()" onunload="KillClock()">
        
        <div class="headerTD"><label>A.Y:&nbsp;<c:out default="" value="${currentAcademicYear}"/></label></div>

        <div id="container">
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-users"></i>Students</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/StudentProcess/viewAll">View All</a>
                 <a class="menu-link" target="mainFrame" href="/vision/StudentProcess/addNew">Add New</a>
                 <a class="menu-link" target="mainFrame" href="/vision/StudentProcess/QuickStudentSave">Quick Student Save</a>
                 <a class="menu-link" target="mainFrame" href="/vision/AttendanceProcess/viewAttendance">View Attendance</a>
                 <a class="menu-link" target="mainFrame" href="/vision/AttendanceProcess/markAttendance">Mark Attendance</a>
                 <a class="menu-link" target="mainFrame" href="/vision/attendancemarkselectdate">Mark Monthly Attendance</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-money"></i>Fees</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/FeesProcess/feesCollect">Fees Collect</a>
                 <a class="menu-link" target="mainFrame" href="/vision/FeesProcess/feesStructure">Fees Structure</a>
                 <a class="menu-link" target="mainFrame" href="/vision/FeesProcess/otherfeesCollect">Bus Fees Collect</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-credit-card"></i>Expenses</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/AdminProcess/viewAllExpenses">Expenses</a>
            </div>
            
            <!-- 1. Accounts (FA 4.0 compatible) -->
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-list-alt"></i>Accounts</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/AccountProcess/createAccount">Chart of Accounts</a>
                 <a class="menu-link" target="mainFrame" href="/vision/AccountProcess/createVoucher">Create Voucher</a>
                 <a class="menu-link" target="mainFrame" href="/vision/vouchersearch">View/Cancel Voucher</a>
                 <a class="menu-link" target="mainFrame" href="/vision/AccountProcess/viewCancelledVouchers">Cancelled Vouchers</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-file-text-o"></i>Documents</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/DocumentsProcess/admissionAbstract">Admission Abstract</a>
                 <a class="menu-link" target="mainFrame" href="/vision/DocumentsProcess/studentsDetailsBonafide">Bonafide Certificate</a>
                 <a class="menu-link" target="mainFrame" href="/vision/DocumentsProcess/studentsDetailsStudyCertificate">Study Certificate</a>
                 <a class="menu-link" target="mainFrame" href="/vision/DocumentsProcess/studentsArticleCertificate">Article 371</a>
                 <a class="menu-link" target="mainFrame" href="/vision/DocumentsProcess/characterCertificate">Character Certificate</a>
                 <a class="menu-link" target="mainFrame" href="/vision/DocumentsProcess/transferCertificate">Transfer Certificate</a>
                 <a class="menu-link" target="mainFrame" href="/vision/ExamDetailsProcess/generateHallTicket">Hall Ticket</a>
                 <a class="menu-link" target="mainFrame" href="/vision/PeriodProcess/generateTimeTable">Class Time Table</a>
                 <a class="menu-link" target="mainFrame" href="/vision/PeriodProcess/generateTeacherTimeTable">Teacher Time Table</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-pencil-square-o"></i>Exams</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/MarksDetailsProcess/marksEntry">Enter Marks</a>
                 <a class="menu-link" target="mainFrame" href="/vision/MarksDetailsProcess/marksEntrySub">Enter Marks All</a>
                 <a class="menu-link" target="mainFrame" href="/vision/MarksDetailsProcess/getSubjectsExams">View Marks</a>
                 <a class="menu-link" target="mainFrame" href="/vision/MarksDetailsProcess/getSubjectsExamsSub">View Marks All</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-trophy"></i>Holistic Development Assessment</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/RatingDetailsProcess/ratingEntry">Enter Ratings</a>
                 <a class="menu-link" target="mainFrame" href="/vision/RatingDetailsProcess/getSubjectsAssessments">View Ratings</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-search"></i>Advance Search</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/StudentProcess/advanceSearchStudents">Search</a>
            </div>
            
            <!-- 2. Generate Cards (FA 4.0 compatible) -->
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-print"></i>Generate Cards</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/Printids/generateIds">Student IDs</a>
                 <a class="menu-link" target="mainFrame" href="/vision/Printids/generateIdsEmployees">Staff IDs</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-book"></i>Library</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/LibraryProcess/addbooks">Add Books</a>
                 <a class="menu-link" target="mainFrame" href="/vision/LibraryProcess/viewbooks">View Books</a>
                 <a class="menu-link" target="mainFrame" href="/vision/LibraryProcess/issuebooks">Issue Books</a>
                 <a class="menu-link" target="mainFrame" href="/vision/LibraryProcess/returnbooks">Return Books</a>
            </div>
               
            <!-- 3. Staff (FA 4.0 compatible) -->
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-user"></i>Staff</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/EmployeeProcess/ViewAllEmployee">View All</a>
                 <a class="menu-link" target="mainFrame" href="/vision/EmployeeProcess/addEmployeePage">Add Staff</a>
                 <a class="menu-link" target="mainFrame" href="/vision/AttendanceProcess/attendanceMarkStaff">Mark Attendance</a>
                 <a class="menu-link" target="mainFrame" href="/vision/AttendanceProcess/viewAttendanceStaff">View Attendance</a>
            </div>
            
            <!-- 4. Sponsor (FA 4.0 compatible) -->
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-thumbs-o-up"></i>Sponsor</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/SponserProcess/ViewAllSponsor">View All</a>
                 <a class="menu-link" target="mainFrame" href="/vision/SponserProcess/addSponsorPage">Add Sponsor</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-sign-in"></i>Login Staff</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/LoginProcess/viewLoginDetail">View Login Detail</a>
                 <a class="menu-link" target="mainFrame" href="/vision/LoginProcess/addLoginStaff">Add Staff Login</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-calendar"></i>Events</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/EventProcess/calendar">Create Event</a>
                 <a class="menu-link" target="mainFrame" href="/vision/EventProcess/viewCalendar">View Calendar</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-plus-square"></i>Extras</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/Backup&Restore">Backup</a>
                 <a class="menu-link" target="mainFrame" href="/vision/changePassword">Change Password</a>
                 <a class="menu-link" target="mainFrame" href="/vision/uploadattendance">Upload Attendance File</a>
                 <a class="menu-link" target="mainFrame" href="/vision/StudentProcess/archiveViewAll">Archive Students</a>
                 <a class="menu-link" target="mainFrame" href="/vision/EmployeeProcess/archiveEmployeeViewAll">Archive Employee</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-shopping-cart"></i>Stock Management</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/MessItemsMoveProcess/issueItems">Generate Receipts</a>
                 <a class="menu-link" target="mainFrame" href="/vision/MessItemsMoveProcess/generateTaxInvoice">Generate Tax Invoice</a>
                 <a class="menu-link" target="mainFrame" href="/vision/MessItemsMoveProcess/generateTaxInvoiceReport">Tax Invoice Report</a>
                 <a class="menu-link" target="mainFrame" href="/vision/MessItemsProcess/purchaseItems">Purchase</a>
                 <a class="menu-link" target="mainFrame" href="/vision/MessItemsProcess/purchaseOrder">Purchase Order</a>
                 <a class="menu-link" target="mainFrame" href="/vision/MessItemsProcess/viewItems">View/Add Items</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-truck"></i>Suppliers</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/MessSuppliersProcess/paymentSuppliers">Make Payment</a>
                 <a class="menu-link" target="mainFrame" href="/vision/MessSuppliersProcess/balanceSuppliers">Balance Report</a>
                 <a class="menu-link" target="mainFrame" href="/vision/MessSuppliersProcess/supplierPaymentReport">Payment & Receipt Report</a>
                 <a class="menu-link" target="mainFrame" href="/vision/MessSuppliersProcess/viewSuppliers">View/Add Suppliers</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-bell"></i>Send Notifications</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/SMSProcess/sendSMS">SMS</a>
                 <a class="menu-link" target="mainFrame" href="/vision/sendemail">Email</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-level-up"></i>Promotion</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/ClassProcess/promoteClass">Promotions</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-upload"></i>Import</a></h5>
            <div>
                  <a class="menu-link" target="mainFrame" href="/vision/importfile">Import Files</a>
                  <a class="menu-link" target="mainFrame" href="/vision/importfilefees">Import Fees Files</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-pencil"></i>Diary</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/DiaryProcess/getdiarystudent">Diary</a>
                 <a class="menu-link" target="mainFrame" href="/vision/DiaryProcess/viewdiarystudent">View Diary</a>
            </div>
            
            <!-- 5. Logbook (FA 4.0 compatible) -->
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-clipboard"></i>Logbook</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/StudentDiaryProcess/getdiarystudent">Add Log</a>
                 <a class="menu-link" target="mainFrame" href="/vision/StudentDiaryProcess/viewdiarystudent">View Logbook</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-question-circle"></i>Enquiry</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/EnquiryProcess/enquiry">Enquiry</a>
                 <a class="menu-link" target="mainFrame" href="/vision/EnquiryProcess/viewEnquiry">View Enquiry</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-tasks"></i>Staff Assignments</a></h5>
            <div>
                 <a class="menu-link" target="mainFrame" href="/vision/JobProcess/viewAllQueries">View Assignment</a>
                 <a class="menu-link" target="mainFrame" href="/vision/JobProcess/viewAllTasks">View Tasks</a>
            </div>
            
            <!-- 6. H.R. (FA 4.0 compatible) -->
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-sitemap"></i>H.R.</a></h5>
            <div>
                <a class="menu-link" target="mainFrame" href="/vision/HrProcess/advanceSalary">Advance Salary</a>
                <a class="menu-link" target="mainFrame" href="/vision/HrProcess/salaryApproval">Advance Salary Approval</a>
                <a class="menu-link" target="mainFrame" href="/vision/HrProcess/salaryIssue">Advance Salary Issue</a>
                <a class="menu-link" target="mainFrame" href="/vision/HrProcess/processSalary">Process Salary</a>
                <a class="menu-link" target="mainFrame" href="/vision/HrProcess/issueStaffSalary">Issue Staff Salary</a>
            </div>
            
            <!-- 7. Leave Management (FA 4.0 compatible) -->
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-calendar-o"></i>Leave Management</a></h5>
            <div>
                <a class="menu-link" target="mainFrame" href="/vision/HrProcess/leaveApplication">Leave Application</a>
                <a class="menu-link" target="mainFrame" href="/vision/HrProcess/leaveApprovals">Leave Approvals</a>
            </div>

            <!-- 8. Appointments (FA 4.0 compatible) -->
            <!-- <h5 class="sideaccordian"><a href="#"><i class="fa fa-clock-o"></i>Appointments</a></h5>
            <div>
                <a class="menu-link" target="mainFrame" href="/vision/AppointmentProcess/viewAllAppointments">Appointment</a>
            </div>

            <h5 class="sideaccordian"><a href="#"><i class="fa fa-briefcase"></i>Case</a></h5>
            <div>
                <a class="menu-link" target="mainFrame" href="/vision/CasesProcess/viewAllCases">Cases</a>
                <a class="menu-link" target="mainFrame" href="/vision/CasesProcess/createCase">Create Cases</a>
            </div>

            <h5 class="sideaccordian"><a href="#"><i class="fa fa-exclamation-triangle"></i>Caveat</a></h5>
            <div>
                <a class="menu-link" target="mainFrame" href="/vision/CaveatProcess/viewAllCaveats">Caveats</a>
                <a class="menu-link" target="mainFrame" href="/vision/CaveatProcess/createCaveats">Create Caveat</a>
            </div> -->
            
        </div>
       
        <script type="text/javascript">
            $(document).ready(function() {
                $('#Notification').jnotifyInizialize({
                    oneAtTime: false,
                    appendType: 'append'
                }).css({
                    'position': 'absolute',
                    'marginTop': '200px',
                    'left': '0px',
                    'width': '175px',
                    'height': '40px',
                    'z-index': '9999'
                });
            });
        </script>

        <div id="n1" class="noti_bubble">
            <table width="100%" border="0">
                <tr></tr>
            </table>
        </div>

        <script>
            getdata1();
        </script>
    </body>
</html>
