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
		<link rel="stylesheet" href="/vision/css/font-awesome.css">
        
        
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
                height: auto;
                background-image: none;
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

.sideaccordian a i {
    display: inline-block;
    width: 14px;
    margin-right: 5px;
    text-align: center;
    color: #143160;
    font-size: 13px;
    font-weight: normal;
}

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

.sideaccordian a::after {
    content: "\f105";
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
    content: "\f107";
    color: #143160 !important;
}

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
       <!--  <form name="theClock">

            <div id="clock" class="headerTD"></div>
        </form> -->
        <%-- <div class="headerTD">Welcome <c:out default="" value="${userAuth}"/> </div> --%>
        <div class="headerTD"><label>A.Y:&nbsp;<c:out default="" value="${currentAcademicYear}"/></label></div>

        <div id="container" style="width: 95%" >
            
             
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-users"></i>Students</a></h5>
            <div>
                <a class="menu-link" target="mainFrame" href="/vision/DocumentsProcess/studentsDetailsReports">Detail Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/DocumentsProcess/studentsAdmissionReports">Admission Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/DocumentsProcess/studentsPendingAdmissionReports">Pending Admission Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/ClassProcess/viewGraduated">Graduated Student</a>
                <a class="menu-link" target="mainFrame" href="/vision/ClassProcess/viewLeftOut">Former Students</a>
                <a class="menu-link" target="mainFrame" href="/vision/ClassProcess/viewDropped">Dropped Out Students</a>
                <a class="menu-link" target="mainFrame" href="/vision/DocumentsProcess/studentsRegistrationReports">Registration Report</a>
            </div>
            
            
              <h5 class="sideaccordian"><a href="#"><i class="fa fa-money"></i>Fees</a></h5>
            <div>
                <a class="menu-link" target="mainFrame" href="/vision/feessummary">Fees Summary</a>
                <a class="menu-link" target="mainFrame" href="/vision/feesCollectionDetails">Fees Collection Details</a>
                <a class="menu-link" target="mainFrame" href="/vision/FeesProcess/feesReport">Fees Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/FeesProcess/feesReportDue">Fees Due Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/feescollectiondetailscategory">Fees Collection Head Wise</a>
                <a class="menu-link" target="mainFrame" href="/vision/FeesProcess/feesDueReportHeadWise">Fees Due Head Wise</a>
                <a class="menu-link" target="mainFrame" href="/vision/FeesProcess/feesDueStampFees">Fees Stamp Due Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/FeesProcess/feesWaiveoffReport">Fees Waive off Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/FeesProcess/feesConcessionReport">Fees Concession Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/feescancelledreceipts">Cancelled Fees Receipts</a>
                <a class="menu-link" target="mainFrame" href="/vision/FeesProcess/otherfeesReport">Bus Fees Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/otherfeesCollectionDetails">Bus Fees Collection Details</a>
                <a class="menu-link" target="mainFrame" href="/vision/FeesProcess/defaulterReport">Defaulter Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/FeesProcess/dndReport">DND Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/otherfeescancelledreceipts">Cancelled Bus Fees Receipts</a>
            </div>

            <h5 class="sideaccordian"><a href="#"><i class="fa fa-pencil-square-o"></i>Exams</a></h5>
            <div>
                <a class="menu-link" target="mainFrame" href="/vision/MarksDetailsProcess/progressReportSingleExams">Marks Card</a>
                <a class="menu-link" target="mainFrame" href="/vision/MarksDetailsProcess/progressReport">Consolidated Marks Card</a>
                <a class="menu-link" target="mainFrame" href="/vision/MarksDetailsProcess/prePrimaryProgressReport">Pre Primary Marks Card</a>
                <a class="menu-link" target="mainFrame" href="/vision/MarksDetailsProcess/getGraphicalReportData">Graphical Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/MarksDetailsProcess/rankReport">Generate Student Rank</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-shopping-cart"></i>Stock Receipts</a></h5>
            <div>
                <a class="menu-link" target="mainFrame" href="/vision/MessItemsMoveProcess/billsReport">Receipts Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/MessItemsMoveProcess/getDueReport">Due Report</a>
            </div>
            
            <h5 class="sideaccordian"><a href="#"><i class="fa fa-bar-chart"></i>Stock</a></h5>
            
            <div>
                <a class="menu-link" target="mainFrame" href="/vision/MessItemsProcess/currentStock">Stock Quantity Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/MessItemsProcess/batchStock">Stock Price Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/MessItemsProcess/issuanceStock">Stock Issuance Report</a>
                <a class="menu-link" target="mainFrame" href="/vision/MessItemsProcess/receiveStock">Stock Received Report</a>
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
