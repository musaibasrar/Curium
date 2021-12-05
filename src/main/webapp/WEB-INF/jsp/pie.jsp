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
        <script language="JavaScript" src="js/motionpack.js"></script>
        <link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
         <link rel="stylesheet" href="css/scss.css">
        <link rel="stylesheet" href="css/datePicker/demos.css">

        <script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
        <script src="js/datePicker/ui/jquery.ui.widget.js"></script>
        <script src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script src="js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script src="js/datePicker/ui/sliderAccess.js"></script>
        <script src="js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <link href="css/notification/jquery.jnotify.css" rel="stylesheet" type="text/css" />
        <script src="js/notification/jquery.jnotify.js" type="text/javascript"></script>

        <script type="text/javascript">
        var donut = document.getElementById('donut');
        var donutCtx = donut.getContext('2d');
        var chart = new Chart(donutCtx).Doughnut([{ value: 101342, color: '#1fc8f8' }, { value: 55342, color: '#76a346' }], { percentageInnerCutout: 50, animateScale: true, segmentShowStroke: false, animateRotate: false });

        var data = { 
          labels: ['12/12/2013','13/12/2013','14/12/2013','14/12/2013','15/12/2013','16/12/2013','17/12/2013'],
           datasets: [
             { data: [100,210,220,300,200,190,192], fillColor: 'rgba(31,200,248,0.1)', strokeColor: '#1fc8f8', pointColor: '#1fc8f8', pointStrokeColor: '#1fc8f8' },
             { data: [130,200,190,150,140,210,0], fillColor: 'rgba(118,163,70,0.1)', strokeColor: '#76a346', pointColor: '#76a346', pointStrokeColor: '#76a346' }
            ]
        };
                   
        var line = document.getElementById('line');
        var lineCtx = line.getContext('2d');
        var chart1 = new Chart(lineCtx).Line(data);

        var line2 = document.getElementById('line2');
        var line2Ctx = line2.getContext('2d');
        var chart2 = new Chart(line2Ctx).Line(data);

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
                background-image: url("images/ui-bg_diagonals-small_50_466580_40x40.png");
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
                window.location = 'notication.jsp';
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
                img.src = "images/" + image;

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
  
    <div class='widget-header-indicator'></div>
  
    </head>
      <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("Controller?process=UserProcess&action=sessionTimeOut");
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
    <body >
    
    <div class='widget widget--2x1'>

  <div class='widget-content'>
    <canvas id='donut' width='200px'></canvas>
    <ul class='value-list'>
      <li>
        <div class='description'>
          Issuance
        </div>
        <div class='value'>
          <span>101,342</span>
          <span class='positive small'>
            10.8%
          </span>
        </div>
      </li>
      <li>
        <div class='description'>
          Redemption
        </div>
        <div class='value'>
          <span>55,342</span>
          <span class='negative small'>
            2.8%
          </span>
        </div>
      </li>
    </ul>
  </div>
  <footer class='widget-footer'>
    <a class='fa fa-trash-o'></a>
    <a class='fa fa-cog'></a>
  </footer>
</div>
<div class='widget widget--1x1'>
  <header class='widget-header'>
    <div class='widget-header-indicator'></div>
  </header>
  <div class='widget-content'>
    <ul class='value-list'>
      <li>
        <div class='description'>
          Issuance
        </div>
        <div class='value'>
          <span class='positive'>
            10.8%
          </span>
        </div>
      </li>
      <li>
        <div class='description'>
          Redemption
        </div>
        <div class='value'>
          <span class='negative'>
            2.8%
          </span>
        </div>
      </li>
    </ul>
  </div>
  <footer class='widget-footer'>
    <a class='fa fa-trash-o'></a>
    <a class='fa fa-cog'></a>
  </footer>
</div>
<div class='widget widget--2x1'>
  <header class='widget-header'>
    <div class='widget-header-indicator'></div>
  </header>
  <div class='widget-content'>
    <canvas height='160px' id='line2' width='480px'></canvas>
  </div>
  <footer class='widget-footer'>
    <a class='fa fa-trash-o'></a>
    <a class='fa fa-cog'></a>
  </footer>
</div>
<div class='widget widget--3x1'>
  <header class='widget-header'>
    <div class='widget-header-indicator'></div>
  </header>
  <div class='widget-content'>
    <canvas height='160px' id='line' width='712px'></canvas>
  </div>
  <footer class='widget-footer'>
    <a class='fa fa-trash-o'></a>
    <a class='fa fa-cog'></a>
  </footer>
</div>
        </body>

</html>
