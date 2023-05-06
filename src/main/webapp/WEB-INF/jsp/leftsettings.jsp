<%-- 
Document   : left settings
Created on : May 10, 2018, 12:51:11 PM
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
        <script language="JavaScript" src="/bsb/js/motionpack.js"></script>
        <link rel="stylesheet" href="/bsb/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/bsb/css/datePicker/demos.css">
		
        <script type="text/javascript" src="/bsb/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/bsb/js/datePicker/ui/jquery.ui.core.js"></script>
        <script src="/bsb/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script src="/bsb/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script src="/bsb/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script src="/bsb/js/datePicker/ui/sliderAccess.js"></script>
        <script src="/bsb/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <link href="/bsb/css/notification/jquery.jnotify.css" rel="stylesheet" type="text/css" />
        <script src="/bsb/js/notification/jquery.jnotify.js" type="text/javascript"></script>
		<link rel="stylesheet" href="/bsb/css/font-awesome.css">
      
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
	response.sendRedirect("/bsb/UserProcess/sessionTimeOut");
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
        <!-- <form name="theClock">

            <div id="clock" class="headerTD"></div>
        </form> -->
        <div class="headerTD" style="width: 95%" ><label style="font-size: 14px;">A.Y:&nbsp;${currentAcademicYear}</label> </div>
        <%-- <div class="headerTD" style="width: 95%">Master Settings <c:out default="" value="${userAuth}"/> </div> --%>

        <div id="container" style="width: 95%" >
            <h5 style="font-size: 12px"><a href="#">General</a></h5>
            <div>
                <a target="mainFrame" href="/bsb/YearProcess/updateYear" style="font-size: 12px;">Academic year</a><br/>
                <a target="mainFrame" href="/bsb/PeriodProcess/periodConfiguration" style="font-size: 12px;">Time Table</a><br/>
            </div>
            
            <h5 style="font-size: 12px"><a href="#">Class</a></h5>
            <div>
                <a target="mainFrame" href="/bsb/ClassProcess/viewClasses" style="font-size: 12px;">Add Classes</a><br/>
            </div>
            
            <h5 style="font-size: 12px"><a href="#">Fees</a></h5>
            <div>
                <a target="mainFrame" href="/bsb/FeesProcess/feesView" style="font-size: 12px;">Fees Category</a><br/>
                <a target="mainFrame" href="/bsb/StampFeesProcess/showFeesDetails" style="font-size: 12px;">Stamp Fee</a><br/>
            </div>
            
            <h5 style="font-size: 12px"><a href="#">Other Fee</a></h5>
            <div>
                <a target="mainFrame" href="/bsb/FeesProcess/otherfeesView" style="font-size: 12px;">Other Fees Category</a><br/>
                <a target="mainFrame" href="/bsb/StampFeesProcess/showOtherFeesDetails" style="font-size: 12px;">Other Fee Stamp</a><br/>

            </div>
            
            <h5 style="font-size: 12px"><a href="#">Exams</a></h5>
            <div>
                <a target="mainFrame" href="/bsb/ExamDetailsProcess/examSchedule" style="font-size: 12px;">Exam Schedule</a><br/>
                <a target="mainFrame" href="/bsb/ExamDetailsProcess/readListOfExams" style="font-size: 12px;">Exam Details</a><br/>
                <a target="mainFrame" href="/bsb/SubjectDetailsProcess/readListOfSubjectNames" style="font-size: 12px;">Subject Master</a><br/>
                <a target="mainFrame" href="/bsb/SubjectDetailsProcess/readListOfSubjects" style="font-size: 12px;">Subject Details</a>
            </div>
            
            <h5 style="font-size: 12px"><a href="#">Attendance</a></h5>
            <div>
                <a target="mainFrame" href="/bsb/AttendanceProcess/attendanceConfiguration" style="font-size: 12px;">Staff/Students</a><br/>
                <a target="mainFrame" href="/bsb/AttendanceProcess/viewAllHolidays" style="font-size: 12px;">Holidays/WeeklyOff</a><br/>
            </div>
            
              <h5 style="font-size: 12px"><a href="#" >Promotion</a></h5>
            <div>
				<a target="mainFrame" href="/bsb/ClassProcess/classHierarchy" style="font-size: 12px;">Class Hierarchy</a><br/>
            </div>
            
            <h5 style="font-size: 12px"><a href="#">Staff</a></h5>
            <div>
                
                <a target="mainFrame" href="/bsb/DepartmentProcess/departmentView" style="font-size: 12px;">Add Department</a><br/>
                <a target="mainFrame" href="/bsb/PositionProcess/positionView" style="font-size: 12px;">Add Position</a><br/>
            </div>

            <h5 style="font-size: 12px"><a href="#" >Accounts</a></h5>
            <div >
                <a target="mainFrame" href="/bsb/AccountProcess/getCurrentFinancialYear" style="font-size: 12px;">Accounting Year</a><br/>
            </div>
            
             <h5 style="font-size: 12px"><a href="#" >H.R.</a></h5>
            <div >
                <a target="mainFrame" href="/bsb/HrProcess/leaveType" style="font-size: 12px;">Leave Type</a><br/>
                <a target="mainFrame" href="/bsb/HrProcess/assignLeave" style="font-size: 12px;">Assign/View Leave</a><br/>
                <a target="mainFrame" href="/bsb/HrProcess/payHead" style="font-size: 12px;">Pay Head</a><br/>
                <a target="mainFrame" href="/bsb/HrProcess/addPayHead" style="font-size: 12px;">Add Pay Head</a><br/>
                <a target="mainFrame" href="/bsb/HrProcess/deletePayHead" style="font-size: 12px;">Delete Pay Head</a><br/>
                <a target="mainFrame" href="/bsb/HrProcess/basicPaySettings" style="font-size: 12px;">Apply Basic Pay</a><br/>
                <a target="mainFrame" href="/bsb/HrProcess/viewEditbasicPay" style="font-size: 12px;">View/Edit Basic Pay</a><br/>
                <a target="mainFrame" href="/bsb/HrProcess/pfSettings" style="font-size: 12px;">PF Settings</a><br/>
            </div>
            
              <!--  <h5 style="font-size: 12px"><a href="#">Extras</a></h5>
            <div>
                <a target="mainFrame" href="/bsb/AttendanceProcess/attendanceConfiguration" style="font-size: 12px;">Staff/Students</a><br/>
                <a target="mainFrame" href="/bsb/AttendanceProcess/viewAllHolidays" style="font-size: 12px;">Holidays/WeeklyOff</a><br/>
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
