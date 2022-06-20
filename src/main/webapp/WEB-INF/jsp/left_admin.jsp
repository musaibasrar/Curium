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
        <script language="JavaScript" src="/sla/js/motionpack.js"></script>
        <link rel="stylesheet" href="/sla/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/sla/css/datePicker/demos.css">
		
        <script type="text/javascript" src="/sla/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/sla/js/datePicker/ui/jquery.ui.core.js"></script>
        <script src="/sla/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script src="/sla/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script src="/sla/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script src="/sla/js/datePicker/ui/sliderAccess.js"></script>
        <script src="/sla/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <link href="/sla/css/notification/jquery.jnotify.css" rel="stylesheet" type="text/css" />
        <script src="/sla/js/notification/jquery.jnotify.js" type="text/javascript"></script>
		<link rel="stylesheet" href="/sla/css/font-awesome.css">
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
            /**
             * Comment
             */
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
                //req.open("POST", url, true);

                //req.send();
                /*req.onreadystatechange = function()
                 {
                 if (req.readyState==4)
                 {
                 if (req.status==200){
                 
                 var count = req.responseXML.getElementsByTagName("count")[0];
                 var childCount=count.childNodes[0].nodeValue;
                 var mdiv = document.getElementById("n1");
                 mdiv.innerHTML=childCount;
                 mdiv.style.visible='block';
                 
                 }
                 }
                 }*/



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
        <style>

            .noti_Container {
                position:relative;
                border:1px solid blue; /* This is just to show you where the container ends */
                width:16px;
                height:16px;
                cursor: pointer;
            }
            .noti_bubble {
                position:absolute;
                font-size: 12px;
                top: 450px;
                left: 0px;
                padding-right:2px;
                background-color:transparent;
                color:black;
                font-weight:bold;

                z-index: 2;
                width: 100%;
                height: 16px;
            }
        </style>
        <style>
            body{
                margin:0;
                padding:0;
                background:#FFFFFF;
                font:80% Tahoma;
                color:#555;
                line-height:150%;
                text-align:left;

            }
            /*body{
                margin:0;
                padding:0;
                background:#E6EEF4;
                font:80% Tahoma;
                color:#555;
                line-height:150%;
                text-align:left;

            }*/
            a{
                text-decoration:none;

                color:#cfe0ea;;
            }
            a:hover{
                text-decoration:underline;
                color:#EB6000;
            }
            h1{
                font-size:140%;
                margin:0 20px;
                line-height:80px;	
            }

            #content{margin:0 0px;}
            p{	
                margin:0 auto;
                width:700px;
                padding:1em 0;
            }


            .noti_Container {
                position:relative;
                border:1px solid blue; /* This is just to show you where the container ends */
                width:16px;
                height:16px;
                cursor: pointer;
            }
            .headerTD{
                border-radius:1px;
                background-color:#4b6a84;
                background-image: url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
                color: #FFFFFF;
                font-family: Tahoma;
                font-size: 13px;
                text-transform: uppercase;
                text-align: center;
                font-weight: bold;
                height: 22px;
            }

.sideaccordian{
		
		font-size: 12px;
		/* border: 0px; */
		border-radius: 5px;
		/* border-bottom:  1px solid #010d1c !important; */
}


.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default { border: 1px solid #cccccc; background: #ffffff url(images/ui-bg_glass_100_f6f6f6_1x400.png) 50% 50% repeat-x; font-weight: bold; color: #1c94c4; }
.ui-widget-content {
    border: 1px solid #b6cfe2;
    background: #ffffff;
    color: #222222;
}


        </style>

        <script>
            function f1()
            {
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



                //req.open("POST", url, true);

                req.send();
                req.onreadystatechange = function()
                {
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
                }



            }


            var timer = null;
            function auto_reload()
            {
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
                if (tDate.getHours() < 10) {
                    hours = "0" + tDate.getHours();

                }
                else {
                    hours = tDate.getHours();
                }
                if (tDate.getMinutes() < 10) {
                    min = "0" + tDate.getMinutes();

                }
                else {
                    min = tDate.getMinutes();
                }
                if (tDate.getSeconds() < 10) {
                    sec = "0" + tDate.getSeconds();

                }
                else {
                    sec = tDate.getSeconds();
                }
                clockDiv.innerHTML = " " + months[tDate.getMonth()] + " " + tDate.getDate() + " " + tDate.getFullYear() + " "
                        + hours + ":"
                        + min + ":"
                        + sec;

                clockID = setTimeout("UpdateClock()", 1000);
            }
            function StartClock() {
                clockID = setTimeout("UpdateClock()", 1);
            }
            function KillClock() {
                if (clockID) {
                    clearTimeout(clockID);
                    clockID = 0;
                }

            }
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
                    navigation: true
                });


            });
        </script>
    </head>
      <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/sla/UserProcess/sessionTimeOut");
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
       <!--  <form name="theClock">

            <div id="clock" class="headerTD"></div>
        </form> -->
        <%-- <div class="headerTD">Welcome <c:out default="" value="${userAuth}"/> </div> --%>

        <div id="container" style="width: 95%" >
            <h5 class="sideaccordian" ><a href="#">Clients</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/StudentProcess/viewAllStudentsWithParents" style="font-size: 12px;">View All</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/StudentProcess/addNew" style="font-size: 12px;">Add New</a>
            			</td>
            		</tr>
            		<!-- <tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/AttendanceProcess/markAttendance" style="font-size: 12px;">Mark Attendance</a>
            			</td>
            		</tr>
            		
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/AttendanceProcess/viewAttendance" style="font-size: 12px;">View Attendance</a>
            			</td>
            		</tr> -->
            		
                </table>
            </div>
            
            <h5 class="sideaccordian" ><a href="#">Jobs</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/QueryProcess/viewAllQueries" style="font-size: 12px;">View</a>
            			</td>
            		</tr>
                </table>
            </div>
            
            
              <h5 class="sideaccordian" ><a href="#">Appointments</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/AppointmentProcess/viewAllAppointments" style="font-size: 12px;">View</a>
            			</td>
            		</tr>
                </table>
            </div>
			
             <!--  <h5 class="sideaccordian" ><a href="#">Fees</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/FeesProcess/feesCollect" style="font-size: 12px;">Fees Collect</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/sla/FeesProcess/feesStructure" style="font-size: 12px;">Fees Structure</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/feesCollectionDetails" style="font-size: 12px;">Fees Collection Details</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="/sla/feescancelledreceipts" style="font-size: 12px;">Cancelled Fees Receipts</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/FeesProcess/feesReport" style="font-size: 12px;">Fees Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/sla/FeesProcess/feesWaiveoffReport" style="font-size: 12px;">Fees Waive off Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/FeesProcess/feesConcessionReport" style="font-size: 12px;">Fees Concession Report</a>
            			</td>
            		</tr>
                </table>
            </div> -->
            
            <h5 class="sideaccordian" ><a href="#">Expenses</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/AdminProcess/viewAllExpenses" style="font-size: 12px;">Expenses</a>
            			</td>
            		</tr>
                </table>
            </div>
            
            
            
            <h5 class="sideaccordian" ><a href="#">Accounts</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/AccountProcess/createAccount" style="font-size: 12px;">Chart of Accounts</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/sla/AccountProcess/createVoucher" style="font-size: 12px;">Create Voucher</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/AccountProcess/viewVoucherReceipt" style="font-size: 12px;">View/Cancel Voucher</a>
            			</td>
            			            		
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/sla/AccountProcess/viewCancelledVouchers" style="font-size: 12px;">Cancelled Vouchers</a>
            			</td>
            		</tr>
            		<!-- <tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/AccountProcess/generalLedgerReport" style="font-size: 12px;">General Ledger Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/sla/incomestatement" style="font-size: 12px;">Income Statement</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/trialbalance" style="font-size: 12px;">Trial Balance</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/sla/AccountProcess/balanceSheet" style="font-size: 12px;">Balance Sheet</a>
            			</td>
            		</tr> -->
                </table>
            </div>
            
            
            <!-- <h5 class="sideaccordian" ><a href="#">Documents</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/DocumentsProcess/studentsDetailsReports" style="font-size: 12px;">Student Details</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="/sla/DocumentsProcess/admissionAbstract" style="font-size: 12px;">Admission Abstract</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/DocumentsProcess/studentsDetailsBonafide" style="font-size: 12px;">Bonafide Certificate</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="/sla/DocumentsProcess/transferCertificate" style="font-size: 12px;">Transfer Certificate</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/ExamDetailsProcess/generateHallTicket" style="font-size: 12px;">Hall Ticket</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="/sla/PeriodProcess/generateTimeTable" style="font-size: 12px;">Class Time Table</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/PeriodProcess/generateTeacherTimeTable" style="font-size: 12px;">Teacher Time Table</a>
            			</td>
            		</tr>
                </table>
            </div> -->
            
            
            <!--  <h5 class="sideaccordian" ><a href="#">Attendance</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/AttendanceProcess/viewAttendance" style="font-size: 12px;">View Attendance</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/AttendanceProcess/markAttendance" style="font-size: 12px;">Mark Attendance</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/AttendanceProcess/attendanceExport" style="font-size: 12px;">Export Attendance</a>
            			</td>
            		</tr>
                </table>
            </div>
             -->
            
           <!--  <h5 class="sideaccordian" ><a href="#">Exams</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/MarksDetailsProcess/marksEntry" style="font-size: 12px;">Enter Marks</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/sla/MarksDetailsProcess/getSubjectsExams" style="font-size: 12px;">View Marks</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/MarksDetailsProcess/progressReport" style="font-size: 12px;">Generate Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/sla/MarksDetailsProcess/getGraphicalReportData" style="font-size: 12px;">Graphical Report</a>
            			</td>
            		</tr>
                </table>
            </div>
             -->
            
                  <!-- <h5 class="sideaccordian" ><a href="#">Advance Search</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/StudentProcess/advanceSearchStudents" style="font-size: 12px;">Search</a>
            			</td>
            		</tr>
                </table>
            </div> -->
            
           <!--   <h5 class="sideaccordian" ><a href="#">Generate Cards</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/Printids/generateIds" style="font-size: 12px;">Generate IDs</a>
            			</td>
            		</tr>
                </table>
            </div> -->
            
            
               
              <h5 class="sideaccordian" ><a href="#">Staff</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/EmployeeProcess/ViewAllEmployee" style="font-size: 12px;">View All</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/EmployeeProcess/addEmployeePage" style="font-size: 12px;">Add</a>
            			</td>
            		</tr>
            		<!-- <tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/AttendanceProcess/attendanceMarkStaff" style="font-size: 12px;">Mark Attendance</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
								<a target="mainFrame" href="/sla/AttendanceProcess/viewAttendanceStaff" style="font-size: 12px;">View Attendance</a>            				 
            			</td>
            		</tr> -->
            		<!-- <tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/AttendanceProcess/attendanceExportViewStaff" style="font-size: 12px;">Export Attendance</a>
            			</td>
            		</tr> -->
                </table>
            </div>
            
            
            
            <h5 class="sideaccordian" ><a href="#">Extras</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/Backup&Restore" style="font-size: 12px;">Backup</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/sla/changePassword" style="font-size: 12px;">Change Password</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/StudentProcess/archiveViewAll" style="font-size: 12px;">Archive Clients</a>
            			</td>
            		</tr>
            		<!-- <tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="/sla/uploadattendance" style="font-size: 12px;">Upload Attendance File</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/StudentProcess/archiveViewAll" style="font-size: 12px;">Archive Clients</a>
            			</td>
            		</tr> -->
                </table>
            </div>
            
           <!--   <h5 style="font-size: 12px"><a href="#" >H.R.</a></h5>
            <div >
                <a target="mainFrame" href="/sla/HrProcess/advanceSalary" style="font-size: 12px;">Advance Salary</a><br/>
                <a target="mainFrame" href="/sla/HrProcess/salaryApproval" style="font-size: 12px;">Advance Salary Approval</a><br/>
                <a target="mainFrame" href="/sla/HrProcess/salaryIssue" style="font-size: 12px;">Advance Salary Status</a><br/>
                <a target="mainFrame" href="/sla/HrProcess/processSalary" style="font-size: 12px;">Process Salary</a><br/>
                <a target="mainFrame" href="/sla/HrProcess/issueStaffSalary" style="font-size: 12px;">Issue Staff Salary</a><br/>
            </div>
            
            <h5 style="font-size: 12px"><a href="#" >Leave Management</a></h5>
            <div >
                <a target="mainFrame" href="/sla/HrProcess/leaveApplication" style="font-size: 12px;">Leave Application</a><br/>
                <a target="mainFrame" href="/sla/HrProcess/leaveApprovals" style="font-size: 12px;">Leave Approvals</a><br/>
            </div> -->
            
            <!-- <h5 class="sideaccordian" ><a href="#" >Stock Management</a></h5>
        	
        	<div style="padding-left: 0px;padding-right: 0px;">
            
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/MessItemsMoveProcess/issueItems" style="font-size: 12px;">Issue</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/MessItemsProcess/purchaseItems" style="font-size: 12px;">Receive</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="/sla/MessItemsProcess/viewItems" style="font-size: 12px;">View/Add Items</a>
            			</td>
            		</tr>
            	</table>
            </div> -->
            
            
           <!--  <h5 class="sideaccordian" ><a href="#" >Stock Reports</a></h5>
            
            <div style="padding-left: 0px;padding-right: 0px;">
            
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/MessItemsProcess/currentStock" style="font-size: 12px;">Stock Quantity Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/MessItemsProcess/batchStock" style="font-size: 12px;">Stock Price Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="/sla/MessItemsProcess/issuanceStock" style="font-size: 12px;">Stock Issuance Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/MessItemsProcess/receiveStock" style="font-size: 12px;">Stock Received Report</a>
            			</td>
            		</tr>
            	</table>
            </div> -->
            
           <!--  <h5 class="sideaccordian"><a href="#" >Suppliers</a></h5>
              
              <div style="padding-left: 0px;padding-right: 0px;">
            
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/MessSuppliersProcess/paymentSuppliers" style="font-size: 12px;">Make Payment</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/MessSuppliersProcess/balanceSuppliers" style="font-size: 12px;">Balance Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="/sla/MessSuppliersProcess/supplierPaymentReport" style="font-size: 12px;">Payment & Receipt Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/MessSuppliersProcess/viewSuppliers" style="font-size: 12px;">View/Add Suppliers</a>
            			</td>
            		</tr>
            	</table>
            </div> -->
            
           <!--  <h5 class="sideaccordian"><a href="#" >Mess Cards</a></h5>
            
            <div style="padding-left: 0px;padding-right: 0px;">
            
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/Printids/generateIds" style="font-size: 12px;">Generate IDs</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/sla/Printids/cardValidity" style="font-size: 12px;">Card Validity</a>
            			</td>
            		</tr>
            	</table>
            </div> -->
            
        <!--     <h5 class="sideaccordian" ><a href="#">Send Notifications</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/sla/SMSProcess/sendSMS" style="font-size: 12px;">SMS</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/sla/sendemail" style="font-size: 12px;">Email</a>
            			</td>
            		</tr>
                </table>
            </div> -->
            
             <!--   <h5 class="sideaccordian" ><a href="#">Promotion</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="/sla/ClassProcess/promoteClass" style="font-size: 12px;">Promotions</a>
            			</td>
            		</tr>
                </table>
            </div>
             -->
            
           <!--  <h5 style="font-size: 12px"><a href="#" >Import</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
                              <a target="mainFrame" href="/sla/importfile" style="font-size: 12px;">Import Files</a><br/>
				        </td>
				     </tr>
				 </table>
            </div> -->
            
            </div>
                   
            
            <!-- END -->
           
       
        <script type="text/javascript">
            $(document).ready(function() {

                $('#Notification')
                        .jnotifyInizialize({
                    oneAtTime: false,
                    appendType: 'append'

                })
                        .css({'position': 'absolute',
                    'marginTop': '200px',
                    'left': '0px',
                    'width': '175px',
                    'height': '40px',
                    'z-index': '9999'
                });
                // --------------------------------------------------------------------------

                // For add a notification on button click
                // Parameter:
                // text: Html do you want to show
                // type: 'message' or 'error'
                // permanent: True if you want to make a message permanent
                // disappearTime: Time spent before closing message




                // -----------------------------------------------------
            });
        </script>


        <div id="n1" class="noti_bubble">
            <table width="100%" border="0">


                <tr>                   


                </tr>

            </table>

        </div>

        <script>
            getdata1();
            
        </script>
    </body>

</html>
