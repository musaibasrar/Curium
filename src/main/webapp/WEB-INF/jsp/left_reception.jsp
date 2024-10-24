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
        <script language="JavaScript" src="/abc/js/motionpack.js"></script>
        <link rel="stylesheet" href="/abc/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/abc/css/datePicker/demos.css">

        <script type="text/javascript" src="/abc/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.core.js"></script>
        <script src="/abc/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script src="/abc/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script src="/abc/js/datePicker/ui/sliderAccess.js"></script>
        <script src="/abc/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <link href="/abc/css/notification/jquery.jnotify.css" rel="stylesheet" type="text/css" />
        <script src="/abc/js/notification/jquery.jnotify.js" type="text/javascript"></script>

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
            function processExpiringStockData() {

                if (getExpiringStockCount.readyState == 4)
                {
                    if (getExpiringStockCount.status == 200) {
                        var count = getExpiringStockCount.responseXML.getElementsByTagName("ExpiringStockCount")[0];
                        var expiringStockCount = count.childNodes[0].nodeValue;
                        var expiringStock = document.getElementById("expiringStock");
                        expiringStock.innerHTML = " " + expiringStockCount;

                        setTimeout('getExpiringStock();', 60000);


                    }
                }

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
                text-decoration:none;
                color:#FFFFFF;
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
	response.sendRedirect("/abc/UserProcess/sessionTimeOut");
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
        <form name="theClock">

            <div id="clock" class="headerTD"></div>
        </form>
        <%-- <div class="headerTD">Welcome <c:out default="" value="${executive}"/> </div> --%>
        <div class="headerTD" style="width: 95%" ><label style="font-size: 14px;">A.Y:&nbsp;${currentAcademicYear}</label> </div>

        <div id="container" style="width: 100%" >
            <h5 style="font-size: 12px"><a href="#">Students</a></h5>
            <div>
                <a target="mainFrame" href="/abc/StudentProcess/viewAll" >View All</a><br/>
                <a target="mainFrame" href="/abc/feesstructure" >Fees Structure</a><br/>
                

            </div>
            <!-- <h5 style="font-size: 12px"><a href="#">Human Resource</a></h5>
            <div>
                <a target="mainFrame" href="/abc/EmployeeProcess/viewAllEmployee" >View Employee List</a><br/>
                <a target="mainFrame" href="/abc/EmployeeProcess/addEmployeePage" >Add Employee</a>
                <a target="mainFrame" href="/abc/DepartmentProcess/departmentView" >Add Department</a>
                <a target="mainFrame" href="/abc/PositionProcess/positionView" >Add Position</a>

            </div> -->

            <h5 style="font-size: 12px"><a href="#">FEES</a></h5>
            
            <div>
            	<a target="mainFrame" href="/abc/FeesProcess/feesView" >Fees Category</a>
                <a target="mainFrame" href="/abc/FeesProcess/feesCollect" >Fees Collect</a><br/>
                

            </div> 
            
            <!-- <h5 style="font-size: 12px"><a href="#" >Administration</a></h5>
            <div>
                <a target="mainFrame" href="/abc/AdminProcess/viewAllExpenses" >Expenses</a><br/>


            </div> -->
            
            <h5 style="font-size: 12px"><a href="#" >Advance Search</a></h5>
            <div>
                <a target="mainFrame" href="/abc/AdvanceSearch" >Search</a><br/>


            </div>
			<!--
            <h5 style="font-size: 12px"><a href="#" >Promotion</a></h5>
            <div>
                <a target="mainFrame" href="/abc/Promotion" >Promotions</a><br/>


            </div> -->

            <!-- <h5 style="font-size: 12px"><a href="#" >Archive</a></h5>
            <div>
                <a target="mainFrame" href="/abc/StudentProcess/archiveViewAll" >Archive Students</a><br/>


            </div> -->

            <!--  <h5 style="font-size: 12px"><a href="#" >Configurations</a></h5>
            <div>
            	<a target="mainFrame" href="/abc/StampFeesProcess/showFeesDetails" >Stamp Fee</a><br/>
                <a target="mainFrame" href="/abc/YearProcess/updateYear" >Academic year</a><br/>
                

            </div> -->
            

            <h5 style="font-size: 12px"><a href="#" >Extras</a></h5>
            <div >
               <!--  <a target="mainFrame" href="/abc/Backup&Restore" >Backup</a><br/>
                <a target="mainFrame" href="/abc/currentIssue" >Current Issue</a><br/>
                <a target="mainFrame" href="/abc/changePassword" >Change Password</a><br/> -->
                <a target="mainFrame" href="/abc/feesCollectionDetails" >Fees Collection Details</a><br/>
            </div>
            
           <!--  <h5 style="font-size: 12px"><a href="#" >Reports</a></h5>
            <div >
                <a target="mainFrame" href="/abc/studentsdetailsreports" >Student Details</a><br/>
                
            </div>  -->
            
            
                   
            
            <!-- END -->
           
       
        <script type="text/javascript">
            $(document).ready(function() {

                // For jNotify Inizialization
                // Parameter:
                // oneAtTime : true if you want show only one message for time
                // appendType: 'prepend' if you want to add message on the top of stack, 'append' otherwise

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
