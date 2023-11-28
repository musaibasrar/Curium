<%-- 
Document   : reports
Created on : Nov 8, 2021, 09:50:11 PM
Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reports</title>
        <script language="JavaScript" src="/children/js/motionpack.js"></script>
        <link rel="stylesheet" href="/children/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/children/css/datePicker/demos.css">
		
        <script type="text/javascript" src="/children/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/children/js/datePicker/ui/jquery.ui.core.js"></script>
        <script src="/children/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script src="/children/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script src="/children/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script src="/children/js/datePicker/ui/sliderAccess.js"></script>
        <script src="/children/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <link href="/children/css/notification/jquery.jnotify.css" rel="stylesheet" type="text/css" />
        <script src="/children/js/notification/jquery.jnotify.js" type="text/javascript"></script>
		<link rel="stylesheet" href="/children/css/font-awesome.css">
        
        
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
	response.sendRedirect("/children/UserProcess/sessionTimeOut");
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
        <div class="headerTD" style="width: 95%" ><label style="font-size: 14px;">A.Y:&nbsp;${currentAcademicYear}</label> </div>

        <div id="container" style="width: 95%" >
            
             
            <h5 class="sideaccordian" ><a href="#">Students</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/children/DocumentsProcess/studentsDetailsReports" style="font-size: 12px;">Detail Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/children/DocumentsProcess/studentsAdmissionReports" style="font-size: 12px;">Admission Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/children/DocumentsProcess/studentsPendingAdmissionReports" style="font-size: 12px;">Pending Admission Report</a>
            			</td>
            		</tr>
            		
                </table>
            </div>
            
            
              <h5 class="sideaccordian" ><a href="#">Fees</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		
            		<!-- <tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/children/feesCollectionDetails" style="font-size: 12px;">Fees Collection Details</a>
            			</td>
            		</tr> -->
            		
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/children/FeesProcess/feesReport" style="font-size: 12px;">Fees Report</a>
            			</td>
            		</tr>
            		
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/children/FeesProcess/feesDueStampFees" style="font-size: 12px;">Fees Stamp Due Report</a>
            			</td>
            		</tr>
            		
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="/children/FeesProcess/feesWaiveoffReport" style="font-size: 12px;">Fees Waive off Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/children/FeesProcess/feesConcessionReport" style="font-size: 12px;">Fees Concession Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="/children/feescancelledreceipts" style="font-size: 12px;">Cancelled Fees Receipts</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/children/FeesProcess/otherfeesReport" style="font-size: 12px;">Other fees Report</a>
            			</td>
            		</tr>
                </table>
            </div>
            
            
             <h5 class="sideaccordian" ><a href="#">Student Attendance</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/children/AttendanceProcess/attendanceExport" style="font-size: 12px;">Export Attendance</a>
            			</td>
            		</tr>
                </table>
            </div>
            
            
              <h5 class="sideaccordian" ><a href="#">Staff</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/children/AttendanceProcess/attendanceExportViewStaff" style="font-size: 12px;">Export Attendance</a>
            			</td>
            		</tr>
                </table>
            </div>
            

            <h5 class="sideaccordian" ><a href="#">Exams</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/children/MarksDetailsProcess/progressReport" style="font-size: 12px;">Generate Marks Card</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/children/MarksDetailsProcess/getGraphicalReportData" style="font-size: 12px;">Graphical Report</a>
            			</td>
            		</tr>
                </table>
            </div>
            
            <h5 class="sideaccordian" ><a href="#">Accounts</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/children/AccountProcess/generalLedgerReport" style="font-size: 12px;">General Ledger Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/children/incomestatement" style="font-size: 12px;">Income Statement</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/children/trialbalance" style="font-size: 12px;">Trial Balance</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				<a target="mainFrame" href="/children/AccountProcess/balanceSheet" style="font-size: 12px;">Balance Sheet</a>
            			</td>
            		</tr>
                </table>
            </div>
            
            <h5 class="sideaccordian" ><a href="#">Expense</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/children/adminexpensesreport" style="font-size: 12px;">Expense Report</a>
            			</td>
            		</tr>
                </table>
            </div>
            
              <h5 class="sideaccordian" ><a href="#">Stock Receipts</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/riyan/MessItemsMoveProcess/billsReport" style="font-size: 12px;">Receipts Report</a>
            			</td>
            		</tr>
                </table>
            </div>
            
            <h5 class="sideaccordian" ><a href="#" >Stock</a></h5>
            
            <div style="padding-left: 0px;padding-right: 0px;">
            
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="/children/MessItemsProcess/currentStock" style="font-size: 12px;">Stock Quantity Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/children/MessItemsProcess/batchStock" style="font-size: 12px;">Stock Price Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="/children/MessItemsProcess/issuanceStock" style="font-size: 12px;">Stock Issuance Report</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:20px ;">
            				 <a target="mainFrame" href="/children/MessItemsProcess/receiveStock" style="font-size: 12px;">Stock Received Report</a>
            			</td>
            		</tr>
            	</table>
            </div>
            
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
