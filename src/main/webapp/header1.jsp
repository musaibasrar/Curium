<%-- 
    Document   : header
    Created on : Feb 13, 2013, 11:10:08 AM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>School Management</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <meta name="Description" content = "School,School Management Software,SchoolCRM,">
        <meta name="Keywords" content = "School,School Management Software,SchoolCRM,">
        <script type="text/javascript">
            var getMember;
            var getVisit;
            function getdata() {

                if (typeof XMLHttpRequest != "undefined") {
                    getMember = new XMLHttpRequest();
                    getVisit = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    getMember = new ActiveXObject("Microsoft.XMLHTTP");
                    getVisit = new ActiveXObject("Microsoft.XMLHTTP");
                }

                getMember.onreadystatechange = processMemberData;
                getMember.open("POST", "StudentController",true);
                getMember.send(null);

                getVisit.onreadystatechange = processVisitData;
                getVisit.open("POST", "AppointmentController",true);
                getVisit.send(null);
            }

            function processMemberData() {
                if (getMember.readyState==4)
                {
                    if (getMember.status==200){

                        var count = getMember.responseXML.getElementsByTagName("count")[0];
                        var childCount=count.childNodes[0].nodeValue;
                        var mdiv = document.getElementById("n1");
                        mdiv.innerHTML=childCount;
                        mdiv.style.visibility='visible';
                        setTimeout('getdata();', 60000);


                    }
                }

            }
            function processVisitData() {
                if (getVisit.readyState==4)
                {
                    if (getVisit.status==200){

                        var visitCount = getVisit.responseXML.getElementsByTagName("visitcount")[0];
                        var childVisitCount=visitCount.childNodes[0].nodeValue;
                        var mdiv = document.getElementById("n2");
                        mdiv.innerHTML=childVisitCount;
                        mdiv.style.visibility='visible';
                        setTimeout('getdata();', 60000);


                    }
                }

            }

        </script>
      <!--  <script  type="text/javascript">
            function hidePatientPopup()
            {
                document.getElementById("n1").style.visibility='hidden';
            }
            function hideVisitPopup()
            {

                document.getElementById("n2").style.visibility='hidden';
            }
        </script> -->
        <style type="text/css">
            <!--
            .style1 {font-family: Arial, Helvetica, sans-serif;
                     font-size: 12px;
                     color: #666666;
            }
            .style50 {color: #FFFFFF}
            .style6 {font-size: 10; font-family: Arial, Helvetica, sans-serif; color: #FFFFFF; }
            a:link {
                color: #FFFFFF;
                text-decoration: none;
            }
            a:visited {
                text-decoration: none;
                color: #FFFFFF;
            }
            a:hover {
                text-decoration: none;
                color: #CBE577;
            }
            a:active {
                text-decoration: none;
                color: #FFFFFF;
            }
            .style5 {font-family: Calibri; font-size: 14px; color: #FFFFFF; }
            .style51 {
                font-size: 14px;
                font-family: Calibri;
            }
            .style52 {
                font-size: 16px;
                font-weight: bold;
                color: #13358F;
            }
            .noti_bubble {
                position:relative;
                font-size: 11px;
                top: -12px;
                left: 20px;
                padding-right:2px;
                background-color:#4B6A84;
                color:white;
                font-weight:normal;
                z-index: 2;
                width: 30px;
                height: 16px;
                border-radius:2px;
                box-shadow:1px 1px 1px gray;

            }
            .noti_bubbleEmpty {
                position:relative;

                top: -16px;
                left: 10px;
                padding-right:2px;


                width: 10px;
                height: 16px;

            }
            -->
        </style>
        <script type="text/javascript">
            function logout(){
                var form1=document.getElementById("form1");
                form1.action="Controller?process=UserProcess&action=logout";
                form1.submit();
            }

        </script>
    </head>
    <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
        <form id="form1" method="post" >
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td height="10" valign="top" background="images/headerBook.png">
                        <table width="100%" height="137" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
                            <tr>
                               <td width="234" rowspan="2"><!--<div align="center"><img src="images/logo.png" width="200" height="64" /></div> --></td> 
                                <!-- <td width="548" rowspan="2"><div align="center"><img src="images/SMS.png" width="391" height="92" /></div></td> -->
                                <!-- <td width="210" height="104">&nbsp;</td> -->
                            </tr>
                            <tr>
                                <td></td>
                                <td width="150" height="34" background="images/tab1.png"><table width="153" border="0" align="center" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td width="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp</td>
                                            <td width="40"><a target="mainFrame" href="Controller?process=StudentProcess&action=viewAllStudentsWithParents"><div align="center" align="center"><img  alt="View All Studets" src="images/Users.png" width="40" height="30" /> <div id="n1" class="noti_bubble"></div></div></a></td>
                                            <td width="40"><a target="mainFrame" href="Controller?process=PersonalProcess&action=viewAll"><div align="center"  align="center"></div></div></a></td>
                                            <td width="40"><a target="mainFrame" href="Controller?process=UserProcess&action=dashBoard"><div align="center" align="center"><img  alt="Dash Board" src="images/dashboard.png" width="30" height="30" /> <div id="" class="noti_bubbleEmpty"></div></div></a></td>
                                            <td width="40" ><div align="center" ><a target="_parent" href="Controller?process=UserProcess&action=logout"><img   src="images/logout_icon.png" width="24" height="24" alt="Log Out" /></a><div class="noti_bubbleEmpty"></div></div></td>
                                        </tr>
                                    </table></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <script>
                getdata();
            </script>
        </form>
    </body>

</html>
